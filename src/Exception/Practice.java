package Exception;


public class Practice {
    public static void main(String[] args) {
        try {
            loop();
        } catch (Exception e) {
            System.out.println(50);
        }

    }

    public static void loop(){
        for (int i = 0; i < 10; i++) {
            if(i==5){
                throw new IllegalArgumentException();
            }
            System.out.print(i);
        }
    }
}
