package com.neginet.muniz.peoplename.domain;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CommonNames {

    TreeMap<Long, String> firstNames = new TreeMap<>(Comparator.reverseOrder());
    TreeMap<Long, String> lastNames = new TreeMap<>(Comparator.reverseOrder());

    public TreeMap<Long, String> getFirstNames() {
        return firstNames;
    }

    public TreeMap<Long, String> getLastNames() {
        return lastNames;
    }

    public void addFirstName(String firstName, Long count){
        addValueToMap(firstNames,firstName, count);
    }

    public void addLastName(String firstName, Long count){
        addValueToMap(lastNames,firstName, count);
    }

    public void addValueToMap(TreeMap<Long, String> map, String value, Long count){
        removeOldValues(map, value);
        map.put(count, value);
        if(map.size()>10)
            map.pollLastEntry();
    }

    private void removeOldValues(Map<Long, String> map, String oldValue){
        for(long key : map.keySet()){
            if(map.get(key).equals(oldValue)) {
                map.remove(key);
                break;
            }
        }
    }


}
