package custom.list;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;

public class SortedIntegerList {
    private final LinkedList<Integer> list;
    boolean allowDuplicates;

    public SortedIntegerList(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
        list = new LinkedList<Integer>();
    }

    public SortedIntegerList() {
        this(true);
    }

    public void add(int value) {
        if (list.isEmpty()) {
            list.add(value);
        } else {
            ListIterator<Integer> iterator = list.listIterator();
            while (iterator.hasNext()) {
                Integer current = iterator.next();

                if (current > value) {
                    iterator.previous();
                    iterator.add(value);
                    return;
                }

                if (current == value && !allowDuplicates) {
                    return;
                }
            }
            iterator.add(value);
        }
    }

    public void remove(int value) {
        list.removeFirstOccurrence(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SortedIntegerList other)) {
            return false;
        }

        if (this.list.size() != other.list.size()) {
            return false;
        }

        ListIterator<Integer> iterator1 = this.list.listIterator();
        ListIterator<Integer> iterator2 = other.list.listIterator();

        while (iterator1.hasNext()) {
            if (!Objects.equals(iterator1.next(), iterator2.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}