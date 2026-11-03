package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy{

    private T array[];

    public IterableWithPolicyImpl(T[] array) {
        this.array = array;
    }
    @Override
    public void setIterationPolicy(Predicate filter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator iterator() {
        return new IteratorWS<>();
    }

    public class IteratorWS<T> implements Iterator{

        private int idx=0;

        @Override
        public boolean hasNext() {
            return idx<(array.length-1);
        }

        @Override
        public Object next() {
            
            idx++;
            return array[idx];
        }

    }
    
}