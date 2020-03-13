package Task20InOut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//       C:\Users\HP\IdeaProjects\New Level\src\Task20InOut\Student.txt

        int i = 0;
        FileInputStream inputStream = null;
        FileOutputStream outputStreamStream = null;


        try {
            inputStream = new FileInputStream("src\\Task20InOut\\Student.txt");
            outputStreamStream = new FileOutputStream("src\\Task20InOut\\kuku.txt", false);
           while ((i = inputStream.read())!=-1){
               outputStreamStream.write(i);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
