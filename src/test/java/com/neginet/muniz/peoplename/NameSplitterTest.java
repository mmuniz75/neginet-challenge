package com.neginet.muniz.peoplename;

import org.junit.Before;
import org.junit.Test;

public class NameSplitterTest {

    private String rootPath;

    @Before
    public void setup(){
        rootPath = this.getClass().getResource("").getPath()
                   .replace("com/neginet/muniz/peoplename","");
    }

    @Test
    public void oneInvalidName() {
        NameFileReader.process(rootPath + "coding-test-data-1.txt");
    }
}
