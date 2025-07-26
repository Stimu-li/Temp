import java.util.*;

/**
 * Playfair Cipher implementation
 * Encrypts/decrypts text using 5x5 key matrix and pair-based rules
 */
class PlayfairCipher {
    private static char[][] matrix = new char[5][5];
    
    // Create 5x5 matrix from keyword
    public static void createMatrix(String key) {
        String alphabet = "abcdefghiklmnopqrstuvwxyz"; // j is omitted
        boolean[] used = new boolean[26];
        int row = 0, col = 0;
        
        // Add keyword letters first
        for (char c : key.toLowerCase().toCharArray()) {
            if (c == 'j') c = 'i'; // treat j as i
            if (!used[c - 'a']) {
                matrix[row][col] = c;
                used[c - 'a'] = true;
                if (++col == 5) { col = 0; row++; }
            }
        }
        
        // Fill remaining with alphabet
        for (char c : alphabet.toCharArray()) {
            if (!used[c - 'a']) {
                matrix[row][col] = c;
                if (++col == 5) { col = 0; row++; }
            }
        }
    }
    
    // Find position of character in matrix
    public static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }
    
    // Encrypt text using Playfair rules
    public static String encrypt(String text) {
        text = text.toLowerCase().replace("j", "i");
        StringBuilder processed = new StringBuilder();
        
        // Process pairs
        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'x';
            
            // Add filler if same letters
            if (first == second) {
                processed.append(first).append('x');
                i--; // reprocess second char
            } else {
                processed.append(first).append(second);
            }
        }
        
        // Add padding if odd length
        if (processed.length() % 2 == 1) processed.append('x');
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < processed.length(); i += 2) {
            char c1 = processed.charAt(i);
            char c2 = processed.charAt(i + 1);
            
            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);
            
            if (pos1[0] == pos2[0]) { // Same row
                result.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                result.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { // Rectangle
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return result.toString();
    }
    
    // Decrypt text using Playfair rules
    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < text.length(); i += 2) {
            char c1 = text.charAt(i);
            char c2 = text.charAt(i + 1);
            
            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);
            
            if (pos1[0] == pos2[0]) { // Same row
                result.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                result.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                result.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                result.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else { // Rectangle
                result.append(matrix[pos1[0]][pos2[1]]);
                result.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return result.toString();
    }
    
    // Display the 5x5 matrix
    public static void displayMatrix() {
        System.out.println("\nThe matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the message: ");
        String message = sc.nextLine();
        
        System.out.print("Enter the key: ");
        String key = sc.nextLine();
        
        createMatrix(key);
        displayMatrix();
        
        String encrypted = encrypt(message);
        String decrypted = decrypt(encrypted);
        
        System.out.println("\nPlayfair Cipher Text: " + encrypted);
        System.out.println("Playfair Plain Text: " + decrypted);
        
        sc.close();
    }
}
