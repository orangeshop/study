package study.enumsetter.code2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.linesOf;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTypeTest {
    @Test
    public void calculatorType() {
        CalculatorType origin_a = CalculatorType.CALC_A;
        CalculatorType origin_b = CalculatorType.CALC_B;
        CalculatorType origin_c = CalculatorType.CALC_C;
        CalculatorType origin_d = CalculatorType.CALC_D;

        long value_a = origin_a.calculate(10);
        long value_b = origin_b.calculate(10);
        long value_c = origin_c.calculate(10);
        long value_d = origin_d.calculate(10);

        assertThat(value_a).isEqualTo(10L);
        assertThat(value_b).isSameAs(100L);
        assertThat(value_c).isSameAs(30L);
        assertThat(value_d).isSameAs(0L);

    }
}