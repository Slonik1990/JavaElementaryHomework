package Task20InOut;
import java.io.File;

public class FileDemo {
    public static void main(String[] args) {

        File file = new File("src\\Task20InOut","kuku.txt");
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getFreeSpace());
        System.out.println(file.getFreeSpace());

    }
}
