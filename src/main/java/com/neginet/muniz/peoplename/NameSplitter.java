package com.neginet.muniz.peoplename;

import java.util.StringTokenizer;

public class NameSplitter {

    private String firstName;
    private String lastName;

    public NameSplitter(String fileLine){
        StringTokenizer names = new StringTokenizer(fileLine, ",");
        firstName = names.nextToken();

        StringTokenizer others = new StringTokenizer(names.nextToken(),"--");
        lastName = others.nextToken().trim();
    }

    public String getFullName(){
        return String.format("%s %s", firstName,lastName);
    }

    public boolean isValid(){
        String fullName = getFullName().replaceAll(" ","" );
        return fullName != null && fullName.chars().allMatch(Character::isLetter);
    }
}
