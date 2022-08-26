package com.neginet.muniz.peoplename;

public class PeopleNameApp {

    public static void main (String[] args) {
        String fileName = args[0];

        if(fileName==null) {
            System.out.println("Full path file name argument not informed !");
            System.exit(1);
        }

        try  {
            NameFileReader nameFileReader = new NameFileReader();
            nameFileReader.process(fileName);

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
}
