package study.enumsetter.code3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PayGroupTest {

    @Test
    public void payGroup() {
        GroupType payCode = GroupType.BAEMIN_PAY;
        PayGroup payGroup = PayGroup.findByPayCode(payCode);

        assertThat(payGroup.name()).isSameAs("CARD");
        assertThat(payGroup.getTitle()).isSameAs("카드");
    }
}