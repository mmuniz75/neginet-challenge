package com.neginet.muniz.peoplename.domain;

import java.util.StringTokenizer;

public class Person {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person(String fileLine){
        StringTokenizer names = new StringTokenizer(fileLine, ",");
        lastName =  names.nextToken();

        StringTokenizer others = new StringTokenizer(names.nextToken(),"--");
        firstName = others.nextToken().trim();
    }

    public String getFullName(){
        return String.format("%s %s", firstName,lastName);
    }

    public boolean isValid(){
        String fullName = getFullName().replaceAll(" ","" );
        return fullName != null && fullName.chars().allMatch(Character::isLetter);
    }
}
