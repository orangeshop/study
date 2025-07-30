package study.chapter1;

import study.NotifyEnum;
import study.chapter1.notifier.Notifier;

import java.util.ArrayList;
import java.util.List;

public abstract class NotifierPoly {

    List<Notifier> notifiers = new ArrayList<>();

    public NotifierPoly(List<Notifier> notifiers) {
        this.notifiers = notifiers;
    }

    public void Dived(NotifyEnum notifyEnum, String message) {
        for (Notifier notifier : notifiers) {
            if(notifier.check(notifyEnum)){
                notifier.send(message);
            }
        }
    }

    protected abstract void checkMessage(String msg);
}
