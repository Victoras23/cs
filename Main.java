
import java.math.BigInteger;
import java.util.Random;
import lab2.*;
import lab3.*;

public class Main {
    public static void main(String[] args) {
        String message = "This message must be protected";

        Random rand = new Random();

        Cipher cesar = new Cesar(rand.nextInt(25) + 1);
        Cipher cessarWithPermutations = new CesarWithPermutations(rand.nextInt(25) + 1, "encrypt");
        Cipher vigenere = new Vigenere("VIGENERECIPHER");
        Cipher playfair = new Playfair("encrypt");
        CipherLab2 rc4 = new RC4("encrypt1234567890123456789012387463298");
        CipherLab2 des = new DES("133457799BBCDFF1");
        RSA rsa = new RSA(BigInteger.valueOf(5), BigInteger.valueOf(47), BigInteger.valueOf(37));

        String cesarEcrypted = cesar.encrypt(message);
        String cesarWithPermutationEncrypted = cessarWithPermutations.encrypt(message);
        String vigenereEncrypted = vigenere.encrypt(message);
        String playfairEncrypted = playfair.encrypt(message);
        String rc4Encrypted = rc4.encrypt(message);
        String desMessage = "596F7572214C6970";
        String desEncrypted = des.encrypt(desMessage);
        int[] intEncArr = { 57, 43, 134, 34, 78, 63, 95, 157 };
        BigInteger[] encArr = rsa.intToBigIntArray(intEncArr);

        System.out.println("Original message: " + message);

        System.out.println("\nCesar encrypted: " + cesarEcrypted);
        System.out.println("Cesar decrypted: " + cesar.Decrypt(cesarEcrypted));
        System.out.println("\nCesar with permutation encrypted: " + cesarWithPermutationEncrypted);
        System.out.println(
                "Cesar with permutation decrypted: " + cessarWithPermutations.Decrypt(cesarWithPermutationEncrypted));
        System.out.println("\nVigenere encrypted: " + vigenereEncrypted);
        System.out.println("Vigenere decrypted: " + vigenere.Decrypt(vigenereEncrypted));
        System.out.println("\nPlayfair encrypted: " + playfairEncrypted);
        System.out.println("Playfair decrypted: " + playfair.Decrypt(playfairEncrypted));
        System.out.println("\nRC4 encrypted: " + rc4Encrypted);
        System.out.println("RC4 decrypted: " + rc4.Decrypt(rc4Encrypted));
        System.out.println("\nDES original: " + desMessage);
        System.out.println("DES encrypted: " + desEncrypted);
        System.out.println("DES decrypted: " + des.Decrypt(desEncrypted));
        System.out.println("\nRSA encrypted sequence: ");
        for (int i = 0; i < encArr.length; i++) {
            System.out.print(encArr[i] + ", ");
        }
        int[] decSeq = rsa.decryptForStringMessage(encArr);
        System.out.println("\nRSA decrypted sequence: ");
        for (int i = 0; i < decSeq.length; i++) {
            System.out.print(decSeq[i] + ", ");
        }
        System.out.println("\nRSA decrypted: " + rsa.decrypt(rsa, decSeq));
    }
}