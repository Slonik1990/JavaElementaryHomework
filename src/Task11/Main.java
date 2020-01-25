package Task11;


import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.util.*;


public class Main {

    public static void main(String[] args) {



        String s1 = "Hello";
        String s2 = "Hel" + "lo";
        System.out.println(s1 == s2);//true


        String s3 = new String("Hel");
        String s4 = new String("lo");
        System.out.println(s1 == s3 + s4);//false

         final String s5 = "Hel";
        final String s6 = "lo";
        System.out.println(s1 == s5 + s6);//false







    }


}


