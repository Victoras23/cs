public class CesarWithPermutations implements Cipher {

    int key;
    String PermutationKey;
    String PermutationKeyAfter;
    Cipher cesar;

    CesarWithPermutations(int key, String permutatuinKey) {
        this.key = key;
        this.PermutationKey = permutatuinKey;
        this.cesar = new Cesar(key);
    }

    private String[] swap(String[] permutation, int x1, int x2, int width) {
        for (int i = 0; i < width; i++) {
            if (x1 < permutation[i].length() && x2 < permutation[i].length()) {
                char temp1 = permutation[i].charAt(x1), temp2 = permutation[i].charAt(x2);
                StringBuilder prepare = new StringBuilder();
                prepare.append(permutation[i]);
                prepare.setCharAt(x1, temp2);
                prepare.setCharAt(x2, temp1);

                permutation[i] = prepare.toString();
            }
        }
        return permutation;
    }

    private String permutation1(String message) {
        int length = this.PermutationKey.length();
        int width = message.length() / length + 1;
        if (message.length() % length > 0)
            width += 1;
        String[] permutation = new String[width];
        permutation[0] = this.PermutationKey;
        int row = 1, count = 0;
        String add = "";
        for (int i = 0; i < message.length(); i++) {
            add += message.charAt(i);
            count += 1;
            if (count == length) {
                permutation[row] = add;
                add = "";
                count = 0;
                row += 1;
            }
        }
        if (!add.equals("")) {
            permutation[row] = add;
        }

        while (permutation[width - 1].length() < length) {
            permutation[width - 1] += " ";
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (permutation[0].charAt(j) > permutation[0].charAt(j + 1)) {
                    permutation = swap(permutation, j, j + 1, width);
                }
            }
        }

        this.PermutationKeyAfter = "";

        for (int j = 0; j < permutation[0].length(); j++) {
            this.PermutationKeyAfter += permutation[0].charAt(j);
        }

        String toBeReturned = "";
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < permutation[i].length(); j++) {
                toBeReturned += permutation[i].charAt(j);
            }
        }

        return toBeReturned;
    }

    private String permutation2(String message) {
        int length = this.PermutationKey.length();
        int width = message.length() / length + 1;
        if (message.length() % length > 0)
            width += 1;
        String[] permutation = new String[width];
        permutation[0] = this.PermutationKeyAfter;
        int row = 1, count = 0;
        String add = "";
        for (int i = 0; i < message.length(); i++) {
            add += message.charAt(i);
            count += 1;
            if (count == length) {
                permutation[row] = add;
                add = "";
                count = 0;
                row += 1;
            }
        }
        if (!add.equals("")) {
            permutation[row] = add;
        }

        while (permutation[width - 1].length() < length) {
            permutation[width - 1] += " ";
        }

        for (int i = 0; i < this.PermutationKey.length(); i++) {
            for (int j = 0; j < permutation[0].length(); j++) {
                if (this.PermutationKey.charAt(i) == permutation[0].charAt(j)) {
                    permutation = swap(permutation, i, j, width);
                }
            }
        }

        String toBeReturned = "";
        for (int i = 1; i < width; i++) {
            for (int j = 0; j < permutation[i].length(); j++) {
                toBeReturned += permutation[i].charAt(j);
            }
        }

        return toBeReturned;
    }

    @Override
    public String encrypt(String message) {

        return this.cesar.encrypt(permutation1(message));
    }

    @Override
    public String Decrypt(String message) {

        return permutation2(this.cesar.Decrypt(message));
    }

}
