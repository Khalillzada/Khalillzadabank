package com.example.khalillzadabank.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumAvailableStatus {
    ACTIVE(1) , DEACTIVE(0) , DELETED(3);

    public int value;
}
