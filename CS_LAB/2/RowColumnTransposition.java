import java.util.Scanner;
import java.util.Arrays;

/**
 * Row Column Transposition Cipher implementation
 * Encrypts text by rearranging characters in keyword-ordered columns
 */
class RowColumnTransposition {
    
    // Assign numbers to keyword letters based on alphabetical order
    public static int[] getKeywordOrder(String keyword) {
        int[] order = new int[keyword.length()];
        char[] sortedKeyword = keyword.toCharArray();
        Arrays.sort(sortedKeyword);
        
        for (int i = 0; i < keyword.length(); i++) {
            char currentChar = keyword.charAt(i);
            for (int j = 0; j < sortedKeyword.length; j++) {
                if (currentChar == sortedKeyword[j]) {
                    order[i] = j + 1;
                    sortedKeyword[j] = '*'; // Mark as used
                    break;
                }
            }
        }
        return order;
    }
    
    // Get column indices in order of keyword numbering
    public static int[] getColumnOrder(String keyword, int[] keywordOrder) {
        int[] columnOrder = new int[keyword.length()];
        for (int i = 1; i <= keyword.length(); i++) {
            for (int j = 0; j < keywordOrder.length; j++) {
                if (keywordOrder[j] == i) {
                    columnOrder[i - 1] = j;
                    break;
                }
            }
        }
        return columnOrder;
    }
    
    // Encrypt text using columnar transposition
    public static String encrypt(String plaintext, String keyword) {
        plaintext = plaintext.toUpperCase().replace(" ", "");
        keyword = keyword.toUpperCase();
        
        // Pad text to fill complete rows
        int extraChars = plaintext.length() % keyword.length();
        if (extraChars != 0) {
            int padding = keyword.length() - extraChars;
            for (int i = 0; i < padding; i++) {
                plaintext += ".";
            }
        }
        
        int rows = plaintext.length() / keyword.length();
        char[][] matrix = new char[rows][keyword.length()];
        
        // Fill matrix row by row
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                matrix[i][j] = plaintext.charAt(index++);
            }
        }
        
        // Display matrix
        System.out.println("\nMatrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        // Get column reading order
        int[] keywordOrder = getKeywordOrder(keyword);
        int[] columnOrder = getColumnOrder(keyword, keywordOrder);
        
        // Read columns in keyword order
        StringBuilder ciphertext = new StringBuilder();
        for (int col : columnOrder) {
            for (int row = 0; row < rows; row++) {
                ciphertext.append(matrix[row][col]);
            }
        }
        
        return ciphertext.toString();
    }
    
    // Decrypt text using columnar transposition
    public static String decrypt(String ciphertext, String keyword) {
        keyword = keyword.toUpperCase();
        int rows = ciphertext.length() / keyword.length();
        char[][] matrix = new char[rows][keyword.length()];
        
        // Get column order
        int[] keywordOrder = getKeywordOrder(keyword);
        int[] columnOrder = getColumnOrder(keyword, keywordOrder);
        
        // Fill matrix column by column in keyword order
        int index = 0;
        for (int col : columnOrder) {
            for (int row = 0; row < rows; row++) {
                matrix[row][col] = ciphertext.charAt(index++);
            }
        }
        
        // Read matrix row by row
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                plaintext.append(matrix[i][j]);
            }
        }
        
        return plaintext.toString().replace(".", "");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Row Column Transposition Cipher");
        System.out.print("1. Encryption\n2. Decryption\nChoose (1/2): ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        
        if (choice == 1) {
            System.out.print("Enter message: ");
            String message = sc.nextLine();
            
            System.out.print("Enter keyword: ");
            String keyword = sc.nextLine();
            
            // Display keyword order
            int[] order = getKeywordOrder(keyword.toUpperCase());
            System.out.println("\nKeyword: " + keyword.toUpperCase());
            System.out.print("Order:   ");
            for (int num : order) {
                System.out.print(num + " ");
            }
            System.out.println();
            
            String encrypted = encrypt(message, keyword);
            System.out.println("\nCipher Text: " + encrypted);
            
        } else if (choice == 2) {
            System.out.print("Enter cipher text: ");
            String ciphertext = sc.nextLine();
            
            System.out.print("Enter keyword: ");
            String keyword = sc.nextLine();
            
            String decrypted = decrypt(ciphertext, keyword);
            System.out.println("Plain Text: " + decrypted);
            
        } else {
            System.out.println("Invalid choice!");
        }
        
        sc.close();
    }
}
