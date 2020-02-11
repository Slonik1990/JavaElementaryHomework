package Task15Map;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Set;

public class MyMapTest {


    //проверяем пустой конструктор сравнивая длину таблицы, вместимость и константный минимум
    @Test
    public void constructorTest() {
        MyMap my = new MyMap();
        boolean tableInizialization = my.getTable().length == my.getCapacity();
        boolean capacity = my.getCapacity() == MyMap.minCapacity();
        Assert.assertTrue(tableInizialization && capacity);
    }


    //тест конструктора принимающего размер таблицы при передаче некорректных значений
    @Test
    public void constructorTest_capacityLowerThanMin() {
        MyMap my = new MyMap(-3);
        Assert.assertEquals(my.getTable().length, MyMap.minCapacity());
    }

    @Test
    public void constructorTest_capacityBiggerThanMax() {
        MyMap my = new MyMap(1000000000);
        Assert.assertEquals(my.getTable().length, MyMap.maxCapacity());
    }


    //проверка генератора индексов
    //если в какой то ситуации сгенерируется некорректный индекс, correct установится на false
    @Test
    public void getIndexTest_correctlyRange() {
        MyMap my = new MyMap();
        boolean correct = true;
        int indexBorder = my.getCapacity() - 1;
        int index;
        for (int i = 0; i < 1000; i = i + 3) {
            index = my.getIndex(i);
            if (index < 0 || index > indexBorder) {
                correct = false;
            }
        }
        Assert.assertTrue(correct);

    }

    //пока что не удалось придумать последовательность тестов, при которой не применялись бы непротестированные методы
    //в проверке isEmpty() и size() применится не протестированный put, при содействии дебагера
    @Test
    public void isEmptytest() {
        MyMap my = new MyMap();
        boolean before = my.isEmpty();
        my.justPut(1, 1);
        boolean after = my.isEmpty();
        Assert.assertNotSame(before, after);
    }

    @Test
    public void sizeTest_atEmptyMap() {
        MyMap my = new MyMap();
        Assert.assertEquals(my.size(), 0);
    }

    @Test
    public void sizeTest_afterAdding() {
        MyMap my = new MyMap();
        my.justPut(1, 1);
        my.justPut(2, 2);
        int before = my.size();
        my.justPut(3, 3);
        int after = my.size();
        Assert.assertEquals(before + 1, after);
    }


    //при добавлении ключа null ожидаем NullPointerException и отсутствие записи
    @Test(expected = NullPointerException.class)
    public void putTest_nullKey() {
        MyMap my = new MyMap();
        my.justPut(null, 1);
        Assert.assertTrue(my.isEmpty());
    }

    //в данной имплементации допускается null в качестве значения
    @Test
    public void putTest_nullValue() {
        MyMap my = new MyMap();
        my.justPut(1, null);
        Assert.assertFalse(my.isEmpty());
    }

    //в данной имплементации нет ограничения на различные типы ключей
    @Test
    public void putTest_DifferentTypes() {
        MyMap my = new MyMap();
        my.justPut(1, 1);
        my.justPut("one", "один");
        my.justPut(true, true);
        my.justPut(new Object[10], null);
        Assert.assertEquals(my.size(), 4);
    }

    //одинаковый ключ перезапишет значение, количество записей не изменится
    @Test
    public void putTest_SameKeys() {
        MyMap my = new MyMap();
        my.justPut(1, 1);
        int before = my.size();
        my.justPut(1, "один");
        int after = my.size();
        Assert.assertEquals(before, after);
    }

    //проверка метода, являющегося индикатором для расширения при различных ситуациях
    @Test
    public void needIncreaseTest_emptyMap() {
        MyMap empty = new MyMap();
        Assert.assertFalse(empty.needIncrease());
    }

    @Test
    public void needIncreaseTest_maxSizeEmptyMap() {
        MyMap bigBrother = new MyMap(1000000);
        Assert.assertFalse(bigBrother.needIncrease());
    }

    @Test
    public void needIncreaseTest_overloadingMoment() {
        MyMap my = new MyMap();
        for (int i = 1; i <= 6; i++) {
            my.justPut(i, null);
        }
        boolean before = my.needIncrease();
        my.justPut(7, null);
        boolean after = my.needIncrease();
        Assert.assertNotSame(before, after);
    }

    @Test
    public void needIncreaseTest_maxSizeOverloadedMap() {
        MyMap my = new MyMap(16384);
        for (int i = 1; i <= 30000; i++) {
            my.put(i, null);
        }
        System.out.println((double) my.size() / (double) my.getCapacity());//1.83
        Assert.assertFalse(my.needIncrease());//не разрешает увеличить из за достижения максимального размера
    }


    //единоразовое увеличение должно увеличить размер таблицы вдвое
    @Test
    public void increaseTest_single() {
        MyMap my = new MyMap();
        int before = my.getCapacity();
        my.increase();
        int after = my.getCapacity();
        Assert.assertTrue(before == after / 2);

    }

    //циклическое увеличение должно прийти к максимально возможному размеру при любом исходном
    @Test
    public void increaseTest_loop() {
        int random = (int) (Math.random() * MyMap.maxCapacity()); //[0, maxCapacity)

        MyMap my = new MyMap(random);
        for (int i = 1; i <= 30000; i++) {
            my.justPut(i, null);
        }
        while (my.needIncrease()) {
            my.increase();
        }
        int result = my.getCapacity();
        Assert.assertEquals(result, MyMap.maxCapacity());
    }

    //выше протестированы все блоки из которых состоит putAll, поэтому будет только один маленький тестик на всякий случай
    //часть ключей у двух мап идентичны, поэтому я расчитываю на перезапись совпадающего диапазона
    @Test
    public void putAllTest() {
        MyMap my = new MyMap();
        for (int i = 1; i <= 20; i++) {
            my.put(i, null);
        }

        MyMap another = new MyMap();
        for (int i = 11; i <= 30; i++) {
            another.put(i, i);
        }
        my.putAll(another);
        Assert.assertTrue(my.size() == 30);
    }

    //тест для значения которое возвращает get и дополнительная проверка перезаписи элементов с одинаковым ключем
    @Test
    public void getTest_ValueByKey() {
        MyMap my = new MyMap();
        for (int i = 1; i <= 20; i++) {
            my.put(i, null);
        }

        MyMap another = new MyMap();
        for (int i = 11; i <= 30; i++) {
            another.put(i, i);
        }
        my.putAll(another);
        Assert.assertEquals(my.get(1), null);
        Assert.assertEquals(my.get(21), 21);
        Assert.assertEquals(my.get(31), null);//обработка несуществующего ключа

    }

    //ожидается exception при попытке получить значение по ключу null
    @Test(expected = NullPointerException.class)
    public void getTest_nullKey() {
        MyMap my = new MyMap();
        my.get(null);

    }

    //специально сделаем карту перенасыщенной, чтобы удаление происходило как из списков так и из одиночных бакетов
    @Test
    public void removeTest() {
        MyMap my = new MyMap();
        for (int i = 1; i <= 7; i++) {
            my.justPut(i, i);
        }

        Object returned = my.remove(1);

        for (int i = 2; i <= 7; i++) {
            my.remove(i, i);
        }

        Assert.assertEquals(returned, 1);
        Assert.assertTrue(my.isEmpty());
    }

    @Test
    public void clearTest() {
        MyMap my = new MyMap();
        for (int i = 1; i <= 10; i++) {
            my.put(i, i);
        }
        my.clear();
        Assert.assertTrue(my.isEmpty());
        Assert.assertTrue(my.getCapacity() == MyMap.minCapacity());

    }

    //проверка конвертирования в коллекцию или сет через сравнение количества записей
    @Test
    public void mapTest_toCollections(){
        MyMap map = new MyMap();
        for (int i = 1; i <= 100; i++) {
            if ( i% 2 == 1){
                map.put(i, i);
            }else{
                map.put(i, null);

            }
        }
        int elements = map.size();

        Collection values = map.values();
        Set keys = map.keySet();
        Set entries = map.entrySet();

        Assert.assertEquals(values.size(), elements);
        Assert.assertEquals(keys.size(), elements);
        Assert.assertEquals(entries.size(), elements);

    }

    //тест обнаружения значения
    @Test
    public void containsValueTest(){
        MyMap map = new MyMap();
        map.put(1, 1);
        map.put(2, "два");
        map.put(3, null);
        Assert.assertTrue(map.containsValue(1));
        Assert.assertTrue(map.containsValue("два"));
        Assert.assertTrue(map.containsValue(null));
        Assert.assertFalse(map.containsValue("три"));

    }

    //тест обнаружения ключа
    @Test
    public void containsKeyTest(){
        MyMap map = new MyMap();
        map.put(1, 1);
        map.put(2, "два");
        map.put(3, null);
        Assert.assertTrue(map.containsKey(1));
        Assert.assertTrue(map.containsKey(2));
        Assert.assertTrue(map.containsKey(3));
        Assert.assertFalse(map.containsKey(4));

    }

    //ожидаемое исключение при попытке проверить наличие ключа null
    @Test(expected = NullPointerException.class)
    public void containsKeyTest_NullKey(){
        MyMap map = new MyMap();
        map.containsKey(null);
    }
}



