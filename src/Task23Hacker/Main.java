package Task23Hacker;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//f016441d00c16c9b912d05e9d81d894d - very
//5ebe2294ecd0e0f08eab7690d2a6ee69 - secret
//13d70e09909669272b19647c2a55dacb - goodforyou
//5f50dfa5385e66ce46ad8d08a9c9be68



public class Main {
    public static void main(String[] args) {

        String symbols = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()_-+="; // строка содержащая все символы участвующие в переборе
        String hash = "5f50dfa5385e66ce46ad8d08a9c9be68"; // хэшированный пароль
        MyMD5 myMD51 = new MyMD5(symbols, 1, 10, hash);
        MyMD5 myMD52 = new MyMD5(symbols, 11, 20, hash);
        MyMD5 myMD53 = new MyMD5(symbols, 21, 30, hash);
        MyMD5 myMD54 = new MyMD5(symbols, 31, 40, hash);



        ExecutorService executorService = Executors.newFixedThreadPool(8);

        executorService.submit(myMD51);
        executorService.submit(myMD52);
        executorService.submit(myMD53);
        executorService.submit(myMD54);









    }


}




