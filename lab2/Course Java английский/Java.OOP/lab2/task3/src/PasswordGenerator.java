class PasswordGenerator {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Wrong arguments");
      return;
    }

    int length;
    try {
      length = Integer.parseInt(args[0]);
      if (length <= 0) {
        throw new NumberFormatException();
      }
    } catch (Exception e) {
      System.out.println("Wrong arguments");
      return;
    }

    String mask = args[1];

    System.out.println(PGenerator.getPassword(mask, length));
  }
}