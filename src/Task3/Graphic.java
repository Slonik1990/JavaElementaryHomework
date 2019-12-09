package Task3;

public class Graphic {

    //метод вывод на консоль прямоугольник с указанными пользователем размерами
    public static void rectangle(){
        int w = Utils.inputSize("Введите ширину прямоугольника");
        int h = Utils.inputSize("Введите высоту прямоугольника");
        System.out.println();
        for (int i = 1; i <=h ; i++) {
            for (int j = 1; j <=w; j++) {
                if(i == 1||j==1||i==h||j==w){
                    System.out.print("*");
                } else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    //выводит * в шахматном порядке
    public static void chess() {
        int w = Utils.inputSize("Введите ширину шахматного поля");
        int h = Utils.inputSize("Введите высоту шахматного поля");
        System.out.println();
        for (int i = 1; i <=h ; i++) {
            for (int j = 1; j <=w; j++) {
                if((i%2==1 && j%2==1)||(i%2==0 && j%2==0)){
                    System.out.print("*");
                } else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }


    //метод вывод на консоль изображение конверта с указанным пользователем размером
    public static void postal(){
        int size = Utils.inputSize("Введите размер конверта");
        System.out.println();
        for (int i = 1; i <=size ; i++) {
            for (int j = 1; j <=size; j++) {
                if(i == 1||j==1||i==size||j==size||i==j||i+j==size+1){
                    System.out.print("\t*");
                } else{
                    System.out.print("\t ");
                }
            }
            System.out.println();
        }
    }



}
