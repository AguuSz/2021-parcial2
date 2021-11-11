package info3.parcial2.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedList<AnyType> implements Iterable<AnyType> {
    private LinkedNode<AnyType> begin;
    private int size;

    public LinkedList() {
        begin = null;
        size = 0;
    }

    public LinkedList(AnyType a) {
        begin = null;
        size = 0;
        try {
            add(a, 0);
        } catch (Exception e) {

        }
    }

    /**
     * Adds a node at the pos position
     *
     * @param d   the data
     * @param pos the position
     */

    public void add(AnyType d, int pos) throws Exception {
        LinkedNode<AnyType> aux;
        if (pos == 0) {
            begin = new LinkedNode<>(begin, d);
        } else {
            aux = getNode(pos - 1);
            aux.next = new LinkedNode<>(aux.next, d);
        }
        size++;
    }


    /**
     * Add data at the begining
     *
     * @param d
     */
    public void add(AnyType d) throws Exception {
        this.add(d, 0);
    }

    /**
     * Get data from positon idx
     *
     * @param pos
     * @return the data
     */
    public AnyType get(int pos) {
        LinkedNode<AnyType> aux = getNode(pos);
        return aux.data;
    }

    /**
     * @param d
     * @param pos
     */
    public void update(AnyType d, int pos) throws Exception {
        LinkedNode<AnyType> aux = getNode(pos);
        aux.data = d;
    }

    /**
     * @param pos
     */
    public void delete(int pos) throws Exception {
        if (pos == 0) {
            if (begin == null)
                throw new Exception("pos not found");
            begin = begin.next;
        } else {
            LinkedNode<AnyType> aux = getNode(pos - 1);
            if (aux.next == null)
                throw new Exception("pos not found");

            aux.next = aux.next.next;
        }
        size--;
    }


    /**
     * @return
     */
    public int getSize() {
        return size;
    }

    private LinkedNode<AnyType> getNode(int pos) {
        LinkedNode<AnyType> aux = begin;
        int p = 0;
        while (p < pos && aux != null) {
            p++;
            aux = aux.next;
        }
        if (aux == null) {
            return null;
        }
        return aux;
    }

    public boolean contains(AnyType value) {
        LinkedNode<AnyType> current = begin;

        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public Object[] toObjectArray() {
        List<AnyType> array = new ArrayList<>(size);

        for (AnyType item : this) {
            array.add(item);
        }

        return array.toArray();
    }

    // Overrides de Iterable
    @Override
    public Iterator<AnyType> iterator() {
        return new Iterator<AnyType>() {

            LinkedNode<AnyType> current = begin;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public AnyType next() {
                if (hasNext()) {
                    AnyType data = current.data;
                    current = current.next;
                    return data;
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not implemented");
            }
        };
    }
}