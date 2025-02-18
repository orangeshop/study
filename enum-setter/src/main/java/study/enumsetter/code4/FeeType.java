package study.enumsetter.code4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeeType implements EnumMapperType{

    PERCENT("정율"),
    MONEY("정액");

    private String title;

    @Override
    public String getCode() {
        return "";
    }

    @Override
    public String getTitle() {
        return "";
    }
}
