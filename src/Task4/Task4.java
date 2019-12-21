package Task4;
import MyUtils.Utils;


public class Task4 {
    public static void main(String[] args) {
        inputPoints();

    }

    public static void inputPoints() {
        System.out.println("Введите координаты верхнего левого угла");
        int x1 = Utils.inputInt("X");
        int y1 = Utils.inputInt("Y");
        System.out.println("Введите координаты нижнего правого угла");
        int x2 = Utils.inputInt("X");
        int y2 = Utils.inputInt("Y");
        rectangleControl(x1, y1, x2, y2);
        System.out.println("Введите координаты точки на плоскости");
        int xPoint = Utils.inputInt("X");
        int yPoint = Utils.inputInt("Y");
        truePoint(x1, y1, x2, y2, xPoint, yPoint);
    }
    //проверка координат точки на принадлежность прямоугольнику
    public static void truePoint(int x1, int y1, int x2, int y2, int x, int y) {
        if(x>=x1 && x<=x2 && y <=y1 && y>=y2) {
            System.out.println("Данная точка принадлежит прямоугольнику");
        } else {
            System.out.println("Данная точка расположена вне границ прямоугольника");
        }
    }

    //метод проверяет возможность построения прямоугольника с такими координатами углов
    public static void rectangleControl(int x1, int y1, int x2, int y2){
        if(x2>x1&&y2<y1){
            System.out.println("Прямоугольник построен");
        }else {
            System.out.println("Построить прямоугольник невозможно. Повторите ввод координат");
            inputPoints();
        }
    }


}
