package com.android.killqwerty.myapp.myapp3.android1;

class PersonTEMP {
    private String name;
    private String lastName;
    private int age;
    private String info = "this is class of personTEMP, create only for this training moment in " +
            "lesson8, json example";
    PersonTEMP(String name, String lastName, int age){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return info;
    }
}
