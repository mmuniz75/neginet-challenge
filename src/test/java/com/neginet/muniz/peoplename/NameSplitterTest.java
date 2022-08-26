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
    }
}
