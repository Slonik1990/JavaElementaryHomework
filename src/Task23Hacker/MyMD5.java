package Task23Hacker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMD5 {
    private char[] symbols = new String("abcdefghijklmnopqrstuvwxyz").toCharArray();

    public MyMD5() {
    }

    public MyMD5(String symbols) {
        this.symbols = symbols.toCharArray();
    }

    public String decoderMD5(String hash) {
//        генерация слова
        for (int i = 0; i < symbols.length; i++) {
            for (int j = 0; j < symbols.length; j++) {
                for (int k = 0; k < symbols.length; k++) {
                    for (int l = 0; l < symbols.length; l++) {
                        for (int m = 0; m < symbols.length; m++) {
                            for (int n = 0; n < symbols.length; n++) {


                                if (i == 0 & j == 0) {
                                    char[] letters = {symbols[k], symbols[l], symbols[m], symbols[n]};
                                    String word = new String(letters);
                                    //сравнение хэш кода
                                    String wordHash = coderMD5(word);
                                    if (wordHash.equals(hash)) {
                                        return word;
                                    }
                                }

                                if (i == 0) {
                                    char[] letters = {symbols[j], symbols[k], symbols[l], symbols[m], symbols[n]};
                                    String word = new String(letters);
                                    //сравнение хэш кода
                                    String wordHash = coderMD5(word);
                                    if (wordHash.equals(hash)) {
                                        return word;
                                    }
                                }

                                char[] letters = {symbols[i], symbols[j], symbols[k], symbols[l], symbols[m], symbols[n]};
                                String word = new String(letters);
                                //сравнение хэш кода
                                String wordHash = coderMD5(word);
                                if (wordHash.equals(hash)) {
                                    return word;
                                }

                            }
                        }
                    }
                }
            }
        }
        System.out.println("не нашел");
        return null;
    }


    public String coderMD5(String text) {
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(text.getBytes());
            for (Byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

}
