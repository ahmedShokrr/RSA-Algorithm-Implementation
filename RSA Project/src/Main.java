import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        projecttt.RSA_Algorithm rsa;
        do {
            rsa = new projecttt.RSA_Algorithm(primeNumberGenerator(), primeNumberGenerator());
        } while(rsa.decryptionTest(rsa.encryptionTest('a')) != 'a');

        System.out.println("(1) Encrypt file.");
        System.out.println("(2) Decrypt file.");
        System.out.print("Select (1) or (2) : ");
        String choice = console.next();
        if(choice.equals("1")) {
            encryptFile(rsa, console);
        } else if(choice.equals("2")) {
            decryptFile(console);
        }
    }

    /** encryption method */
    public static void encryptFile(projecttt.RSA_Algorithm rsa, Scanner console) {
        System.out.print("Enter the Path of the file: ");
        String filePath = console.next();
        try {
            Scanner reader = new Scanner(new File(filePath));
            String text = "";
            while(reader.hasNext()) {
                text += reader.nextLine();
            }
            reader.close();

            rsa.encryption(text);

            PrintWriter writer = new PrintWriter(new File("C:\\Users\\shokr\\Desktop\\Ecrypted_File.txt"));
            //writer.print(rsa.getD() + " " + rsa.getN());
            //writer.println();

            for(String Char: rsa.getEncryptedMessage()) {
                writer.print(Char + " ");
            }
            writer.close();
        } catch(FileNotFoundException ex) {
            System.out.println("File Not Found.");
        } catch(IOException ex) {
            ex.toString();
        }
    }

    public static void decryptFile(Scanner console) {
        System.out.print("Enter The Path of the file: ");
        String path = console.next();

        try {
            Scanner reader = new Scanner(new File(path));
            PrintWriter writer = new PrintWriter("C:\\Users\\shokr\\Desktop\\Decrypted_File.txt");

            String text = "";
            while(reader.hasNext()) {
                text += reader.nextLine();
            }
            reader.close();

            projecttt.RSA_Algorithm rsa = new projecttt.RSA_Algorithm(Arrays.asList(text.split(" ")));
            rsa.decryption();
            writer.print(rsa.getDecryptedMessage());
            writer.close();
        } catch(FileNotFoundException ex) {
            System.out.println("File Not Found.");
        } catch(IOException ex) {
            ex.toString();
        }
    }

    /** Prime number generator method*/
    public static BigInteger primeNumberGenerator() {
        Random random = new Random();
        // Generate random number with range 2048 digit
        BigInteger num = new BigInteger(2024, random);

        // if this number is prime the number will return, else num++ until be prime.
        if(isPrime(num)) {
            return num;
        } else {
            int number = 0;
            while(true) {
                number++;
                BigInteger newNum = num.add(BigInteger.valueOf(number));
                if(isPrime(newNum)) {
                    return newNum;
                }
            }
        }

    }

    // Helper Method. Check if The BigInteger is prime or not.
    public static boolean isPrime(BigInteger n) {
        int numOfTests = 100; // number of trials

        if(n.mod(BigInteger.TWO).equals(0))
            return false;

        for(int i = 1; i <= numOfTests; i++) {
            if(millerTest(n) == false) {
                return false;
            }
        }
        return true;
    }
    // Another helper method called millerTest that use Set 3(Miller-Rabin) Algorithm
    private static boolean millerTest(BigInteger n) {
        Random random = new Random();

        // Ensures that temp > 1 and temp < n
        BigInteger temp = BigInteger.ZERO;

        do {
            temp = new BigInteger(n.bitLength()-1, random);
        } while(temp.compareTo(BigInteger.ONE) <= 0);

        // Screen out n if our random number happens to share a factor with n.
        if(!n.gcd(temp).equals(BigInteger.ONE))
            return false;

        BigInteger base = n.subtract(BigInteger.ONE);

        // figure out the largest power of two that divides evenly into n-1.
        int k = 0;
        while((base.mod(BigInteger.TWO)).equals(BigInteger.ZERO)) {
            base = base.divide(BigInteger.TWO);
            k++;
        }

        BigInteger curValue = temp.modPow(base, n);

        // if this works out, we just say it is prime.
        if(curValue.equals(BigInteger.ONE))
            return true;

        // otherwise, we will check to see if this value successively squared ever yields -1.
        for(int i = 0; i < k; i++) {

            // Check n-1 is equivalent to -1.
            if(curValue.equals(n.subtract(BigInteger.ONE)))
                return true;
                // Square this previous number.
            else
                curValue = curValue.modPow(BigInteger.TWO, n);
        }

        return false;
    }

}


