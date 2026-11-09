package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

import it.unibo.nestedenum.MonthSorterNested.Month.sortByDays;
import it.unibo.nestedenum.MonthSorterNested.Month.sortByOrder;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private Comparator<String> BY_DAYS = new sortByDays();
    private Comparator<String> BY_ORDER = new sortByOrder();

@Override
public Comparator<String> sortByDays() {
    return BY_DAYS;
}

@Override
public Comparator<String> sortByOrder() {
    return BY_ORDER;
}

    enum Month {
    JANUARY(30),
    FEBRUARY(30),
    MARCH(30),
    APRIL(30),
    MAY(30),
    JUNE(30),
    JULY(30),
    AUGUST(30),
    SEPTEMBER(30),
    OCTOBER(30),
    NOVEMBER(30),
    DECEMBER(30);
    
final private int days;

    Month(final int days) {
            this.days = days;
    }

    static Month fromString(String monthString){
        try { 
            return Month.valueOf(monthString.toUpperCase());
        } catch (final IllegalArgumentException e) { 
            Month match = null;
            for (Month iterMonth : Month.values()) {
                if (iterMonth.toString().startsWith(monthString.toUpperCase())) {
                    if (match!=null) {
                        throw new IllegalArgumentException(
                            monthString + " is ambiguous: both " + match + " and " + iterMonth + " would be valid matches",
                            e
                        );
                    }
                match = iterMonth;
                }
            }
            if (match == null) {
                throw new IllegalArgumentException("che cazzo hai scritto");
            }
            return match;
        }
    }

    static class sortByDays implements Comparator<String>{
    @Override 
    public int compare(String s1, String s2){
    Month v1 = Month.fromString(s1);
    Month v2 = Month.fromString(s2);
   return Integer.compare(v1.days,v2.days);
    }
}
    static class sortByOrder implements Comparator<String>{
    @Override 
    public int compare(String s1, String s2){
        return Month.fromString(s1).compareTo(Month.fromString(s2));
    }
}

}
}