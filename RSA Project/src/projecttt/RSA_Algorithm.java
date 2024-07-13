package projecttt;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RSA_Algorithm {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger k;
    private BigInteger e;
    private BigInteger d;
    private ArrayList<String> encryptedMessage;
    private String decryptedMessage = "";

    /** Constructors */
    public RSA_Algorithm(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.k = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
        this.e = e_Generator();
        this.d = (k.add(BigInteger.ONE)).divide(e);
        this.encryptedMessage = new ArrayList<String>();
    }

    public RSA_Algorithm(List<String> list) {
        this.encryptedMessage = new ArrayList<String>(list);
        this.d = new BigInteger(this.encryptedMessage.remove(0));
        this.n = new BigInteger(this.encryptedMessage.remove(0));
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getN() {
        return n;
    }

    public ArrayList<String> getEncryptedMessage(){
        return encryptedMessage;
    }

    public String getDecryptedMessage() {
        return decryptedMessage;
    }

    public void setD(String d) {
        this.d = new BigInteger(d);
    }
    public void setN(String n) {
        this.n = new BigInteger(n);
    }
    public void encryption(String text) {
        String totalKey = d + " " + n;
        encryptedMessage.add(totalKey);
        for(char Char : text.toCharArray()) {
            encryptedMessage.add((BigInteger.valueOf(Char).modPow(e, n).toString()));
        }
    }

    public void decryption () {
        for(String Char : encryptedMessage) {
            decryptedMessage += (char) (new BigInteger(Char).modPow(d, n).intValue());
        }
    }

    public BigInteger gcd(BigInteger e, BigInteger k) {
        if(k.equals(BigInteger.ZERO))
            return e;

        return gcd(k, e.mod(k));
    }

    public BigInteger e_Generator() {
        BigInteger e = BigInteger.valueOf(2);

        if(gcd(e, k).equals(BigInteger.ONE)) {
            return e;
        } else {
            int add = 0;
            while(true) {
                add++;
                BigInteger newE = e.add(BigInteger.valueOf(add));
                if(gcd(newE, k).equals(BigInteger.ONE)) {
                    return newE;
                }
            }
        }
    }

    /** Decryption and Encryption tests*/
    public BigInteger encryptionTest(char input) {
        return (BigInteger.valueOf(input).modPow(e, n));
    }

    public char decryptionTest(BigInteger output) {
        char letter = (char) (output.modPow(d, n).intValue());
        return letter;
    }
}
