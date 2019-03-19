package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class Map<K, V> {
    ArrayList<MapEntry<K, V>> s;

    public Map() {
        s = new ArrayList<>();
    }

    public void clear() {
        s.clear();
    }

    public boolean containsKey(K key) {
        for (MapEntry M: s) {
            if(M.getKey().equals(key))
                return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (MapEntry m: s) {
            if(m.getValue().equals(value))
                return true;
        }
        return false;
    }

    public Set<MapEntry<K, V>> entrySet() {
        Set<MapEntry<K, V>> st = new Set<>(s);
        return st;
    }

    public V get(K key){
        for (MapEntry<K, V> m: s) {
            if(m.getKey().equals(key)) {
                return m.getValue();
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }


    public V put(K key, V value) {
        /*
        MapEntry<K, V> p = null;
        V v = null;
        for(MapEntry q: s) {
            if(q.getKey().equals(key)) {
                p = q;
                break;
            }
        }
        if(p != null) {
            v = p.getValue();

        }*/
        if(containsKey(key)) {
            V v = remove(key);
            s.add(new MapEntry<>(key, value));
            return v;
        } else {
            s.add(new MapEntry<>(key, value));
            return null;
        }
    }

    public V remove(K key){
        int x;
        for(x = 0; x < s.size(); x++) {
            if(s.get(x).getKey().equals(key))
                break;
        }
        V v = s.get(x).getValue();
        s.remove(x);
        return v;
    }

    public int size() {
        return s.size();
    }

    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (MapEntry<K, V> m: s) {
            values.add(m.getValue());
        }
        return values;
    }

    public Set<K> keySet() {
        Set<K> keys = new Set<>();
        for (MapEntry<K, V> m: s) {
            keys.add(m.getKey());
        }
        return keys;
    }
}

class MapEntry<K, V> {
    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    public boolean equals(Object o) {
        if(o instanceof MapEntry) {
            return ((MapEntry) o).getKey().equals(this.key);
        }
        return false;
    }
}

class Set<E> {
    ArrayList<E> s;

    public Set() {
        s = new ArrayList<>();
    }

    public Set(ArrayList<E> s) {
        this.s = s;
    }

    public boolean contains(E o) {
        for (E d: s) {
            if(s.equals(o))
                return true;
        }
        return false;
    }

    public boolean add(E o) {
        if(!contains(o)) {
            s.add(o);
            return true;
        }
        return false;
    }

    public void clear() {
        s.clear();
    }

    public boolean isEmpty() {
        return s.isEmpty();
    }

    public Iterator iterator() {
        return s.iterator();
    }

    public boolean remove(E o) {
        return s.remove(o);
    }

    public int size() {
        return s.size();
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("[");
        //Iterator i = s.iterator();
        int i = 0;

        for (E o: s) {
            if(i != 0) {
                st.append(", ");
            }
            st.append(o.toString());
            i++;
        }
        st.append("]");
        return st.toString();
    }

    public Object[] toArray() {
        return s.toArray();
    }
}