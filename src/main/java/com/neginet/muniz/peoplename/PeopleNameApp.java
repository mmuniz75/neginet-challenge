package com.neginet.muniz.peoplename;

import com.neginet.muniz.peoplename.dto.NameCounts;

import java.util.TreeMap;

public class PeopleNameApp {

    public static void main (String[] args) {
        String fileName = args[0];

        if(fileName==null) {
            System.out.println("Full path file name argument not informed !");
            System.exit(1);
        }

        try  {
            NameFileReader nameFileReader = new NameFileReader();
            var output = nameFileReader.process(fileName);

            printFirstOutPut(output.getNameCounts());
            printCommonOutPut(output.getCommonNames().getLastNames(),"last");
            printCommonOutPut(output.getCommonNames().getFirstNames(),"first");

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

   private static void printFirstOutPut(NameCounts counts){
       System.out.println(String.format("Full names : %s",counts.getCountFullName()));
       System.out.println(String.format("Last names: %s",counts.getCountLastNames()));
       System.out.println(String.format("First names: %s",counts.getCountFistNames()));
   }

    private static void printCommonOutPut(TreeMap<Long, String> map, String title){
        System.out.println(String.format("\nThe most common %s names are:", title));
        for(long key : map.keySet())
            System.out.println( map.get(key) + " : " + key);
    }

}
