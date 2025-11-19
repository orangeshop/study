package study;


import java.lang.reflect.Method;
import java.util.*;
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
        List<Member> list3 = members.stream()
                .map(member -> new Member(member.getName(), member.getAge() + 10, member.getGender()))
                .toList();

        for (Member member : list3) {
            System.out.println(member.getName() + " " + member.getAge() + " " + member.getGender());
        }

        List<List<Integer>> ls = List.of(List.of(1, 2), List.of(3, 4), List.of(5, 6));

        ls.stream().flatMap(integers -> integers.stream()).forEach(System.out::println);


        List<List<Object>> objects = List.of(List.of("aaaa", "bbbb"), List.of(111, 222));

        List<Integer> list4 = objects.stream()
                .flatMap(object -> object.stream())
                .filter(o ->
                        o instanceof Integer
                )
                .map(o -> Integer.class.cast(o))
                .map(i -> i + 100)
                .toList();

        list4.stream().forEach(System.out::println);


        // ------------------------------------------
        // 숨은 숫자 찾아서 뻥튀기하기
        List<Object> messyList = Arrays.asList(1, "Hello", 10, "Java", false, 5);

        // 2. 문제 풀이 (여기를 채워보세요!)
        List<Integer> result = messyList.stream()
                .filter(o -> o instanceof Integer)
                .map(Integer.class::cast)
                .map(integer -> integer * 10)
                .toList(); // 자바 16 이상 (이하 버전은 collect(Collectors.toList()))

        // 예상 출력: [10, 100, 50]
        System.out.println(result);

        // ------------------------------------------
        // 모든 주문 내역을 하나로 합치기
        List<List<String>> shoppingBaskets = Arrays.asList(
                Arrays.asList("사과", "바나나", "포도"),
                Arrays.asList("바나나", "키위"),
                Arrays.asList("포도", "수박", "사과")
        );

        // 2. 문제 풀이 (여기를 채워보세요!)
        List<String> result2 = shoppingBaskets.stream()
                .flatMap(Collection::stream)
                .distinct()
                .toList();

        // 예상 출력: [바나나, 사과, 수박, 키위, 포도]
        System.out.println(result2);


        // --------------------------------------
        //
        List<String> stringNumbers = Arrays.asList("1,5,3", "10,2", "4,8,6");

        List<Integer> result3 = stringNumbers.stream()
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(s -> Integer.parseInt(s))
                .sorted()
                .toList();

        // 예상 출력: [1, 2, 3, 4, 5, 6, 8, 10]
        System.out.println(result3);


        // --------------------------------------
        //
        List<Employee> employees = Arrays.asList(
                new Employee("김철수", "개발팀", 5000),
                new Employee("이영희", "마케팅", 4500),
                new Employee("박민수", "개발팀", 6000),
                new Employee("최지우", "마케팅", 4000),
                new Employee("정준하", "인사팀", 3500)
        );

        // 문제 풀이
        // 힌트: collect() 안에 Collectors.groupingBy를 써야 합니다.
        // groupingBy의 첫 번째 인자는 '기준(부서)', 두 번째 인자는 '무엇을 할지(합계)'입니다.

        Map<String, Integer> salaryByDept = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(), Collectors.summingInt(value -> value.getSalary())));
        // 여기에 코드를 채워보세요!

        // 예상 출력: {개발팀=11000, 인사팀=3500, 마케팅=8500} (순서는 달라도 됨)
        System.out.println(salaryByDept);

        // --------------------------------------
        //
        List<String> scores = Arrays.asList(
                "Kim:85",
                "Lee:92",
                "Park:78",
                "Choi:92", // 동점자 처리는 신경 쓰지 말고 아무나 1명 나오면 됨
                "Jeong:88"
        );

        // 문제 풀이
        String topStudentName = scores.stream()
                .map(s -> {
                    String[] parts = s.split(":");
                    return new Student(parts[0], Integer.parseInt(parts[1]));
                })
                .sorted((o1, o2) -> o2.score - o1.score)
                .map(student -> student.name)
                .findFirst()
                .orElse("")
                ;
        // 힌트 1: "Kim:85"를 분리해서 이름과 점수를 가진 무언가로 바꿔야 함
        // 힌트 2: 점수를 기준으로 max()를 찾아야 함
        // 힌트 3: max()는 Optional을 반환함
        // .orElse("없음") 등으로 마무리

        // 예상 출력: Lee (혹은 Choi)
        System.out.println("1등: " + topStudentName);


    }

    static class Student {
        String name;
        int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }


    static class Employee {
        String name;
        String department;
        int salary;

        public Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public int getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}