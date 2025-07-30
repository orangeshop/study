package study.chapter2;

import study.chapter1.notifier.Notifier;

import java.util.List;

public class ConcatName extends NotifierPoly {

    private String name;

    public ConcatName(List<Notifier> notifiers, String name) {
        super(notifiers);
        this.name = name;
    }

    @Override
    protected void concatMessage(String msg) {
        System.out.println(name + " " + msg);
    }

    @Override
    public NotifierPoly check() {
        return this;
    }
}
