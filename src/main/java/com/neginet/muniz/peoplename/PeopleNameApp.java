package com.neginet.muniz.peoplename;

import com.neginet.muniz.peoplename.domain.Person;
import com.neginet.muniz.peoplename.dto.NameCounts;

import java.util.List;
import java.util.TreeMap;

public class PeopleNameApp {

    public static void main (String[] args) {

        if (args == null || args.length<2){
            System.out.println("Full path file name and/or new number of names arguments not informed !");
            System.exit(1);
        }

        String fileName = args[0];
        Integer numberModifiedNames = Integer.valueOf(args[1]);

        try  {
            NameFileReader nameFileReader = new NameFileReader();
            var output = nameFileReader.process(fileName, numberModifiedNames);

            printFirstOutPut(output.getNameCounts());
            printCommonOutPut(output.getCommonNames().getLastNames(),"last");
            printCommonOutPut(output.getCommonNames().getFirstNames(),"first");
            printModifiedNamesOutPut(output.getModifiedNames());

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

   private static void printFirstOutPut(NameCounts counts){
       System.out.println(String.format("Full names : %s",counts.getCountFullName()));
       System.out.println(String.format("Last names: %s",counts.getCountLastNames()));
       System.out.println(String.format("First names: %s",counts.getCountFistNames()));
   }

    private static void printCommonOutPut(TreeMap<Long, List<String>> map, String title){
        System.out.println(String.format("\nThe most common %s names are:", title));
        for(long key : map.keySet()){
            for(String name : map.get(key))
                System.out.println(  name + " : " + key);
        }
    }

    private static void printModifiedNamesOutPut(List<Person> modifiedNames){
        System.out.println("\nList of Modified Names:");
        for(Person people : modifiedNames)
            System.out.println(people.getDisplayName());
    }

}
