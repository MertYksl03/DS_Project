package data_structures;

import data_structures.LinkedList;

public class HashMap{
    private LinkedList[] map;
    private int size;

    public HashMap(int size){
        this.size = size;
        map = new LinkedList[size];
        for(int i = 0; i < size; i++){
            map[i] = new LinkedList<Integer>();
        }
    }

    private int hash(int key){
        return key % size;
    }

    public void put(int key, int value){
        int index = hash(key);
        LinkedList<Integer> list = map[index];
        
        // Check if the key already exists
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i) == key) {
                list.remove(i);  // Remove old key
                break;
            }
        }
        
        list.add(key);  // Add the new key
        // You can store the value in the same list or in a separate list depending on design
    }

    public int get(int key){
        int index = hash(key);
        LinkedList<Integer> list = map[index];
        
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i) == key) {
                return list.get(i);  // Return the corresponding value
            }
        }
        
        return -1;  // Return an error value or handle differently if key is not found
    }

    public void remove(int key){
        int index = hash(key);
        LinkedList<Integer> list = map[index];
        
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i) == key) {
                list.remove(i);
                break;
            }
        }
    }

    public boolean containsKey(int key){
        int index = hash(key);
        LinkedList<Integer> list = map[index];
        
        for (int i = 0; i < list.length(); i++) {
            if (list.get(i) == key) {
                return true;
            }
        }
        
        return false;
    }

    public int size(){
        return size;
    }
}