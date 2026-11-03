package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] array,Predicate<T> filter) {
        this.array = array;
        this.filter = filter;

    }
    public IterableWithPolicyImpl(T[] array) {
        this(array, new Predicate<T>(){
                @Override
                public boolean test(T elem) {
                return true;
                }
            }
        );
    }


    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorWS();
    }

    private class IteratorWS implements Iterator<T> {

        private int idx = 0;

        @Override
        public boolean hasNext() {
            return idx < array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[idx++];
        }
    }
}