package study.chapter1.notifier;

import study.NotifyEnum;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Call EMAIL " + message);
    }

    @Override
    public boolean check(NotifyEnum notifyEnum) {
        return NotifyEnum.EMAIL.equals(notifyEnum);
    }
}
