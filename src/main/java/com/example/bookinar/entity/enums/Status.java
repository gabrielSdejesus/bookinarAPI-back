package com.example.bookinar.entity.enums;

import java.util.Arrays;

public enum Status {
    DISABLED(0),
    ACTIVE(1);

    private final Integer status;

    Status(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public static Status getTipoStatus(Integer status) {
        return Arrays.stream(Status.values())
                .filter(sts -> sts.getStatus().equals(status))
                .findFirst()
                .get();
    }
}
