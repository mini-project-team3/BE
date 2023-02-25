package com.sparta.be.entity;

import lombok.Getter;

@Getter
public enum CategoryType {
    C1(1, "인문"),
    C2(2, "사회"),
    C3(3, "과학"),
    C4(4, "문학"),
    C5(5, "예술"),
    C6(6, "가정"),
    C7(7, "어린이");


    private int code;
    private String categoryName;

    CategoryType(int code, String categoryName) {
        this.code = code;
        this.categoryName = categoryName;
    }

}
