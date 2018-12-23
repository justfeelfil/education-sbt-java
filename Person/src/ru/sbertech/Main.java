package ru.sbertech;

public class Main {

    public static void main(String[] args) {
	    Person dima = new PersonImpl("Dima", 20, Gender.MALE);
	    Person sveta = new PersonImpl("Sveta", 21, Gender.FEMALE);
	    Person vasya = new PersonImpl("Vasya", 22, Gender.MALE);
	    Person anya = new PersonImpl("Anya", 23, Gender.FEMALE);
	    Person egor = new PersonImpl("Egor", 17, Gender.MALE);

        marry(dima,sveta);
	    marry(sveta,dima);
	    marry(anya, sveta);
	    marry(vasya, egor);
	    marry(egor, sveta);
	    marry(vasya, sveta);
    }

    public static void marry(Person per1, Person per2) {
        System.out.println("Attempting to marry " + per1.toString() + "\nwith " + per2.toString());
        boolean result = per1.marry(per2);
        System.out.println("Result = " + result + "\n");
    }
}
