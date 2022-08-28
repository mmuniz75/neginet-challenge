package com.neginet.muniz.peoplename.domain;

import java.util.*;

public class CommonNames {

    TreeMap<Long, List<String>> firstNames = new TreeMap<>(Comparator.reverseOrder());
    TreeMap<Long, List<String>> lastNames = new TreeMap<>(Comparator.reverseOrder());

    public TreeMap<Long, List<String>> getFirstNames() {
        return firstNames;
    }

    public TreeMap<Long, List<String>> getLastNames() {
        return lastNames;
    }

    public void addFirstName(String firstName, Long count){
        addValueToMap(firstNames,firstName, count);
    }

    public void addLastName(String firstName, Long count){
        addValueToMap(lastNames,firstName, count);
    }

    public void shirinkMapNames(){
        firstNames = shirinkMap(firstNames);
        lastNames = shirinkMap(lastNames);
    }

    private TreeMap<Long, List<String>> shirinkMap(TreeMap<Long, List<String>> map){
        TreeMap<Long, List<String>> shirinkMap = new TreeMap<>(Comparator.reverseOrder());
        int countItens = 0;

        for(long key : map.keySet()){
            List names = map.get(key);
            var remainSpace = 10 - countItens;
            if(names.size() > remainSpace){
                List<String> newNames = new ArrayList<>();
                newNames.addAll(names.subList(0,remainSpace));
                shirinkMap.put(key, newNames);
                break;
            }else {
                countItens = countItens + names.size();
                shirinkMap.put(key, names);
            }
        }

        return shirinkMap;
    }

    public void addValueToMap(TreeMap<Long, List<String>> map, String value, Long count){
        removeOldValues(map, value);
        List<String> values = null;

        if(map.containsKey(count))
            values = map.get(count);
        else
            values = new ArrayList<>();

        values.add(value);

        map.put(count, values);
    }

    private void removeOldValues(Map<Long, List<String>> map, String oldValue){
        for(long key : map.keySet()){
            for(String name : map.get(key))
                if(name.equals(oldValue)) {
                    List names = map.get(key);
                    names.remove(name);
                    if(names.isEmpty())
                        map.remove(key);
                    return;
                }
        }
    }


}
