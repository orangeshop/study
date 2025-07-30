package study.chapter1;

import study.NotifyEnum;
import study.chapter1.notifier.EmailNotifier;
import study.chapter1.notifier.SmsNotifier;

import java.util.List;

public class ChapterOneMain {
    public static void main(String[] args) {
        NotifierProcessor notify = new NotifierProcessor(
                List.of(new EmailNotifier(), new SmsNotifier())
        );
        notify.Dived(NotifyEnum.EMAIL, "테스트 메시지");
    }
}