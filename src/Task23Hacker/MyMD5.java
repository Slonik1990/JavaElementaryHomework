package Task23Hacker;


import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.ls.LSOutput;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

public class MyMD5 implements Runnable {
    private char[] symbols;
    private int from;
    private int to;
    private String hash;



    public MyMD5(String symbols, int from, int to, String hash) {
        this.symbols = symbols.toCharArray();
        this.from = from;
        this.to = to;
        this.hash = hash;
    }

    @Override
    public void run() {
        hacker();
    }


    public void hacker (){
        long start = System.currentTimeMillis();
        boolean status;

        status = decoder4(hash, from, to);
        if(!status){
            status = decoder5(hash, from, to);
        }
        if(!status){
            status = decoder6(hash, from, to);
        }
        if(!status){
            status = decoder7(hash, from, to);
        }
        if(!status){
            status = decoder8(hash, from, to);
        }
        System.out.println(Thread.currentThread().getName() +"." +
                "; TOTAL TIME " + (System.currentTimeMillis() - start));
        System.exit(0);
    }

    public boolean decoder4(String hash, int from, int to) {
        long start = System.currentTimeMillis();
        for (int i = from - 1; i < to; i++) {
            for (int j = 0; j < symbols.length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    for (int l = 0; l < symbols.length; l++) {

                        char[] letters = {symbols[i], symbols[j], symbols[k], symbols[l]};
                        String word = new String(letters);
                        String wordHash = coderApache(word);
                        if (wordHash.equals(hash)) {
                            System.out.println("DECRYPTED WORD: " + word);
                            return true;
                        }

                    }
                }
            }
        }
        System.out.println("4-character combinations finished - time: " + (System.currentTimeMillis() - start));
        return false;
    }

    public boolean decoder5(String hash, int from, int to) {
        long start = System.currentTimeMillis();
        for (int i = from - 1; i < to; i++) {
            for (int j = 0; j < symbols.length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    for (int l = 0; l < symbols.length; l++) {
                        for (int m = 0; m < symbols.length; m++) {

                            char[] letters = {symbols[i], symbols[j], symbols[k], symbols[l], symbols[m]};
                            String word = new String(letters);
                            String wordHash = coderApache(word);
                            if (wordHash.equals(hash)) {
                                System.out.println("DECRYPTED WORD: " + word);
                                return true;
                            }

                        }
                    }
                }
            }
        }
        System.out.println("5-character combinations finished - time: " + (System.currentTimeMillis() - start));
        return false;
    }

    public boolean decoder6(String hash, int from, int to) {
        long start = System.currentTimeMillis();
        for (int i = from - 1; i < to; i++) {
            for (int j = 0; j < symbols.length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    for (int l = 0; l < symbols.length; l++) {
                        for (int m = 0; m < symbols.length; m++) {
                            for (int n = 0; n < symbols.length; n++) {
                                char[] letters = {symbols[i], symbols[j], symbols[k], symbols[l], symbols[m], symbols[n]};
                                String word = new String(letters);
                                String wordHash = coderApache(word);
                                if (wordHash.equals(hash)) {
                                    System.out.println("DECRYPTED WORD: " + word);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("6-character combinations finished - time: " + (System.currentTimeMillis() - start));
        return false;
    }

    public boolean decoder7(String hash, int from, int to) {
        long start = System.currentTimeMillis();
        for (int i = from - 1; i < to; i++) {
            for (int j = 0; j < symbols.length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    for (int l = 0; l < symbols.length; l++) {
                        for (int m = 0; m < symbols.length; m++) {
                            for (int n = 0; n < symbols.length; n++) {
                                for (int o = 0; o < symbols.length; o++) {
                                    char[] letters = {symbols[i], symbols[j], symbols[k], symbols[l], symbols[m], symbols[n], symbols[n]};
                                    String word = new String(letters);
                                    String wordHash = coderApache(word);
                                    if (wordHash.equals(hash)) {
                                        System.out.println("DECRYPTED WORD: " + word);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("7-character combinations finished - time: " + (System.currentTimeMillis() - start));
        return false;
    }

    public boolean decoder8(String hash, int from, int to) {
        long start = System.currentTimeMillis();
        for (int i = from - 1; i < to; i++) {
            for (int j = 0; j < symbols.length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    for (int l = 0; l < symbols.length; l++) {
                        for (int m = 0; m < symbols.length; m++) {
                            for (int n = 0; n < symbols.length; n++) {
                                for (int o = 0; o < symbols.length; o++) {
                                    for (int p = 0; p < symbols.length; p++) {
                                        char[] letters = {symbols[i], symbols[j], symbols[k], symbols[l], symbols[m], symbols[n], symbols[o], symbols[p]};
                                        String word = new String(letters);
                                        String wordHash = coderApache(word);
                                        if (wordHash.equals(hash)) {
                                            System.out.println("DECRYPTED WORD: " + word);
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("8-character combinations finished - time: " + (System.currentTimeMillis() - start));
        return false;
    }


    //различные реализации кодировщиков
    private static String coderApache(String text) {
        return DigestUtils.md5Hex(text);
    }





}
