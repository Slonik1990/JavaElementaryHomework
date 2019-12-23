package MyUtils;

public class ArrayUtilits {

    //метод arrayMin возвращает наименьший элемент массива
    // благодаря реализации перегрузки может принимать массив с целыми или дробными числами
    public static int arrayMin (int [] arr){
        int min = arr [0];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < min) {
                min = arr[i];
            }
        return min;
    }
    public static double arrayMin (double [] arr) {
        double min = arr[0];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < min) {
                min = arr[i];
            }
        return min;
    }


    //метод arrayMax возвращает наибольший элемент массива
    // благодаря реализации перегрузки может принимать массив с целыми или дробными числами
    public static int arrayMax (int [] arr){
        int max = arr [0];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > max) {
                max = arr[i];
            }
        return max;
    }
    public static double arrayMax (double [] arr) {
        double max = arr[0];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > max) {
                max = arr[i];
            }
        return max;
    }

    //индекс минимального элемента массива
    public static int arrayMinIndex (int [] arr){
        int minIndex = 0;
        int min = arr [minIndex];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        return minIndex;
    }
    public static int arrayMinIndex (int [] arr, int start){
        int minIndex = start;
        int min = arr [start];
        for (int i = start; i < arr.length; i++)
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        return minIndex;
    }


    //индекс максимального элемента массива
    public static int arrayMaxIndex (int [] arr){
        int maxIndex = 0;
        int max = arr [maxIndex];
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;

            }
        return maxIndex;
    }
    public static int arrayMaxIndex (int [] arr, int start){
        int maxIndex = start;
        int max = arr [start];
        for (int i = start; i < arr.length; i++)
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        return maxIndex;
    }



    //сравнение массивов
    public static boolean sameArrays(int[]a, int[]b) {
        boolean same =true;
        if (a.length != b.length) {
            same = false;
        } else{
            for (int i = 0; i < a.length; i++) {
                if(a[i] != b[i]){
                    same = false;
                    break;
                } else{
                    same = true;
                }
            }
        }
        return same;
    }


    //слияние двух массивов
    public static int[] arrayAdd(int[] a, int[] b){
        int[] c = new int[a.length+b.length];
        for (int i = 0; i < c.length; i++) {
            if(i<a.length){
                c[i]=a[i];
            }else{
                c[i]=b[i-a.length];
            }
        }
        return c;
    }
    public static char[] arrayAdd(char[] a, char[] b){
        char[] c = new char[a.length+b.length];
        for (int i = 0; i < c.length; i++) {
            if(i<a.length){
                c[i]=a[i];
            }else{
                c[i]=b[i-a.length];
            }
        }
        return c;
    }


    //метод выводит на экран элементы массива, реализована перегрузка
    public static void printArray (int[]arr){
        for(int i: arr){
            System.out.printf("[%d] ", i);
        }
        System.out.println();
    }
    public static void printArray (double[]arr){
        for(double i: arr){
            System.out.printf("[%f] ", i);
        }
        System.out.println();
    }
    public static void printArray (char[]arr){
        for(char i: arr){
            System.out.printf("[%s] ", i);
        }
        System.out.println();
    }

    //метод перемешивает массив
    public static void arrayChaos (char[] arr){
        for (int i = 0; i < arr.length ; i++) {
            int a = (int) (Math.random()*arr.length);
            char c = arr[a];
            arr[a] = arr [i];
            arr[i] = c;
        }
    }

    //принимает массив и меняет элементы с принимаемыми индексами местами
    public static void swap (int[]arr, int ind1, int ind2){
        int buffer = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = buffer;

    }

    //упорядочивает массив по убыванию
    public static void sortToMin(int[]arr){
        for (int i = 0; i < arr.length; i++) {
            ArrayUtilits.swap(arr, i, arrayMaxIndex(arr, i));
        }
    }

    //упорядочивает массив по возрастанию
    public static void sortToMax(int[]arr){
        for (int i = 0; i < arr.length; i++) {
            ArrayUtilits.swap(arr, i, arrayMinIndex(arr, i));
        }
    }

    //сортировка пузырьком основана на попарном сравнении элементов и смещении большего в конец списка
    //внутренний цикл пошагово смещает больший элемент в конец массива
    //внешний цикл запускает внутренний столько раз, сколько элементов в массиве
    public static void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length-1; j++) {
                if (numbers[j] > numbers[j+1]){
                    ArrayUtilits.swap(numbers, j, j+1);
                }
            }
        }
    }

}