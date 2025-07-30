package study.chapter1.notifier;

import study.NotifyEnum;

public class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Call SMS " + message);
    }

    @Override
    public boolean check(NotifyEnum notifyEnum) {
        return NotifyEnum.SMS.equals(notifyEnum);
    }
}
