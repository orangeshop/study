package com.example.demo.common.code.enums;

import com.example.demo.common.code.registry.CommonCodeAware;
import com.example.demo.common.code.registry.CommonCodeGroup;

@CommonCodeGroup("PAYMENT_STATUS")
public enum PaymentStatus implements CommonCodeAware {
    PAID, PENDING, CANCELED;
}
