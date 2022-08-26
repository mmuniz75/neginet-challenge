package com.neginet.muniz.peoplename.dto;

import com.neginet.muniz.peoplename.domain.CommonNames;

public class AnalisysOutput {

    private NameCounts nameCounts;
    private CommonNames commonNames;

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
