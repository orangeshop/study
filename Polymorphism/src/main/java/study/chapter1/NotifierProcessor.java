package study.chapter1;

import study.chapter1.notifier.Notifier;

import java.util.List;

public class NotifierProcessor extends NotifierPoly{
    public NotifierProcessor(List<Notifier> notifiers) {
        super(notifiers);
    }

    @Override
    protected void checkMessage(String msg) {
        System.out.println("NotifierPolyExtend: " + msg);
    }
}
