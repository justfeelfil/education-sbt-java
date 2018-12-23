package ru.sbertech;

public interface Person {

    String getName();

    Gender getGender();

    int getAge();

    boolean marry(Person person);

    Person getSpouse();

    void divorce();
}
