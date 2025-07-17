package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        for(int i =0; i < 1000; i++) {
            memberController.save(Member.create("user "+i, "user"+i+"@naver.com"));
        }
    }



    void getList2(int num){
        System.out.println("--- getList() 단독 "+num+" 호출 시간 측정 시작 ---");

        // 1. 메소드 실행 시작 시점의 나노초 기록
        long startTime = System.nanoTime();

        // 2. 측정하고자 하는 메소드 호출
        List<MemberService.MemberDTO> resultList = memberController.getList();

        // 메소드 결과 처리 (원하는 출력 등)
        System.out.println("불러온 게시글 목록:");
//        resultList.forEach(System.out::println);

        // 3. 메소드 실행 종료 시점의 나노초 기록
        long endTime = System.nanoTime();

        // 4. 총 걸린 시간 계산 (나노초 단위)
        long durationNano = endTime - startTime;

        // 5. 나노초를 밀리초, 또는 초 단위로 변환하여 출력
        double durationMillis = (double) durationNano / 1_000_000.0; // 나노초 -> 밀리초
        double durationSeconds = (double) durationNano / 1_000_000_000.0; // 나노초 -> 초

        System.out.println("------------------------------------");
        System.out.printf("getList() 호출에 총 걸린 시간: %.3f ms\n", durationMillis); // 소수점 3자리까지 출력
        System.out.printf("getList() 호출에 총 걸린 시간: %.3f s\n", durationSeconds);
        System.out.println("------------------------------------");

    }

    @Test
    @Transactional
    void getTest(){
        getList2(1);
        getList2(2);
    }


    @Test
    @Transactional
    void getList(){
        System.out.println("--- getList() 단독 호출 시간 측정 시작 ---");

        // 1. 메소드 실행 시작 시점의 나노초 기록
        long startTime = System.nanoTime();

        // 2. 측정하고자 하는 메소드 호출
        List<MemberService.MemberDTO> resultList = memberController.getList();

        // 메소드 결과 처리 (원하는 출력 등)
        System.out.println("불러온 게시글 목록:");
//        resultList.forEach(System.out::println);

        // 3. 메소드 실행 종료 시점의 나노초 기록
        long endTime = System.nanoTime();

        // 4. 총 걸린 시간 계산 (나노초 단위)
        long durationNano = endTime - startTime;

        // 5. 나노초를 밀리초, 또는 초 단위로 변환하여 출력
        double durationMillis = (double) durationNano / 1_000_000.0; // 나노초 -> 밀리초
        double durationSeconds = (double) durationNano / 1_000_000_000.0; // 나노초 -> 초

        System.out.println("------------------------------------");
        System.out.printf("getList() 호출에 총 걸린 시간: %.3f ms\n", durationMillis); // 소수점 3자리까지 출력
        System.out.printf("getList() 호출에 총 걸린 시간: %.3f s\n", durationSeconds);
        System.out.println("------------------------------------");

    }
}