package com.example.demo.common.code.registry;

public interface CommonCodeAware {
    default String getCodeValue(){
        return CommonCodeRegistry.getCodeValue(this);
    }

    default String getDescription(){
        return CommonCodeRegistry.getDescription(this);
    }
}
