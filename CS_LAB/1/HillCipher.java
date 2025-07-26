import java.util.Scanner;

/**
 * Hill Cipher implementation
 * Encrypts text using matrix multiplication with a key matrix
 */
class HillCipher {
    // 3x3 key matrix
    private static final int[][] KEY_MATRIX = {
        {17, 17, 5},
        {21, 18, 21},
        {2, 2, 19}
    };
    
    // Convert text to numerical array
    public static int[] textToNumbers(String text) {
        int[] numbers = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            numbers[i] = text.charAt(i) - 'a';
        }
        return numbers;
    }
    
    // Convert numerical array to text
    public static String numbersToText(int[] numbers, int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append((char) ((numbers[i] % 26) + 'a'));
        }
        return result.toString();
    }
    
    // Encrypt text using Hill cipher
    public static String encrypt(String plaintext) {
        plaintext = plaintext.toLowerCase().replace(" ", "");
        
        // Pad text to multiple of 3
        while (plaintext.length() % 3 != 0) {
            plaintext += "x";
        }
        
        int[] plainArray = textToNumbers(plaintext);
        int[] cipherArray = new int[plaintext.length()];
        
        // Process in blocks of 3
        for (int block = 0; block < plaintext.length() / 3; block++) {
            int startIndex = block * 3;
            
            // Matrix multiplication for each position in block
            for (int i = 0; i < 3; i++) {
                cipherArray[startIndex + i] = 0;
                for (int j = 0; j < 3; j++) {
                    cipherArray[startIndex + i] += KEY_MATRIX[i][j] * plainArray[startIndex + j];
                }
            }
        }
        
        return numbersToText(cipherArray, plaintext.length());
    }
    
    // Display key matrix
    public static void displayKeyMatrix() {
        System.out.println("Key Matrix:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(KEY_MATRIX[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter plain text: ");
        String plaintext = sc.nextLine();
        
        displayKeyMatrix();
        
        String encrypted = encrypt(plaintext);
        
        System.out.println("\nOriginal Text: " + plaintext);
        System.out.println("Encrypted Text: " + encrypted);
        
        sc.close();
    }
}
