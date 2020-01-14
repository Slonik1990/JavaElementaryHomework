package Task9;

//Для полноценной реализации нужно использовать инструмент позволяющий производить операции с числами значительно
//превышающий long - это BigInteger
//Для реализации простого калькулятора чисел Фибоначчи на обывательском уровне, например для смартфона я бы выбрал
//обычный цикл без кэширования,скорость вычисления вполне отличная и ни кчему засорять память.

//Сугубо для практики реализую в стиле ООП калькулятор, который очень часто используется в научных расчетах
// и число которое нужно расчитать постоянно растет и скорость гораздо важнее памяти.


import Task8.MyContainer;
import java.math.BigInteger;

public class Fibonachi {
    private BigInteger prev;
    private BigInteger old;
    private BigInteger result;
    private  MyContainer cache;

    public Fibonachi(){
        prev = new BigInteger("1");
        old = new BigInteger("1");
        result = new BigInteger("0");
        cache = new MyContainer();
        cache.add(new BigInteger("0"));
        cache.add(new BigInteger("1"));
        cache.add(new BigInteger("1"));
    }



    public  BigInteger calculator(int index) {
        if (cache.size()>index) return (BigInteger)cache.getElement(index);
        for (int i = 3; i <=index; i++) {
            result = prev.add(old);
            cache.add(result);
            old = prev;
            prev = result;
        }
        return (BigInteger)cache.getElement(index);

        //почему если return result;
        //получаются вот такие результаты - 0, 1, 1, 2, 5, 5, 34
        //при этом дебаггер показывает что в коллекцию все записывается верно
    }
}
