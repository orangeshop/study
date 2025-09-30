# 값 객체(Value Object) 패턴 학습 프로젝트

도메인 주도 설계(DDD)의 핵심 구성요소 중 하나인 값 객체(Value Object) 패턴을 학습하고, 이를 Java로 구현하는 프로젝트입니다.

## 🎯 목적

-   값 객체의 두 가지 핵심 특징인 '불변성(Immutability)'과 '동등성(Equality)'을 이해합니다.
-   `String`이나 `int`와 같은 원시 타입을 그대로 사용하는 것(Primitive Obsession)이 아니라, 의미를 명확히 나타내는 값 객체로 포장하는 것의 이점을 학습합니다.
-   `equals()`와 `hashCode()` 메소드를 올바르게 오버라이딩하여 값에 의한 동등성 비교를 구현합니다.
-   값 객체를 사용함으로써 코드의 가독성, 안정성, 표현력이 어떻게 향상되는지 체감합니다.

## 🛠️ 기술 스택

*   **Language**: Java
*   **Testing**: JUnit 5

## 📖 핵심 학습 내용

이 프로젝트는 '돈(Money)', '주소(Address)', '이름(Name)' 등과 같이 도메인에서 의미를 가지는 값을 별도의 객체로 만드는 과정을 다룹니다.

**예시: `Money` 값 객체**
`int price` 대신 `Money price`를 사용함으로써, `price`가 단순한 숫자가 아니라 '금액'이라는 도메인 의미를 가짐을 명확히 할 수 있습니다.

```java
// 나쁜 예: 원시 타입 사용
int price = 1000;
int deliveryFee = 3000;
int total = price + deliveryFee; // 숫자들의 합일 뿐, 의미가 불분명하다.

// 좋은 예: 값 객체 사용
Money price = new Money(1000);
Money deliveryFee = new Money(3000);
Money total = price.add(deliveryFee); // 돈(Money)과 돈을 더한다는 의미가 명확하다.
```

`Money` 객체는 내부에 금액과 관련된 비즈니스 로직(예: `add`, `subtract`, `isGreaterThan` 등)을 포함할 수 있으며, 생성된 이후에는 상태가 변하지 않는 불변 객체로 만들어 코드의 부작용(Side Effect)을 방지합니다.