public class PGenerator {
    public static String getPassword(String mask, int length) {
        if (length <= 0 || mask.isEmpty()) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        StringBuilder stringBuilder = new StringBuilder();
        int randomPosition;
        for (int i = 0; i < length; i++) {
            randomPosition = (int) (Math.random() * mask.length());
            stringBuilder.append(mask.charAt(randomPosition));
        }

        return stringBuilder.toString();
    }
}
