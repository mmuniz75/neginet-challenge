package com.neginet.muniz.peoplename;

import com.neginet.muniz.peoplename.domain.Person;
import com.neginet.muniz.peoplename.dto.AnalisysOutput;
import com.neginet.muniz.peoplename.dto.NameCounts;

import java.io.*;
import java.util.HashMap;

public class NameFileReader {

    private HashMap<String, Long> firstNames = new HashMap<>();
    private HashMap<String, Long> lastNames = new HashMap<>();
    private HashMap<String, Long> fullNames = new HashMap<>();

    public AnalisysOutput process (String fileName) {

        AnalisysOutput output = new AnalisysOutput();
        if(fileName==null)
            throw new IllegalStateException("Full path file name argument not informed !");

        File file = new File(fileName);

        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
                if(count % 2 == 0)
                    continue;

                Person person = new Person(line);
                if(person.isValid()){
                    processNameCounts(person);
                }

            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(String.format("File %s not exists !",fileName));
        } catch (IOException e) {
             throw new IllegalStateException(e.getMessage());
        }

        output.setNameCounts(new NameCounts(firstNames.size(), lastNames.size(), fullNames.size()));
        return output;
    }

    private void processNameCounts(Person person){
        sumMap(firstNames, person.getFirstName());
        sumMap(lastNames, person.getLastName());
        sumMap(fullNames, person.getFullName());
    }

    private void sumMap(HashMap<String, Long> map, String key){
        if(map.containsKey(key))
            map.put(key, map.get(key) + 1);
        else
            map.put(key, 1L);
    }

}
