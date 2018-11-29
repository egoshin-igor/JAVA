class CaesarCipher {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Wrong arguments");
      return;
    }
    CipherMode cipherMode;
    switch (args[0]) {
      case "-e":
        cipherMode = CipherMode.Encoding;
        break;
      case "-d":
        cipherMode = CipherMode.Decoding;
        break;
      default:
        System.out.println("Wrong arguments");
        return;
    }
    String originString = args[2];
    int key;
    try {
      key = Integer.parseInt(args[1]);
    } catch (Exception e) {
      System.out.println("Wrong arguments");
      return;
    }
    String cipher = Cipher.getCipher(originString, cipherMode, key);
    if (cipher != null) {
      System.out.println(cipher);
    } else {
      System.out.println("Wrong arguments");
    }
  }
}