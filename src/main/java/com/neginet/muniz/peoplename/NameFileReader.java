package com.neginet.muniz.peoplename;

import com.neginet.muniz.peoplename.domain.CommonNames;
import com.neginet.muniz.peoplename.domain.Person;
import com.neginet.muniz.peoplename.dto.AnalisysOutput;
import com.neginet.muniz.peoplename.dto.NameCounts;

import java.io.*;
import java.net.CookieHandler;
import java.util.HashMap;

public class NameFileReader {

    private HashMap<String, Long> firstNames = new HashMap<>();
    private HashMap<String, Long> lastNames = new HashMap<>();
    private HashMap<String, Long> fullNames = new HashMap<>();
    private CommonNames commonNames = new CommonNames();

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
        commonNames.shirinkMapNames();
        output.setCommonNames(commonNames);
        return output;
    }

    private void processNameCounts(Person person){
        long count = sumMap(firstNames, person.getFirstName());
        commonNames.addFirstName(person.getFirstName(), count);

        count = sumMap(lastNames, person.getLastName());
        commonNames.addLastName(person.getLastName(), count);

        sumMap(fullNames, person.getFullName());
    }

    private Long sumMap(HashMap<String, Long> map, String key){
       long count = 1L ;

       if(map.containsKey(key)) {
            count = map.get(key) + 1;
            map.put(key, count);
       }else
            map.put(key, count);

       return count;
    }

}
