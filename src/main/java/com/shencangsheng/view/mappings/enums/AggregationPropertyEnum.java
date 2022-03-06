package com.shencangsheng.view.mappings.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AggregationPropertyEnum {
    STRING,
    TERMS,
    DATE,
    DOUBLE,
    GROUP,
    TERMS_CARDINALITY,
    CARDINALITY,
    LONG;
}
