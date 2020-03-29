package Task23Hacker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//f016441d00c16c9b912d05e9d81d894d - very
//5ebe2294ecd0e0f08eab7690d2a6ee69 - secret
//13d70e09909669272b19647c2a55dacb
//5f50dfa5385e66ce46ad8d08a9c9be68

public class Main {
    public static void main(String[] args) {

        Thread myThread1 = new Thread("a"){
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                MyMD5 hak = new MyMD5("abcdefghijklmnopqrstuvwxyz");
                System.out.println("Распознано слово: " + hak.decoderMD5("5ebe2294ecd0e0f08eab7690d2a6ee69"));
                System.out.println(System.currentTimeMillis() - l);
                System.out.println(getName());
                System.exit(0);
            }
        };

        Thread myThread2 = new Thread("b"){
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                MyMD5 hak = new MyMD5("zyxwvutsrqponmlkjihgfedcba");
                System.out.println("Распознано слово: " + hak.decoderMD5("5ebe2294ecd0e0f08eab7690d2a6ee69"));
                System.out.println(System.currentTimeMillis() - l);
                System.out.println(getName());
                System.exit(0);
            }
        };






        myThread1.start();
        myThread2.start();


    }


}




