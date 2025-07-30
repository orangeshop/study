## Chapter One

이 프로젝트는 자바의 객체지향 원칙, 특히 \*\*다형성(Polymorphism)\*\*을 활용하여 여러 종류의 알림(Email, SMS 등)을 일관된 방식으로 처리하는 방법을 보여주는 간단한 예제입니다.

### 🎯 핵심 컨셉

이 코드는 다음과 같은 객체지향 설계 개념을 학습하는 것을 목표로 합니다.

1. **인터페이스 (Interface):** `Notifier` 인터페이스를 통해 '알림을 보낸다(`send`)'와 '자신의 타입을 확인한다(`check`)'는 공통된 규약(contract)을 정의합니다.
2. **구현체 (Implementation):** `EmailNotifier`,** **`SmsNotifier` 클래스가** **`Notifier` 인터페이스를 각자의 방식에 맞게 구체적으로 구현합니다.
3. **추상 클래스 (Abstract Class):** `NotifierPoly` 추상 클래스는 모든 알림 처리기들의 공통된 로직과 구조를 제공합니다. 이 클래스는** **`Notifier` 구현체들의 목록을 관리하고, 이들을 순회하며 조건에 맞는 알림을 보내는** **`Dived` 메서드를 포함합니다.
4. **상속 (Inheritance):** `NotifierProcessor`와** **`ConcatName` 클래스는** **`NotifierPoly`를 상속받아, 추상 메서드를 구현하거나 새로운 기능을 추가하여 자신만의 알림 처리기를 만듭니다.
5. **다형성 (Polymorphism):** `NotifierProcessor`는** **`EmailNotifier`나** **`SmsNotifier` 같은 구체적인 타입을 몰라도,** **`Notifier`라는 추상적인 타입에 의존하여** **`send`와** **`check` 메서드를 호출합니다.

### 📂 코드 구조

* **`Notifier.java` (인터페이스):**
  * `send(String message)`: 메시지 발송을 위한 추상 메서드.
  * `check(NotifyEnum notifyEnum)`: 자신의 타입이 맞는지 확인하는 추상 메서드.
* **`EmailNotifier.java` /** **`SmsNotifier.java` (구현 클래스):**
  * `Notifier` 인터페이스를 구현한 구체적인 알림 발송 클래스들입니다.
* **`NotifierPoly.java` (추상 클래스):**
  * `List<Notifier> notifiers`:** **`EmailNotifier`,** **`SmsNotifier` 등 여러 구현체를 담는 리스트입니다.
  * `Dived(NotifyEnum, String)`:** **`notifiers` 리스트를 순회하며,** **`check` 메서드로 올바른 구현체를 찾아** **`send` 메서드를 호출하는 핵심 로직을 담고 있습니다.
* **`NotifierProcessor.java` /** **`ConcatName.java` (구현 클래스):**
  * `NotifierPoly`를 상속받아 완성된 구체적인 알림 처리기 클래스입니다.
* **`ChapterOneMain.java` (실행 클래스):**
  * `NotifierProcessor`를 생성하고, 실제 알림 발송을 테스트하는 메인 클래스입니다.

### 🚀 실행 방법

1. 프로젝트를 컴파일하고** **`ChapterOneMain` 클래스를 실행합니다.
2. 콘솔에 다음과 같은 결과가 출력되는 것을 확인할 수 있습니다.
   ```
   Call EMAIL 테스트 메시지
   ```

### 🤔 설계에 대한 고찰 (Points to Consider)

현재** **`Dived` 메서드는** **`for`문을 순회하며** **`notifier.check()`를 통해 적절한 구현체를 찾는 방식을 사용합니다. 이 방법은 다형성을 활용하는 하나의 예시이지만, 다음과 같은 개선점을 생각해 볼 수 있습니다.

* **효율성:** Notifier의 종류가 많아지면** **`for`문을 매번 순회하는 것은 비효율적일 수 있습니다 (O(n)).
* **책임:** `Dived` 메서드가 '어떤 Notifier를 선택할지' 결정하는 로직을 직접 가지고 있습니다.

더 발전된 설계에서는** ****`Map<NotifyEnum, Notifier>`** 자료구조를 사용하여** **`if`문 없이 O(1)의 속도로 원하는 Notifier를 즉시 찾아내는 'Dispatcher' 패턴을 적용해볼 수 있습니다. 이 프로젝트는 그 발전된 설계를 위한 좋은 출발점이 됩니다.


## Chapter Two

이 프로젝트는 자바의** ****추상 클래스**와** ****상속**을 활용하여, 전체적인 처리 흐름(템플릿)은 부모가 정의하되 세부적인 내용은 자식이 구현하는 \*\*템플릿 메서드 패턴(Template Method Pattern)\*\*을 보여주는 간단한 예제입니다.

### 🎯 핵심 컨셉

1. **추상 클래스 (Abstract Class):** `NotifierPoly`는 메시지를 처리하는 큰 틀의 로직을 정의합니다.** **`Dived`와 같은 일반 메서드와 함께, 자식 클래스가 반드시 구현해야 하는** **`concatMessage`라는 추상 메서드를 포함합니다.
2. **상속 (Inheritance):** `ConcatName`과** **`ConcatPhoneNumber`는** **`NotifierPoly`를 상속받아,** **`concatMessage`라는 '미완성된' 부분을 각자의 방식('이름'을 붙이거나 '전화번호'를 붙이는 등)으로 구체화하여 완성시킵니다.
3. **다형성 (Polymorphism):** `main` 메서드에서** **`a`,** **`b`,** **`c` 변수는 모두** **`NotifierPoly`라는 추상 타입으로 선언되었지만, 실제로는** **`ConcatName`이나** **`ConcatPhoneNumber`와 같은 구체적인 객체를 담고 있습니다. 각 객체는** **`concatMessage` 호출에 대해 자신만의 방식으로 다르게 동작합니다.

### 📂 코드 구조

* **`NotifierPoly.java` (추상 클래스):**
  * 모든 자식 클래스가 공유하는** **`Dived` 메서드와** **`notifiers` 리스트를 가집니다.
  * `concatMessage(String msg)`라는 추상 메서드를 정의하여, 자식 클래스가 이 부분을 반드시 구현하도록 강제합니다.
* **`ConcatName.java` /** **`ConcatPhoneNumber.java` (구현 클래스):**
  * `NotifierPoly`를 상속받아** **`concatMessage` 메서드를 각자의 로직(이름 붙이기, 전화번호 붙이기)에 맞게 재정의(Override)합니다.
* **`ConcatIsChecked.java` (인터페이스):**
  * `check()` 메서드를 정의하며,** **`NotifierPoly`가 이 인터페이스를 구현합니다. 현재 코드에서는** **`this`를 반환하여 자기 자신을 확인하는 용도로 사용되고 있습니다.
* **`ChapterTwoMain.java` (실행 클래스):**
  * `ConcatName`과** **`ConcatPhoneNumber` 객체를 생성하고, 부모 타입인** **`NotifierPoly` 변수에 담아 각 메서드를 호출하며 다형적 동작을 테스트합니다.
