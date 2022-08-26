package com.neginet.muniz.peoplename;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NameSplitterTest {

    private String rootPath;

    @Before
    public void setup(){
        rootPath = this.getClass().getResource("").getPath()
                   .replace("com/neginet/muniz/peoplename","");
    }

    @Test
    public void oneInvalidName() {
        NameFileReader nameFileReader = new NameFileReader();
        var output = nameFileReader.process(rootPath + "coding-test-data-1.txt");
        var nameCounts = output.getNameCounts();
        assertTrue( nameCounts.getCountFullName() == 5);
        assertTrue( nameCounts.getCountLastNames() == 4);
        assertTrue( nameCounts.getCountFistNames() == 3);

        var commonNames = output.getCommonNames();
        var commonFirstNames = commonNames.getFirstNames();
        var commonLastNames = commonNames.getLastNames();

        assertEquals("Smith", commonLastNames.get(2L).get(0));
        assertEquals("Thomas", commonLastNames.get(1L).get(0));
        assertEquals("Upton", commonLastNames.get(1L).get(1));
        assertEquals("Cartman", commonLastNames.get(1L).get(2));

        assertEquals("Joan", commonFirstNames.get(3L).get(0));
        assertEquals("Sam", commonFirstNames.get(1L).get(0));
        assertEquals("Eric", commonFirstNames.get(1L).get(1));
    }

}
