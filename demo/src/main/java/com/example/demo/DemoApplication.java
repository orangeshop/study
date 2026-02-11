package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

/*
하려고 하는거 공통 코드 + enum을 같이 쓰고 싶은데

결국 사람이 enum에서 상태를 맞춰줘야 하는데 이런걸 없애고 싶기에 리플렉션으로 런타임에 주입을 할 생각

ex)
PAID(~~~, ~~~, 콩통 코드 value)

를 넣으려는 생각

이걸 리플렉션으로 넣어서 작업하면 될 듯?


* */