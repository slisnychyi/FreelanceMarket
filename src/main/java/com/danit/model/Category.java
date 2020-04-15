package com.danit.model;

import lombok.Getter;

public enum Category {

    DEVELOPER(1), DESIGNER(2), TESTER(3), MANAGER(4);

    @Getter
    private final int id;

    Category(int id) {
        this.id = id;
    }
}
