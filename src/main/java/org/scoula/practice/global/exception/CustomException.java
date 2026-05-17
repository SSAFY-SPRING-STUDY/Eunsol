package org.scoula.practice.global.exception;

import lombok.Getter;
import org.scoula.practice.global.exception.error.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
