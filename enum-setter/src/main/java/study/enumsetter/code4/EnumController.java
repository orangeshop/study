package study.enumsetter.code4;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnumController {

//    @Autowired
//    private EnumMapper enumMapper;

    private final EnumMapper enumMapper;

    public EnumController(EnumMapper enumMapper) {
        this.enumMapper = enumMapper;
    }

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("FeeType", FeeType.class);
        return enumMapper;
    }

    @GetMapping("/categories")
    public List<EnumMapperValue> getCategories(){
        return enumMapper.get("FeeType");
    }
}
