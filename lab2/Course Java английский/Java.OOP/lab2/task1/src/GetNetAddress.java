import list.util.ListUtil;

import java.util.ArrayList;

class GetNetAddress {
  private static final int REQUIRED_IP_ADDRESS_LENGTH = 4;
  private static final int REQUIRED_SUBNET_MASK_LENGTH = 4;

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("GetNetAddress <IP address> <Subnet mask>");
      return;
    }

    ArrayList<Byte> ipAddress;
    ArrayList<Byte> subnetMask;

    try {
      ipAddress = ListUtil.getByteList(args[0]);
    } catch (Exception e) {
      System.out.println("Wrong ip address");
      return;
    }
    try {
      subnetMask = ListUtil.getByteList(args[1]);
    } catch (Exception e) {
      System.out.println("Wrong mask");
      return;
    }

    if (ipAddress.size() != REQUIRED_IP_ADDRESS_LENGTH) {
      System.out.println("Wrong ip address");
      return;
    }

    if (!validateMask(subnetMask)) {
      System.out.println("Wrong mask");
      return;
    }

    ArrayList<Byte> netAddress = getNetAddress(ipAddress, subnetMask);

    System.out.println(ListUtil.getString(netAddress, '.'));
  }

  private static ArrayList<Byte> getNetAddress(ArrayList<Byte> ipAddress, ArrayList<Byte> subnetMask) {
    ArrayList<Byte> netAddress = new ArrayList<>();
    byte netAddressPart;
    for (int i = 0; i < ipAddress.size(); i++) {
      netAddressPart = (byte) (ipAddress.get(i) & subnetMask.get(i));
      netAddress.add(netAddressPart);
    }

    return netAddress;
  }

  private static boolean validateMask(ArrayList<Byte> subnetMask) {
    if (subnetMask.size() != REQUIRED_SUBNET_MASK_LENGTH) {
      return false;
    }

    for (Byte b : subnetMask) {
      String byteString = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
      byteString = new StringBuilder(byteString).reverse().toString();
      boolean firstOneFound = false;
      for (int i = 0; i < byteString.length(); i++) {
        if (firstOneFound && byteString.charAt(i) == '0') {
          return false;
        }

        if (byteString.charAt(i) == '1') {
          firstOneFound = true;
        }
      }
    }
    return true;
  }
}