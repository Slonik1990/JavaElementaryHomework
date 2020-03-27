package Task03;

import MyUtils.Utils;

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



    //метод выводит на консоль изображение конверта с указанным пользователем размером
    //принимает коэффициент погрешности, позволяющий избежать слабой прорисковки диагонали(рекомендован 0,5)
    //для сохранения работоспособности программы при любых соотношениях сторон используется специальный коэффициент
    //if else if разбит по элементам рисунка для гибкости и читабельности


    public static void postal (double pix){
        double h = Utils.inputSize("Введите высоту конверта");
        double w = Utils.inputSize("Введите ширину конверта");
        System.out.println();
        double k = h/w;//коэффициент соотношения сторон

        for (int i = 1; i <=h ; i++) {
            for (int j = 1; j <=w; j++) {

                if(i == 1||j==1||i==h||j==w ){//прямоугольник
                    System.out.print("\t*");

                }else if(i-pix<=j*k && i+pix>=j*k){//диагональ
                    System.out.print("\t*");

                }else if(i-pix <=(w-j+1)*k && i+pix>=(w-j+1)*k){//вторая диагональ
                    System.out.print("\t*");

                }else{
                    System.out.print("\t ");
                }
            }
            System.out.println();
        }
    }

}
