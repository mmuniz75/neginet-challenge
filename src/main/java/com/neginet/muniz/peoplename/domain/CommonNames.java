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
       shirinkMap(firstNames);
       shirinkMap(lastNames);
    }

    private void shirinkMap(TreeMap<Long, List<String>> map){
        int countItens = 0;
        List<Long> keysToRemove = new ArrayList<>();
        for(long key : map.keySet()){
            List names = map.get(key);
            var remainSpace = 10 - countItens;
            if(names.size() > remainSpace){
                List<String> newNames = new ArrayList<>();
                newNames.addAll(names.subList(0,remainSpace));
                map.put(key, newNames);
                for(long key2 : map.keySet()){
                    if(key2>=key) continue;
                    keysToRemove.add(key2);
                }
            }else
                countItens = countItens + names.size();
        }

        if(!keysToRemove.isEmpty()){
            for(long key : keysToRemove)
                map.remove(key);
        }

    }

    public void addValueToMap(TreeMap<Long, List<String>> map, String value, Long count){
        removeOldValues(map, value);
        List<String> values = null;
        if(map.containsKey(count)) {
            values = map.get(count);
            if(map.size()==10)
                map.pollLastEntry(); // remove last item if already has 10 itens
        }else
            values = new ArrayList<>();

        values.add(value);

        map.put(count, values);
        if(map.size()>10)
            map.pollLastEntry();
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
