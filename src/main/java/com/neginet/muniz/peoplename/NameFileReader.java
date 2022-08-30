package com.neginet.muniz.peoplename;

import com.neginet.muniz.peoplename.domain.CommonNames;
import com.neginet.muniz.peoplename.domain.Person;
import com.neginet.muniz.peoplename.dto.AnalisysOutput;
import com.neginet.muniz.peoplename.dto.NameCounts;

import java.io.*;
import java.net.CookieHandler;
import java.util.*;
import java.util.stream.Collectors;

public class NameFileReader {

    private HashMap<String, Long> firstNames = new HashMap<>();
    private HashMap<String, Long> lastNames = new HashMap<>();
    private HashMap<String, Long> fullNames = new HashMap<>();
    private CommonNames commonNames = new CommonNames();
    private Integer countModifiedNames = 25;

    public AnalisysOutput process (String fileName, int countModifiedNames) {

        AnalisysOutput output = new AnalisysOutput();
        if(fileName==null)
            throw new IllegalStateException("Full path file name argument not informed !");

        if(countModifiedNames>0)
            this.countModifiedNames = countModifiedNames;

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
                    collectAllNames(person);
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
        output.setModifiedNames(processModifiedNames());
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

    Queue<String> noDuplicateLastNames = new ArrayDeque<>();
    Stack<String> noDuplicateFirstNames = new Stack<>();
    List<String> existsNames = new ArrayList<>();
    private void collectAllNames(Person person) {
        if(existsNames.size() >= countModifiedNames*2)
            return;

        if(!existsNames.contains(person.getFirstName())){
            existsNames.add(person.getFirstName());
            noDuplicateFirstNames.push(person.getFirstName());
        }

        if(!existsNames.contains(person.getLastName())){
            existsNames.add(person.getLastName());
            noDuplicateLastNames.add(person.getLastName());
        }

    }

    private List<Person>processModifiedNames() {
        List<Person> modifiedNames = new ArrayList<>();

        int countFirstName = noDuplicateFirstNames.size();
        int countLastName = noDuplicateLastNames.size();

        for(int index = 0; index < countFirstName && index < countLastName ;index++){
            Person person = new Person(noDuplicateFirstNames.pop(), noDuplicateLastNames.poll());
            modifiedNames.add(person);
        }

        return modifiedNames;
    }

}
