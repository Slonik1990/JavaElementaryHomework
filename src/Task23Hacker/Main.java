package Task23Hacker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args) {
//f016441d00c16c9b912d05e9d81d894d - very
//5ebe2294ecd0e0f08eab7690d2a6ee69
//13d70e09909669272b19647c2a55dacb
        long l = System.currentTimeMillis();

        MyMD5 hacker = new MyMD5();
        System.out.println(hacker.coderMD5("homework"));
//        hacker.decoderMD5("f016441d00c16c9b912d05e9d81d894d");

        System.out.println(System.currentTimeMillis() - l);

    }




}




