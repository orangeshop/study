package study;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static List<Member> members = new ArrayList<>();

    static {
        members.add(new Member("Olivia", 25, Member.Gender.FEMALE));
        members.add(new Member("Emma", 28, Member.Gender.FEMALE));
        members.add(new Member("Sophia", 31, Member.Gender.FEMALE));

        members.add(new Member("Liam", 25, Member.Gender.MALE));
        members.add(new Member("Noah", 30, Member.Gender.MALE));
        members.add(new Member("James", 33, Member.Gender.MALE));
    }

    public static void main(String[] args) {
        for (Member member : members) {
            System.out.println(member.getName() + " " + member.getAge() + " " + member.getGender());
        }

        // 성별 필터
        System.out.println("---------");
        List<Member> list = members.stream()
                .filter(member -> member.getGender().equals(Member.Gender.FEMALE)).toList();

        for (Member member : list) {
            System.out.println(member.getName() + " " + member.getAge() + " " + member.getGender());
        }

        // 나이 필터
        System.out.println("---------");
        List<Member> list1 = members.stream().filter(member -> member.getAge() == 25).toList();

        for (Member member : list1) {
            System.out.println(member.getName() + " " + member.getAge() + " " + member.getGender());
        }

        // 나이 & 성별 필터
        System.out.println("---------");
        List<Member> list2 = members.stream()
                .filter(member -> member.getAge() == 25)
                .filter(member -> member.getGender().equals(Member.Gender.MALE))
                .toList();

        for (Member member : list2) {
            System.out.println(member.getName() + " " + member.getAge() + " " + member.getGender());
        }

        // 나이 + 10 map
        System.out.println("---------");


    }
}