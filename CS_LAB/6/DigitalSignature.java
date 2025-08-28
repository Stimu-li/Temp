import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Scanner;

/**
 * Digital Signature Standard (DSS) implementation
 * Signs documents using DSA and verifies signatures
 */
class DigitalSignature {
    
    // Generate DSA key pair
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        
        KeyPair pair = keyGen.generateKeyPair();
        System.out.println("✓ DSA Key pair generated successfully");
        
        return pair;
    }
    
    // Sign a document
    public static byte[] signDocument(String fileName, PrivateKey privateKey) throws Exception {
        // Create signature object
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initSign(privateKey);
        
        // Read and update document data
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        
        System.out.println("Reading document: " + fileName);
        while ((len = bufin.read(buffer)) != -1) {
            dsa.update(buffer, 0, len);
        }
        bufin.close();
        
        // Generate signature
        byte[] signature = dsa.sign();
        System.out.println("✓ Document signed successfully");
        
        return signature;
    }
    
    // Verify document signature
    public static boolean verifySignature(String fileName, byte[] signature, PublicKey publicKey) throws Exception {
        // Create signature object for verification
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        dsa.initVerify(publicKey);
        
        // Read and update document data
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        
        while ((len = bufin.read(buffer)) != -1) {
            dsa.update(buffer, 0, len);
        }
        bufin.close();
        
        // Verify signature
        boolean isValid = dsa.verify(signature);
        System.out.println("Signature verification: " + (isValid ? "✓ VALID" : "✗ INVALID"));
        
        return isValid;
    }
    
    // Save signature to file
    public static void saveSignature(byte[] signature, String fileName) throws Exception {
        FileOutputStream sigfos = new FileOutputStream(fileName);
        sigfos.write(signature);
        sigfos.close();
        System.out.println("✓ Signature saved to: " + fileName);
    }
    
    // Save public key to file
    public static void savePublicKey(PublicKey publicKey, String fileName) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        FileOutputStream keyfos = new FileOutputStream(fileName);
        keyfos.write(keyBytes);
        keyfos.close();
        System.out.println("✓ Public key saved to: " + fileName);
    }
    
    // Load public key from file
    public static PublicKey loadPublicKey(String fileName) throws Exception {
        FileInputStream keyfis = new FileInputStream(fileName);
        byte[] keyBytes = new byte[keyfis.available()];
        keyfis.read(keyBytes);
        keyfis.close();
        
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        
        System.out.println("✓ Public key loaded from: " + fileName);
        return publicKey;
    }
    
    // Load signature from file
    public static byte[] loadSignature(String fileName) throws Exception {
        FileInputStream sigfis = new FileInputStream(fileName);
        byte[] signature = new byte[sigfis.available()];
        sigfis.read(signature);
        sigfis.close();
        
        System.out.println("✓ Signature loaded from: " + fileName);
        return signature;
    }
    
    // Create test document
    public static void createTestDocument(String fileName) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        writer.write("This is a test document for digital signature.\n");
        writer.write("Content: Confidential information that needs authentication.\n");
        writer.write("Date: " + new java.util.Date() + "\n");
        writer.close();
        System.out.println("✓ Test document created: " + fileName);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("=== Digital Signature Standard Demo ===\n");
            
            Scanner sc = new Scanner(System.in);
            
            // Get document filename
            System.out.print("Enter document filename (or press Enter for test document): ");
            String fileName = sc.nextLine().trim();
            
            if (fileName.isEmpty()) {
                fileName = "test_document.txt";
                createTestDocument(fileName);
            }
            
            // Check if file exists
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("Error: File '" + fileName + "' not found!");
                return;
            }
            
            System.out.println("\n--- Key Generation ---");
            KeyPair keyPair = generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            
            System.out.println("\n--- Document Signing ---");
            byte[] signature = signDocument(fileName, privateKey);
            
            System.out.println("\n--- Saving Files ---");
            saveSignature(signature, "document.sig");
            savePublicKey(publicKey, "public.key");
            
            System.out.println("\n--- Signature Verification ---");
            // Load saved files and verify
            PublicKey loadedPublicKey = loadPublicKey("public.key");
            byte[] loadedSignature = loadSignature("document.sig");
            
            boolean isValid = verifySignature(fileName, loadedSignature, loadedPublicKey);
            
            System.out.println("\n--- Summary ---");
            System.out.println("Document: " + fileName);
            System.out.println("Signature File: document.sig");
            System.out.println("Public Key File: public.key");
            System.out.println("Signature Status: " + (isValid ? "VERIFIED ✓" : "FAILED ✗"));
            
            sc.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
