package Task14Vocabluary;

public class Vocabluary {
    private Entry[] data = new Entry[1000];
    private int records;


    public Vocabluary() {
    }

    public Vocabluary(int capacity) {
        this.data = new Entry[capacity];
    }

    public int size() {
        return records;
    }


    public int getIndex(String word) {
        return Math.abs(word.hashCode()) % data.length;
    }


    public void add(String eng, String rus) {
        int index = getIndex(eng);
        Entry current = data[index];
        while (current != null) {
            if (current.getEng().equals(eng)) {
                current.setRus(rus);
                System.out.println("Перевод изменен");
                return;
            } else {
                current = current.getNext();
            }
        }
        Entry created = new Entry(eng, rus);
        created.setNext(data[index]);
        data[index] = created;
        System.out.println("Запись добавлена");
        records++;
    }


    public void translate(String forTranslate) {
        for (Entry entry : data) {
            while (entry != null) {
                if (forTranslate.equals(entry.eng)) {
                    System.out.println(entry.toString());
                    return;
                }
                entry = entry.getNext();
            }
        }
        System.out.println("Перевода для " + forTranslate + " нет в словаре");
    }


    public String get(String forTranslate) {
        for (Entry entry : data) {
            while (entry != null) {
                if (forTranslate.equals(entry.eng)) {
                    return entry.rus;
                }
                entry = entry.getNext();
            }
        }
        return null;
    }


    public void showAll(){
        System.out.println("\nАнгло-русский словарь");
        System.out.println("Слов: " + records);
        for (Entry entry : data) {
            while (entry != null) {
                System.out.println(entry);
                entry = entry.getNext();
            }
        }
        System.out.println();
    }

    public void remove(String word) {
        int index = getIndex(word);
        if(data[index] == null){
            System.out.println("Запись отсутствует");
            return;
        }
        if(data[index].eng.equals(word)){
            data[index] = data[index].next;
            records--;
            System.out.println("Запись удалена");
            return;
        }
        Entry current = data[index];
        while (current.next!=null){
            if(current.next.eng.equals(word)){
                current.setNext(current.next.next);
                records--;
                System.out.println("Запись удалена");
                return ;
            } else {
                current = current.getNext();
            }
        }
        System.out.println("Запись отсутствует");
    }



    public void clear() {
        this.data = new Entry[1000];
        records = 0;

    }


    class Entry {
        private String eng;
        private String rus;
        Entry next;


        public Entry(String eng, String rus) {
            if (eng == null || rus == null) throw new IllegalArgumentException();
            this.eng = eng;
            this.rus = rus;

        }

        public String getEng() {
            return eng;
        }


        public String getRus() {
            return rus;
        }

        public void setRus(String rus) {
            this.rus = rus;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + eng + " - " + rus + "]";
        }
    }
}
