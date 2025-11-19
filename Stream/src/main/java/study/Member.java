package study;

public class Member {
    private String name;
    private int age;
    private Gender gender;

    public Member(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public enum Gender {
        MALE, FEMALE
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    private void reflection(){
        System.out.println("success reflection");
    }
}
