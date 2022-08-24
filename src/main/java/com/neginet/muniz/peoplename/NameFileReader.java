package com.neginet.muniz.peoplename;

import java.io.*;

public class NameFileReader {

    public static void process (String fileName) {
        if(fileName==null)
            throw new IllegalStateException("Full path file name argument not informed !");

        File file = new File(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(String.format("File %s not exists !",fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
