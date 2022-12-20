package lab2;

public class DES implements CipherLab2 {

    String key;

    public DES(String key) {
        this.key = key;
    }

    String hextoBin(String input) {
        int n = input.length() * 4;
        input = Long.toBinaryString(
                Long.parseUnsignedLong(input, 16));
        while (input.length() < n)
            input = "0" + input;
        return input;
    }

    String binToHex(String input) {
        int n = (int) input.length() / 4;
        input = Long.toHexString(
                Long.parseUnsignedLong(input, 2));
        while (input.length() < n)
            input = "0" + input;
        return input;
    }

    String permutation(int[] sequence, String input) {
        String output = "";
        input = hextoBin(input);
        for (int i = 0; i < sequence.length; i++)
            output += input.charAt(sequence[i] - 1);
        output = binToHex(output);
        return output;
    }

    String xor(String a, String b) {
        long t_a = Long.parseUnsignedLong(a, 16);
        long t_b = Long.parseUnsignedLong(b, 16);
        t_a = t_a ^ t_b;
        a = Long.toHexString(t_a);
        while (a.length() < b.length())
            a = "0" + a;
        return a;
    }

    String leftCircularShift(String input, int numBits) {
        int n = input.length() * 4;
        int perm[] = new int[n];
        for (int i = 0; i < n - 1; i++)
            perm[i] = (i + 2);
        perm[n - 1] = 1;
        while (numBits-- > 0)
            input = permutation(perm, input);
        return input;
    }

    String[] getKeys(String key) {
        String keys[] = new String[16];
        key = permutation(Constants.PC1, key);
        for (int i = 0; i < 16; i++) {
            key = leftCircularShift(key.substring(0, 7),
                    Constants.shiftBits[i])
                    + leftCircularShift(
                            key.substring(7, 14),
                            Constants.shiftBits[i]);
            keys[i] = permutation(Constants.PC2, key);
        }
        return keys;
    }

    String sBox(String input) {
        String output = "";
        input = hextoBin(input);
        for (int i = 0; i < 48; i += 6) {
            String temp = input.substring(i, i + 6);
            int num = i / 6;
            int row = Integer.parseInt(
                    temp.charAt(0) + "" + temp.charAt(5),
                    2);
            int col = Integer.parseInt(
                    temp.substring(1, 5), 2);
            output += Integer.toHexString(
                    Constants.sbox[num][row][col]);
        }
        return output;
    }

    String round(String input, String key, int num) {
        String left = input.substring(0, 8);
        String temp = input.substring(8, 16);
        String right = temp;
        temp = permutation(Constants.EP, temp);
        temp = xor(temp, key);
        temp = sBox(temp);
        temp = permutation(Constants.P, temp);
        left = xor(left, temp);
        return right + left;
    }

    String encryptDES(String plainText) {
        int i;
        String keys[] = getKeys(this.key);

        plainText = permutation(Constants.IP, plainText);
        for (i = 0; i < 16; i++) {
            plainText = round(plainText, keys[i], i);
        }

        plainText = plainText.substring(8, 16)
                + plainText.substring(0, 8);
        plainText = permutation(Constants.IP1, plainText);
        return plainText;
    }

    String decryptDES(String plainText) {
        int i;
        String keys[] = getKeys(this.key);

        plainText = permutation(Constants.IP, plainText);
        for (i = 15; i > -1; i--) {
            plainText = round(plainText, keys[i], 15 - i);
        }

        plainText = plainText.substring(8, 16)
                + plainText.substring(0, 8);
        plainText = permutation(Constants.IP1, plainText);
        return plainText;
    }

    @Override
    public String encrypt(String message) {
        return this.encryptDES(message);
    }

    @Override
    public String Decrypt(String message) {
        return this.decryptDES(message);
    }
}
