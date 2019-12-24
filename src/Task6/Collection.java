package Task6;
/**Одним из ключевых аспектов при выборе подхода к реализации данной задачи, является то, что 0 может означать как
 установленное значение, так и дефолтное. Рассмотрел несколько вариантов решения:

 1) Создание сопутствующего массива boolean переменных
 2) Создание поля-счетчика

 Если в коллекции номер ячейки тоже будет нести в себе некую информативность, например значение элемента массива -
 сумма затрат, индекс элемента - число месяца, при таком подходе необходим  boolean массив который на основе логики
 программы будет взаимодействовать с массивом int и определит что значит 0 для элемента: я ничего не потратил(true) или
 не внес данные(false).

 Я выбрал иной подход, с использованием поля-счетчика:
 Система будет реализована по приницпу очереди, все свободные элементы массива будут располагаться в конце,
 а элементы с установленными значениями - в начале.
 Удаление элемента будет сдвигать очередь вперед, а добавление будет производиться в конец очереди.
 Я думаю что в backend данная реализация вполне может пригодиться. Например как очередь на запись в базу данных.

 */
public class Collection {
    private int[]arr;
    private int x;// счетчик записанных элементов



    //конструктор
    public Collection(int elements) {
        this.arr = new int[elements];
        x = 0;
    }


    //добавляет элемент следующим после всех объявленных
    public void add(int element){
        if(x<arr.length){
            arr[x] = element;
            x++;
        }else{
            System.out.println("Недостаточно места");
            return;
        }

    }



    //все следующие элементы от принимаемого индекса смещаются на 1 в сторону начала массива, тем самым удаляемый элемент замещается
    //последний элемент коллекции дублируется, т.к. его изначальное место не замещается, но это не влияет на работу программы
    //т.к. ячейка которую он занимал воспринимается как ячейка  с неустановленным значением за счет декрементации счетчика
    public void remove(int index){
        if(index>=arr.length){
            System.out.println("Выход за рамки коллекции");
            return;
        }
        for (int i = index; i < arr.length-1; i++) {
            arr[i]=arr[i+1];
        }
       x--;
    }

    //возвращает значение элемента с таким индексом
    public int getValue(int index){
        return arr[index];
    }

    //присвоено ли данному элементу массива значение
    public boolean isEmpty(int index){
        if(index>=arr.length){
            System.out.println("Выход за рамки коллекции");
        }
        boolean a = false;
        if(x>index){
            a=true;
        }
        return a;
    }

    //проверяет в массиве наличие элемента с принимаемым значением
    public boolean contains(int value){
        boolean a = false;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==value){
                a = true;
                break;
            }
        }
        return a;
    }

    //принимает int и проверяет значения элементов массива на соответствие
    // выводит на экран индескы подходящих, либо сообщение об отсутствии соответствия
    public void indexOf(int value){
        if(contains(value)){
            for (int i = 0; i < x; i++) {
                if(arr[i]==value){
                    System.out.println("Данному значению соответствует элемент с индексом: " + i);
                }
            }
        }else{
            System.out.println("Нет элементов с таким значением");
        }
    }

    //возвращает размер коллекции
    public int getSize(){
        return arr.length;
    }

    //возвращает количество записанных элементов
    public int getRecordingSize(){
        return x;
    }

    //возвращает количество свободных ячеек
    public int getEmptySize(){
        return arr.length - x;
    }

    //полное удаление всей записанной информации
    public void hardClear(){
        for (int i = 0; i < arr.length; i++) {
            arr[i]=0;
        }
        x=0;
    }

    //обнуление счетчика, элементы массива воспринимаются программой как свободные и будут перезаписаны.
    //До тех пор пока новые значения не займут данные элементы, их возможно считать
    public void softClear(){
        x=0;
    }

    //если достаточно свободных ячеек, все установленные значения принимаемой коллекции добавляет в коллекцию.
    //Метод записывающий информацию с другой коллекции ценой потери данных основной коллекции разрабатывать не стал
    //пусть настолько опасную операцию пользователь производит вручную методом remove
    public void addAll(Collection donor){
        if(donor.x>getEmptySize()){
            System.out.println("Недостаточно свободного места. Необходимо удалить элементов: " + (donor.x - getEmptySize()));
        }else{
            for (int i = 0; i < donor.x; i++) {
                add(donor.arr[i]);
            }
        }
    }

    //не строгое сравнение, при котором  сравнивается записанное содержимое
    public boolean valueEquals(Collection numbers){
        boolean eq = false;
        if(getRecordingSize() == numbers.getRecordingSize()){
            for (int i = 0; i < x; i++) {
                if(arr[i]!=numbers.arr[i]){
                    eq = false;
                    break;
                }else{
                    eq = true;
                }
            }
        }
        return eq;
    }

    //строгое сравнение коллекций по содержимому и размеру
    public boolean equals(Collection numbers){
        boolean eq = false;
        if(getRecordingSize() == numbers.getRecordingSize()&&arr.length==numbers.arr.length){
            for (int i = 0; i < x; i++) {
                if(arr[i]!=numbers.arr[i]){
                    eq = false;
                    break;
                }else{
                    eq = true;
                }
            }
        }
        return eq;
    }

    //выводит на печать объявленные элементы коллекции
    public void print(){
        if(x>0){
            for (int i = 0; i < x; i++) {
                System.out.print("[" + arr[i]+ "] ");
            }
        }else{
            System.out.println("Коллекция пуста");
        }
        System.out.println();
    }

    //сортировка пузырьком основана на попарном сравнении элементов и смещении большего в конец списка
    public  void bubbleSort() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x-1; j++) {
                if (arr[j] > arr[j+1]){
                    swap(j, j+1);
                }
            }
        }
    }


    //сортировка  методом перебора на убывание (пузырек наоборот)
    public void downSort(){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x-1; j++) {
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

    public void test(){
        if((getEmptySize()+ getRecordingSize())==getSize()){
            System.out.println("Норм");
        }
    }


}
