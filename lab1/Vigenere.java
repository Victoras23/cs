public class Vigenere implements Cipher {

    String key;

    Vigenere(String key) {
        this.key = key;
    }

    @Override
    public String encrypt(String message) {
        String toBeReturned = "";
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            toBeReturned += (char) ((c + this.key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % this.key.length();
        }
        return toBeReturned;
    }

    @Override
    public String Decrypt(String message) {
        String toBeReturned = "";
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            toBeReturned += (char) ((c - this.key.charAt(j) + 26) % 26 + 'A');
            j = ++j % this.key.length();
        }
        return toBeReturned;
    }

}
