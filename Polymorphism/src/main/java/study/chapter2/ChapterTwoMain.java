package study.chapter2;


import study.NotifyEnum;
import study.chapter1.notifier.EmailNotifier;

import java.util.List;

public class ChapterTwoMain {
    public static void main(String[] args) {

        NotifierPoly a = new ConcatPhoneNumber(List.of(), 123456);
        a.concatMessage("Main Message : Call A");

        NotifierPoly b = new ConcatName(List.of(new EmailNotifier()), "Jang");
        b.concatMessage("Main Message");
        b.Dived(NotifyEnum.EMAIL, "Main Message: Call B");

        NotifierPoly c = a.check();
        c.concatMessage("Main Message : Call A");

    }
}