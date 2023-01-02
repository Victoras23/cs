
import java.math.BigInteger;
import java.util.Random;
import lab2.*;
import lab3.*;

public class Main {
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String WHITE = "\033[0;37m";

    public static void main(String[] args) {
        String message = "This message must be protected";

        Random rand = new Random();

        /////////////////////////////////
        // Declarations
        /////////////////////////////////

        Cipher cesar = new Cesar(rand.nextInt(25) + 1);
        Cipher cessarWithPermutations = new CesarWithPermutations(rand.nextInt(25) + 1, "encrypt");
        Cipher vigenere = new Vigenere("VIGENERECIPHER");
        Cipher playfair = new Playfair("encrypt");
        CipherLab2 rc4 = new RC4("encrypt1234567890123456789012387463298");
        CipherLab2 des = new DES("133457799BBCDFF1");
        RSA rsa = new RSA(BigInteger.valueOf(5), BigInteger.valueOf(47), BigInteger.valueOf(37));

        /////////////////////////////////
        // Encryptions
        /////////////////////////////////

        String cesarEcrypted = cesar.encrypt(message);
        String cesarWithPermutationEncrypted = cessarWithPermutations.encrypt(message);
        String vigenereEncrypted = vigenere.encrypt(message);
        String playfairEncrypted = playfair.encrypt(message);
        String rc4Encrypted = rc4.encrypt(message);
        String desMessage = "596f7572214c6970";
        String desEncrypted = des.encrypt(desMessage);
        BigInteger[] rsaEncrypted = rsa.encryptForStringMessage(message);

        /////////////////////////////////
        // Decriptions
        /////////////////////////////////

        String cesarDecrypted = cesar.Decrypt(cesarEcrypted);
        String cesarWithPermutationDecrypted = cessarWithPermutations.Decrypt(cesarWithPermutationEncrypted);
        String vigenereDecrypted = vigenere.Decrypt(vigenereEncrypted);
        String playfairDecrypted = playfair.Decrypt(playfairEncrypted);
        String rc4Decrypted = rc4.Decrypt(rc4Encrypted);
        String desDecrypted = des.Decrypt(desEncrypted);
        int[] decSeq = rsa.decryptForStringMessage(rsaEncrypted);
        String rsaDecrypted = rsa.decrypt(rsa, decSeq);

        /////////////////////////////////
        // Output
        /////////////////////////////////

        System.out.println("Original message: " + message);

        System.out.println("\nCesar encrypted: " + cesarEcrypted);
        System.out.println("Cesar decrypted: " + cesarDecrypted);
        validation(message, cesarDecrypted);

        System.out.println("\nCesar with permutation encrypted: " + cesarWithPermutationEncrypted);
        System.out.println("Cesar with permutation decrypted: " + cesarWithPermutationDecrypted);
        validation(message, cesarWithPermutationDecrypted.stripTrailing());

        System.out.println("\nVigenere encrypted: " + vigenereEncrypted);
        System.out.println("Vigenere decrypted: " + vigenereDecrypted);
        validation(message.replace(" ", "").toUpperCase(), vigenereDecrypted);

        System.out.println("\nPlayfair encrypted: " + playfairEncrypted);
        System.out.println("Playfair decrypted: " + playfairDecrypted);
        validation(message.replace(" ", "").toUpperCase(), playfairDecrypted);

        System.out.println("\nRC4 encrypted: " + rc4Encrypted);
        System.out.println("RC4 decrypted: " + rc4Decrypted);
        validation(message, rc4Decrypted);

        System.out.println("\nDES encrypted: " + desEncrypted);
        System.out.println("DES decrypted: " + desDecrypted);
        validation(desMessage, desDecrypted);

        System.out.print("\nRSA encrypted sequence: ");
        for (int i = 0; i < rsaEncrypted.length; i++) {
            System.out.print(rsaEncrypted[i] + ", ");
        }
        System.out.print("\nRSA decrypted sequence: ");
        for (int i = 0; i < decSeq.length; i++) {
            System.out.print(decSeq[i] + ", ");
        }
        System.out.println("\nRSA decrypted: " + rsaDecrypted);
        validation(message, rsaDecrypted);
    }

    private static void validation(String initial, String decString) {
        if (initial.equals(decString)) {
            System.out.print(GREEN);
            System.out.println("#################################################################");
            System.out.println("                        Validation Success");
            System.out.println("#################################################################");
            System.out.print(WHITE);
        } else {
            System.out.print(RED);
            System.out.println("#################################################################");
            System.out.println("                        Validation Failed");
            System.out.println("#################################################################");
            System.out.print(WHITE);
        }
    }
}