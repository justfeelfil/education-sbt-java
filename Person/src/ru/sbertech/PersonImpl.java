package ru.sbertech;

public class PersonImpl implements Person {
    private final String name;
    private final int age;
    private final Gender gender;
    private Person spouse;

    PersonImpl(String name, int age, Gender gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Gender getGender(){
        return  gender;
    }

    @Override
    public int getAge(){
        return age;
    };

    /**
     * This method checks gender of persons.
     * 1) If genders are not equal - tries to marry.
     * 2) If one of them has another spouse - execute divorce(sets spouse = null for husband and wife.
     *      Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     *
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */

    @Override
    public boolean marry(Person person){

        // Check if marriage is allowed
        if(this.gender == person.getGender() || this.age < 18 || person.getAge() < 18) {
            return false;
        }

        // When allowed
        if (this.spouse != null) {
            if (this.spouse != person) {
                this.divorce();
                this.spouse = person;
                person.marry(this);
            }
        } else {
            this.spouse = person;
            person.marry(this);
        }
        return true;
    }

    @Override
    public Person getSpouse() {
        return spouse;
    }

    @Override
    public void divorce() {
        if (this.spouse == null){
            return;
        }
        else{
            Person temp = this.spouse;
            this.spouse = null;
            temp.divorce();
        }
    }

    @Override
    public String toString(){
        String gender;
        if (this.gender == Gender.MALE){
            gender = "male";
        }
        else {
            gender = "female";
        }
        String result = getName() + ":\n    age = " + getAge() +
                "\n\tgender = " + gender + "\n\tspouse = " + (spouse != null ? spouse.getName() : null);
        return result;
    }
}
