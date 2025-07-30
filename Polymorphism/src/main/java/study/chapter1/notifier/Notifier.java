package study.chapter1.notifier;

import study.NotifyEnum;

public interface Notifier {
    void send(String message);
    boolean check(NotifyEnum notifyEnum);
}
