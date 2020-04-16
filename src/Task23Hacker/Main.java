package Task23Hacker;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


//f016441d00c16c9b912d05e9d81d894d - very
//5ebe2294ecd0e0f08eab7690d2a6ee69 - secret
//13d70e09909669272b19647c2a55dacb - goodforyou
//5f50dfa5385e66ce46ad8d08a9c9be68
//


public class Main {
    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {

        String symbols = "abcdefghijklmnopqrstuvwxyz"; // строка содержащая все символы участвующие в переборе
        String hash = "f016441d00c16c9b912d05e9d81d894d"; // хэшированный пароль
        MyMD5 myMD51 = new MyMD5(symbols, 1, 13, hash);
        MyMD5 myMD52 = new MyMD5(symbols, 14, 26, hash);
        MyMD5 myMD53 = new MyMD5(symbols, 27, 39, hash);
        MyMD5 myMD54 = new MyMD5(symbols, 40, 52, hash);



        ExecutorService executorService = Executors.newFixedThreadPool(8);


        executorService.submit(myMD51);
        executorService.submit(myMD52);
        executorService.submit(myMD53);
        executorService.submit(myMD54);






    }


}




