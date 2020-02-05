package Task14;

import java.util.Comparator;

public class MoneyComparator implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            Comparable node1 = (BankDataBase.Account)o1;
            Comparable node2 = (BankDataBase.Account)o2;
            return Integer.compare(((BankDataBase.Account) node1).getInvestor().getMoney(), ((BankDataBase.Account) node2).getInvestor().getMoney());
        }
    }

