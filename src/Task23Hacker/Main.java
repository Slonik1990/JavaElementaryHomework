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

//4bea249142664d11a289ffdf30225a91


public class Main {
    public static void main(String[] args) {

        String symbols = "abcdefghijklmnopqrstuvwxyz"; // строка содержащая все символы участвующие в переборе
        String hash = "5ebe2294ecd0e0f08eab7690d2a6ee69"; // хэшированный пароль
        MyMD5 myMD51 = new MyMD5(symbols, 1, 7, hash);
        MyMD5 myMD52 = new MyMD5(symbols, 7, 13, hash);
        MyMD5 myMD53 = new MyMD5(symbols, 14, 20, hash);
        MyMD5 myMD54 = new MyMD5(symbols, 21, 26, hash);



        ExecutorService executorService = Executors.newFixedThreadPool(8);

        executorService.submit(myMD51);
        executorService.submit(myMD52);
        executorService.submit(myMD53);
        executorService.submit(myMD54);




//        Thread thread1 = new Thread(){
//            @Override
//            public void run() {
//                myMD5.hacker(hash, 1,1);
//            }
//        };
//        Thread thread2 = new Thread(){
//            @Override
//            public void run() {
//                myMD5.hacker(hash, 2,2);
//            }
//        };
//
//
//        thread1.start();
//        thread2.start();




    }


}




