package Task15Upgrade;


import Task15Upgrade.Hasher;

public class MyHash implements Hasher {

    /**
     * Hash вычисляется по следующему принципу:
     * 1) Первые три символа в ключе образует первые цифры хэшкода (одну или две),
     * либо 0 если это не латинские буквы (метод firstPartOfHash)
     * <p>
     * 2) Сумма трех последних символов образует вторую часть хэшкода (метод secondPartOfHash)
     * <p>
     * 3) Если сумма последних трех символов ключа  менее двузначной, она дополняется нолем
     * Например слово add преобразуется в 909
     * <p>
     * 3) Преобразование char в int происходит по логике определенной в методе charConvertor
     * <p>
     * 4) Слова начинающиеся с символа не входящего в латинский алфавит будут распологаться раньше всех,
     * например !!!add преобразуется в 009 или просто 9
     * <p>
     * 5) Словам начинающимся с не латинских букв выделено довольно мало места, т.к. они не являются нашей целевой группой
     * но все таки немножко есть
     * <p>
     * 6) Данный адгоритм кодирования создает около 7900 уникальных ключей
     * <p>
     * 7) Если возникнет необходимость складывать более менее по алфавиту, можно в качестве первой части хэш кода
     * использовать кодировку первой буквы, но программы словари обычно просто выдают реакцию на ключ, и не важно
     * как там что хранится
     */
    @Override
    public int getHash (String key) {
        StringBuffer sb = new StringBuffer();
        int first = firstPartOfHash(key);
        sb.append(first);

        int last = secondPartOfHash(key);
        //переменная добавляется в строку, дополняясь нолями если нужно
        if (last < 10) {
            sb.append("0").append(last);
        } else {
            sb.append(last);
        }
        String hash = sb.toString();

        return Integer.valueOf(hash);
    }

    /**
     * Метод переопределяющий стандартную кодировку char в кодировку от 1 до 26
     * другие символы возвращают значение (0) для дальнейшей обработки
     * <p>
     * Данный метод решает следующие проблемы:
     * 1) Стандартная кодировка символов создаст пустоту в начале таблицы
     * 2) Пренебрегает регистром, благодаря чему ключ не зависит от регистра, и пользователю становится проще
     * 3) Создает особую реакцию на символы, не входящие в латинский алфавит
     */
    private static int charConvertor(int c) {
        int i = c;
        if (c >= 65 && c <= 90) {
            return i = i - 64;
        } else if ((c >= 97 && c <= 122)) {
            return i = i - 96;
        } else {
            return 0;
        }
    }

    //возвращает первую часть кода на основе суммы трех последних символов
    private int firstPartOfHash(String key) {
        char[] arr = key.toCharArray();
        int summ = 0;
        if (arr.length == 1) {
            return charConvertor(arr[0]);
        } else if (arr.length == 2) {
            return charConvertor(arr[0]) + charConvertor(arr[1]);
        } else {
            return charConvertor(arr[0]) + charConvertor(arr[1]) + charConvertor(arr[2]);
        }
    }

    //возвращает вторую часть кода на основе суммы трех последних символов
    private int secondPartOfHash(String key) {
        char[] arr = key.toCharArray();
        if (arr.length == 1) {
            return charConvertor(arr[arr.length - 1]);
        } else if (arr.length == 2) {
            return charConvertor(arr[arr.length - 1]) + charConvertor(arr[arr.length - 2]);
        } else {
            return charConvertor(arr[arr.length - 1]) + charConvertor(arr[arr.length - 2]) + charConvertor(arr[arr.length - 3]);
        }

    }
}
