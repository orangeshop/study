package study.enumsetter.code4;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumMapper {

    public EnumMapper() {}

    // 1. 등록된 Enum 정보를 저장할 맵
    private Map<String, Class<? extends EnumMapperType>> factory = new LinkedHashMap<>();

    // 2. Enum 클래스를 맵에 등록
    public void put(String key, Class<? extends EnumMapperType> e) {
        factory.put(key, e);
    }

    // 3. 특정 Enum 클래스를 EnumMapperValue 리스트로 변환
    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e){
        return Arrays.stream(e.getEnumConstants())       // Enum 상수 배열을 스트림으로
                .map(EnumMapperValue::new)              // 각 상수를 EnumMapperValue로 매핑
                .collect(Collectors.toList());          // List로 수집
    }

    // 4. 등록된 모든 Enum을 한 번에 변환하여 반환
    public Map<String, List<EnumMapperValue>> getAll() {
        return factory.keySet().stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> toEnumValues(factory.get(key))
                ));
    }

    // 5. 특정 키에 해당하는 Enum만 변환하여 반환
    public List<EnumMapperValue> get(String key) {
        if (!factory.containsKey(key)) {
            return new ArrayList<>();
        }
        return toEnumValues(factory.get(key));
    }
}