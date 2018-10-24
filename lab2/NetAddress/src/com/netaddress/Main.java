package com.netaddress;

import com.netaddress.list.util.ListUtil;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final int REQUIRED_IP_ADDRESS_LENGTH = 4;
        final int REQUIRED_SUBNET_MASK_LENGTH = 4;

        if (args.length != 2) {
            System.out.println("run so: +.exe <ipAddress> <subnetMask>");
            return;
        }

        try {
                ArrayList<Byte> ipAddress = ListUtil.getByteList(args[0]);
            ArrayList<Byte> subnetMask = ListUtil.getByteList(args[1]);

            if (ipAddress.size() != REQUIRED_IP_ADDRESS_LENGTH && subnetMask.size() != REQUIRED_SUBNET_MASK_LENGTH) {
                throw new SizeLimitExceededException("Correct param format: 0-255.0-255.0-255.0-255");
            }
            ArrayList<Byte> netAddress = getNetAddress(ipAddress, subnetMask);

            System.out.println(ListUtil.getString(netAddress, '.'));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Byte> getNetAddress(ArrayList<Byte> ipAddress, ArrayList<Byte> subnetMask) {
        var netAddress = new ArrayList<Byte>();
        byte netAddressPart;
        for (int i = 0; i < ipAddress.size(); i++) {
            netAddressPart = (byte) (ipAddress.get(i) & subnetMask.get(i));
            netAddress.add(netAddressPart);
        }

        return netAddress;
    }
}
