package study.chapter2;

import study.chapter1.notifier.Notifier;

import java.util.List;

public class ConcatPhoneNumber extends NotifierPoly {

    private int phoneNumber;

    public ConcatPhoneNumber(List<Notifier> notifiers, int phoneNumber) {
        super(notifiers);
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void concatMessage(String msg) {
        System.out.println(phoneNumber + " " + msg);
    }

    @Override
    public NotifierPoly check() {
        return this;
    }
}
