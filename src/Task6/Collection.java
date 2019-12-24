package Task6;

import MyUtils.Utils;

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
 Способ реализации методов будет выбираться в аспекте, будто данная система ведет очередь пациентов на прием к доктору
 по номеру мед.карточки, а размер коллекции символизирует число пациентов, которых готов принять доктор.

 Методы будут проекцией реальных жизненных ситуаций: человек покинул очередь, человек пришел и стал в конец очереди,
 пришел человек от Василия Петровича и встал в начало очереди, привели группу людей, которых нужно обязательно принять,
 но это может быть либо срочно (метод hardAddToStart), либо их поставят в конец очереди вместо кого-то(метод hardAddingToEnd),

 По этой причине представлен широкий спектр методов связанных с добавлением:
 - добаление элемента в конец addToEnd
 - вставка элемента в начало addToStart
 - добавление коллекции в данную после записанных элементов addAllToEnd
 - вставка элементов другой коллекции перед записанными addAllToStart
 - методы дополнительно реализованы с пометкой Hard, для случаев когда недостаточно места  но принять данные важнее,
 чем сохранить имеющиеся.
 - коллекция перетягивает столько элементов, на сколько есть мест softAdding

 Думаю что данный подход можно использовать так же в IT, например как очередь на запись в базу данных
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
    public void addToEnd(int element){
        if(x<arr.length){
            arr[x] = element;
            x++;
        }else{
            System.out.println("Недостаточно места");
            return;
        }
    }

    //вставка в начало очереди
    public void addToStart(int element){
        if(x<arr.length){
            for (int i = (x-1); i>= 0; i--) {
                arr[i+1] = arr[i];
            }
            arr[0] = element;
            x++;
        }else{
            System.out.println("Недостаточно места");
            return;
        }
    }
    //если достаточно свободных ячеек, все установленные значения принимаемой коллекции добавляет в начало,
    //а все значения принимающей коллекции смещаются и распологаются за принимаемыми
    public void addAlltoStart(Collection donor){
        if(donor.arr.length>arr.length){
            System.out.println("Принимаемая коллекция больше принимающей, операция невозможна!!!");
            return;
        }
        if(donor.x>getEmptySize()){
            System.out.println("Недостаточно свободного места. Необходимо удалить элементов: " + (donor.x - getEmptySize()));
            System.out.println("Удалите элементы вручную методом remove или используйте hardAddingToEnd");
        }else{
            for (int i = x-1; i >=0; i--) {
                arr[i+donor.x]=arr[i];
            }
            for (int i = 0; i < donor.x; i++) {
                arr[i] = donor.arr[i];
            }
            x=x+donor.x;
        }
    }
    //если достаточно свободных ячеек, все установленные значения принимаемой коллекции добавлются после имеющихся
    public void addAllToEnd(Collection donor){
        if(donor.arr.length>arr.length){
            System.out.println("Принимаемая коллекция больше принимающей, операция невозможна!!!");
            return;
        }
        if(donor.x>getEmptySize()){
            System.out.println("Недостаточно свободного места. Необходимо удалить элементов: " + (donor.x - getEmptySize()));
            System.out.println("Удалите элементы вручную методом remove или используйте hardAddingToEnd");
        }else{
            for (int i = 0; i < donor.x; i++) {
                addToEnd(donor.arr[i]);
            }
        }
    }
    //добавление одной коллекции в конец другой при недостатке места
    //часть элементов принимающей коллекции перезаписываются
    public void hardAddingToEnd(Collection donor){
        String control1 = Utils.inputWord("Для подтверждения опасной операции введите RECORD");
        String control2 = "RECORD";
        if(control1.equals(control2)) {
            x = arr.length - donor.x;
            addAllToEnd(donor);
        }else{
            System.out.println("Не подтверждено");
        }
    }
    //добавление одной коллекции в конец другой при недостатке места
    //часть элементов принимающей коллекции перезаписываются
    public void hardAddToStart(Collection donor){
        String control1 = Utils.inputWord("Для подтверждения опасной операции введите RECORD");
        String control2 = "RECORD";
        if(control1.equals(control2)) {
            x=arr.length-donor.x;
            addAlltoStart(donor);
        }else{
            System.out.println("Не подтверждено");
        }
    }

    //переносит из начала принимаемой коллекции, столько элементов, на сколько есть свободных мест и выводит отчет
    public void softAdding(Collection donor){
        int elements=0;
        while (getRecordingSize()<arr.length&&donor.getRecordingSize()>0){
            addToEnd(donor.arr[0]);
            donor.remove(0);
            elements++;
        }
        System.out.println(elements);
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
