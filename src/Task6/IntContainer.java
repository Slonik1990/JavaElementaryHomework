package Task6;

public class IntContainer {

    private int[] arr = new int[0];
    private int size;


    //добавление элемента
    public void add(int element) {
        int[] newArr = new int[size + 1];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        newArr[size] = element;
        arr = newArr;
        size++;
    }


    //удаление элемента перезаписью массива без удаляемого элемента
    public void remove(int index) {
        if(index<0 || index>size-1){
            System.out.println("Некорректный индекс");
            return;
        }
        int[] newArr = new int[size - 1];
        for(int i = 0;i<index;i++){
            newArr[i]=arr[i];
        }
        for (int i = index; i < newArr.length; i++) {
            newArr[i]=arr[i+1];
        }
        arr = newArr;
        size--;
    }

    public void print(){
        System.out.print("List: ");
        for(int i=0;i<size;i++){
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public boolean contains(int value){ int count = 0;
        boolean y = false;
        for (int el : arr) {
            if(el==value){
                count++;
                y=true;
            }
        }
        if(count>1){
            System.out.println("Обнаружено " +count+ " элементов с указанным значениеим");
        }
        return y;
    }

    //возвращает величину контейнера
    public int getSize() {

        return size;
    }

    //возвращает элемент по индексу ячейки
    public int getElement(int index) {
        if(index<size && index>=0)return arr[index];
        else throw new IndexOutOfBoundsException();
    }

    //слияние контейнеров
    public void addAll(IntContainer donor){
        int [] newArr = new int[size+donor.getSize()];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        for (int i = size; i < newArr.length; i++) {
            newArr[i]=donor.getElement(i-size);
        }

        arr = newArr;
        size = size+donor.getSize();
    }

    //контейнер принимает массив
    public void addAll(int[] donor){
        int [] newArr = new int[size+donor.length];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        for (int i = size; i < newArr.length; i++) {
            newArr[i]=donor[i-size];
        }

        arr = newArr;
        size = size+donor.length;
    }

    //сравнение коллекций
    public boolean equalToOtherList(IntContainer otherList){
        if(otherList==null){
            return false;
        }
        if(this.getSize()!=otherList.getSize()){
            return false;
        }
        for(int i=0;i<getSize();i++){
            if(this.getElement(i)!=otherList.getElement(i)){
                return false;
            }
        }
        return true;
    }


    public int findIndex(int value){
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if(this.arr[i]==value)return i;
        }
        return index;
    }

    public void clear(){
        this.arr = new int[0];
        this.size = 0;
        System.out.println("Контейнер очицен");
    }

    //сортировка пузырьком основана на попарном сравнении элементов и смещении большего в конец списка
    public  void bubbleSort() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize()-1; j++) {
                if (arr[j] > arr[j+1]){
                    swap(j, j+1);
                }
            }
        }
    }


    //сортировка  методом перебора на убывание (пузырек наоборот)
    public void downSort(){
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize()-1; j++) {
                if(arr[j+1]>arr[j]) {
                    swap(j, j+1);
                }
            }
        }
    }

    //меняет элементы с принимаемыми индексами местами
    private   void swap (int ind1, int ind2){
        int buffer = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = buffer;
    }


}
