package com.neginet.muniz.peoplename.dto;

public class NameCounts {
    private Integer countFistNames;
    private Integer countLastNames;
    private Integer countFullName;

    public NameCounts(Integer countFistNames, Integer countLastNames, Integer countFullName) {
        this.countFistNames = countFistNames;
        this.countLastNames = countLastNames;
        this.countFullName = countFullName;
    }

    public Integer getCountFistNames() {
        return countFistNames;
    }

    public Integer getCountLastNames() {
        return countLastNames;
    }

    public Integer getCountFullName() {
        return countFullName;
    }


}
