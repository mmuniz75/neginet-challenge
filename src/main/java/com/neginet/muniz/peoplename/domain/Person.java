package com.neginet.muniz.peoplename.domain;

import java.util.Objects;
import java.util.StringTokenizer;

public class Person {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getDisplayName(){
        return String.format("%s, %s", lastName,firstName);
    }

}
