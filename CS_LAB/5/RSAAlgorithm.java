import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * RSA Algorithm implementation
 * Asymmetric encryption using public-private key pairs
 */
class RSAAlgorithm {
    private static Scanner sc = new Scanner(System.in);
    
    // Generate suitable value of e (public exponent)
    public static BigInteger generateE(BigInteger phi) {
        Random random = new Random();
        BigInteger e;
        
        do {
            // Generate random number between 3 and phi-1
            e = new BigInteger(phi.bitLength() - 1, random);
            // Ensure e is odd and greater than 2
            if (e.compareTo(BigInteger.valueOf(2)) <= 0) {
                e = BigInteger.valueOf(3);
            }
        } while (phi.gcd(e).compareTo(BigInteger.ONE) != 0);
        
        return e;
    }
    
    // Check if number is prime (simple check for small numbers)
    public static boolean isPrime(BigInteger num) {
        if (num.compareTo(BigInteger.valueOf(2)) < 0) return false;
        if (num.equals(BigInteger.valueOf(2))) return true;
        if (num.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return false;
        
        BigInteger sqrt = num.sqrt();
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(sqrt) <= 0; i = i.add(BigInteger.valueOf(2))) {
            if (num.remainder(i).equals(BigInteger.ZERO)) return false;
        }
        return true;
    }
    
    // Encrypt message using public key
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }
    
    // Decrypt ciphertext using private key
    public static BigInteger decrypt(BigInteger ciphertext, BigInteger d, BigInteger n) {
        return ciphertext.modPow(d, n);
    }
    
    // Display RSA process information
    public static void displayRSAInfo() {
        System.out.println("=== RSA Algorithm Demo ===");
        System.out.println("RSA is an asymmetric encryption algorithm");
        System.out.println("Uses separate keys for encryption and decryption");
        System.out.println("Security based on difficulty of factoring large primes");
        System.out.println("=======================================\n");
    }
    
    public static void main(String[] args) {
        displayRSAInfo();
        
        try {
            // Input prime numbers
            System.out.print("Enter first prime number (p): ");
            BigInteger p = sc.nextBigInteger();
            
            System.out.print("Enter second prime number (q): ");
            BigInteger q = sc.nextBigInteger();
            
            // Validate inputs
            if (!isPrime(p) || !isPrime(q)) {
                System.out.println("Warning: Please ensure both numbers are prime for security!");
            }
            
            if (p.equals(q)) {
                System.out.println("Warning: p and q should be different for security!");
            }
            
            // Calculate RSA parameters
            BigInteger n = p.multiply(q);
            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            BigInteger e = generateE(phi);
            BigInteger d = e.modInverse(phi);
            
            // Display keys
            System.out.println("\n--- Key Generation ---");
            System.out.println("p = " + p);
            System.out.println("q = " + q);
            System.out.println("n = p × q = " + n);
            System.out.println("φ(n) = (p-1)(q-1) = " + phi);
            System.out.println("e (public exponent) = " + e);
            System.out.println("d (private exponent) = " + d);
            
            System.out.println("\nPublic Key: (" + e + ", " + n + ")");
            System.out.println("Private Key: (" + d + ", " + n + ")");
            
            // Encryption
            System.out.print("\nEnter message (number < " + n + "): ");
            BigInteger message = sc.nextBigInteger();
            
            if (message.compareTo(n) >= 0) {
                System.out.println("Error: Message must be less than n = " + n);
                return;
            }
            
            System.out.println("\n--- Encryption Process ---");
            System.out.println("Plaintext: " + message);
            BigInteger ciphertext = encrypt(message, e, n);
            System.out.println("Ciphertext: C = M^e mod n = " + message + "^" + e + " mod " + n + " = " + ciphertext);
            
            // Decryption
            System.out.println("\n--- Decryption Process ---");
            BigInteger decrypted = decrypt(ciphertext, d, n);
            System.out.println("Decrypted: M = C^d mod n = " + ciphertext + "^" + d + " mod " + n + " = " + decrypted);
            
            // Verification
            System.out.println("\n--- Verification ---");
            System.out.println("Original Message: " + message);
            System.out.println("Decrypted Message: " + decrypted);
            System.out.println("Match: " + message.equals(decrypted));
            
        } catch (Exception e) {
            System.err.println("RSA Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
