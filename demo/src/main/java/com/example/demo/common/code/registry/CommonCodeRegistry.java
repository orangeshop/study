package com.example.demo.common.code.registry;

import com.example.demo.common.code.mapper.CommonCodeMapper;
import com.example.demo.common.code.model.CommonCode;
import com.example.demo.common.code.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommonCodeRegistry implements ApplicationRunner {

    private final CommonCodeMapper commonCodeMapper;

    private static final Map<Enum<?>, CommonCode> STORE = new ConcurrentHashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        reload();
    }

    private void reload() {
        STORE.clear();
        registerEnum(PaymentStatus.class);
    }

    private <E extends Enum<E> & CommonCodeAware> void registerEnum(Class<E> enumClass) {
        CommonCodeGroup annotation = enumClass.getAnnotation(CommonCodeGroup.class);
        if (annotation == null) return;

        String groupCode = annotation.value();
        List<CommonCode> codes = commonCodeMapper.findByGroupCode(groupCode);

        for (CommonCode code : codes) {
            try {
                E enumValue = Enum.valueOf(enumClass, code.getCodeName());
                STORE.put(enumValue, code);
            } catch (IllegalArgumentException e) {
                log.warn("DB 코드 '{}'에 매핑되는 enum 상수가 없습니다: {}",
                        code.getCodeName(), enumClass.getSimpleName());
            }
        }
    }

    public static String getCodeValue(Object enumValue) {
        CommonCode code = STORE.get(enumValue);
        return code != null ? code.getCodeValue() : null;
    }

    public static String getDescription(Object enumValue) {
        CommonCode code = STORE.get(enumValue);
        return code != null ? code.getDescription() : null;
    }
}
