package com.neginet.muniz.peoplename.dto;

import com.neginet.muniz.peoplename.domain.CommonNames;
import com.neginet.muniz.peoplename.domain.Person;

import java.util.List;

public class AnalisysOutput {

    private NameCounts nameCounts;
    private CommonNames commonNames;
    private List<Person> modifiedNames;


    public List<Person> getModifiedNames() {
        return modifiedNames;
    }

    public void setModifiedNames(List<Person> modifiedNames) {
        this.modifiedNames = modifiedNames;
    }

    public NameCounts getNameCounts() {
        return nameCounts;
    }

    public CommonNames getCommonNames() {
        return commonNames;
    }

    public void setCommonNames(CommonNames commonNames) {
        this.commonNames = commonNames;
    }

    public void setNameCounts(NameCounts nameCounts) {
        this.nameCounts = nameCounts;
    }
}
