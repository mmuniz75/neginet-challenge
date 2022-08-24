package com.neginet.muniz.peoplename;

import java.io.*;

public class NameFileReader {

    public static void main (String[] args) {
        String fileName = args[0];

        if(fileName==null)
            throw new IllegalStateException("Full path file name argument not informed !");

        File file = new File(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(String.format("File %s not exists !",fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
