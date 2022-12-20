package lab2;

public class RC4 implements CipherLab2 {

    static final int N = 256;
    static int temp = 0;
    private String key;

    public RC4(String key) {
        this.key = key;
    }

    String RC4(final String text) {
        int[] S = new int[N];
        String keyStream = "";
        String result = "";
        S = KSA(S);
        keyStream = PRGA(S, keyStream, text.length());
        for (int i = 0; i < text.length(); ++i) {
            result += (char) (text.charAt(i) ^ keyStream.charAt(i));
        }

        return result;
    }

    static String PRGA(int[] S, String keyStream, final int textLen) {
        int i = 0, j = 0;
        for (int k = 0; k < textLen; ++k) {
            i = (i + 1) % N;
            j = (j + S[i]) % N;
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            keyStream += (char) ((S[(S[i] + S[j]) % N]));
        }

        return keyStream;
    }

    int[] KSA(int[] S) {
        for (int i = 0; i < N; ++i) {
            S[i] = i;
        }
        int j = 0;
        int kLen = this.key.length();
        for (int i = 0; i < N; ++i) {
            j = (j + S[i] + (int) key.charAt(i % kLen)) % N;
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }

        return S;
    }

    @Override
    public String encrypt(String message) {
        return RC4(message);
    }

    @Override
    public String Decrypt(String message) {
        return RC4(message);
    }

}
