
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String message = "This message must be protected";

        Random rand = new Random();

        Cipher cesar = new Cesar(rand.nextInt(25) + 1);
        Cipher cessarWithPermutations = new CesarWithPermutations(rand.nextInt(25) + 1, "encrypt");
        Cipher vigenere = new Vigenere("VIGENERECIPHER");
        Cipher playfair = new Playfair("encrypt");

        String cesarEcrypted = cesar.encrypt(message);
        String cesarWithPermutationEncrypted = cessarWithPermutations.encrypt(message);
        String vigenereEncrypted = vigenere.encrypt(message);
        String playfairEncrypted = playfair.encrypt(message);

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
    }
}