package study.enumsetter.code2;

import lombok.Getter;

import java.util.function.Function;

@Getter
public enum CalculatorType {
    CALC_A(value -> value),
    CALC_B(value -> value * 10),
    CALC_C(value -> value * 3),
    CALC_D(value -> 0L);

    private Function<Long, Long> function;

    CalculatorType(Function<Long, Long> function) {
        this.function = function;
    }

    public long calculate(long value){
        return function.apply(value);
    }
}
