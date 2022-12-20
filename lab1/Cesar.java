public class Cesar implements Cipher {

    int key;
    String lowerCase, uppercase;

    Cesar(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {
        String toBeReturned = "";
        char thisLetter;
        for (int i = 0; i < message.length(); i++) {
            if ((message.charAt(i) >= 65 && message.charAt(i) <= 90)
                    || (message.charAt(i) >= 97 && message.charAt(i) <= 122)) {
                thisLetter = message.charAt(i);
                int value = thisLetter + this.key;
                if ((message.charAt(i) >= 65 && message.charAt(i) <= 90 && value > 90)
                        || (message.charAt(i) >= 97 && message.charAt(i) <= 122 && value > 122)) {
                    value = value - 26;
                }
                thisLetter = (char) value;
            } else {
                thisLetter = message.charAt(i);
            }
            toBeReturned += thisLetter;
        }
        return toBeReturned;
    }

    @Override
    public String Decrypt(String message) {
        String toBeReturned = "";
        char thisLetter;
        for (int i = 0; i < message.length(); i++) {
            if ((message.charAt(i) >= 65 && message.charAt(i) <= 90)
                    || (message.charAt(i) >= 97 && message.charAt(i) <= 122)) {
                thisLetter = message.charAt(i);
                int value = thisLetter - this.key;
                if ((message.charAt(i) >= 65 && message.charAt(i) <= 90 && value < 65)
                        || (message.charAt(i) >= 97 && message.charAt(i) <= 122 && value < 97)) {
                    value = value + 26;
                }
                thisLetter = (char) value;
            } else {
                thisLetter = message.charAt(i);
            }
            toBeReturned += thisLetter;
        }
        return toBeReturned;
    }

}
