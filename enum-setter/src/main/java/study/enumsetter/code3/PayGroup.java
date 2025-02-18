package study.enumsetter.code3;

import lombok.Getter;

import java.util.*;

@Getter
public enum PayGroup {
    CASH("현금", Arrays.asList(GroupType.ACCOUNT_TRANSFER, GroupType.REMITTANCE, GroupType.ON_SITE_PAYMENT, GroupType.TOSS)),
    CARD("카드", Arrays.asList(GroupType.PAYCO, GroupType.CARD, GroupType.KAKAO_PAY, GroupType.BAEMIN_PAY)),
    ETC("기타", Arrays.asList(GroupType.POINT, GroupType.COUPON)),
    EMPTY("없음", Collections.EMPTY_LIST);

    private String title;
    private List<GroupType> ls;

    PayGroup(String title, List<GroupType> ls) {
        this.title = title;
        this.ls = ls;
    }

    public static PayGroup findByPayCode(GroupType code) {

        PayGroup result = Arrays.stream(PayGroup.values())
                .filter(payGroup -> payGroup.hashPayCode(code))
                .findAny()
                .orElse(EMPTY);

        System.out.println(result);


        return result;
    }

    public boolean hashPayCode(GroupType code) {
        return ls.stream()
                .anyMatch(pay -> pay.equals(code));
    }
}
