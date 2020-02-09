package Task13Treeset;

import java.util.Comparator;

public class DepositComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Investor inv1 = (Investor)o1;
        Investor inv2 = (Investor)o2;
        return inv1.getDeposit() - inv2.getDeposit();
    }
}
