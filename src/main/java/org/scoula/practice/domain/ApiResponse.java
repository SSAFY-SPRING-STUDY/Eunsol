package org.scoula.practice.domain;

import org.scoula.practice.global.exception.error.ErrorCode;

public record ApiResponse<T>(String message, T data) {

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>("success", data);
    }

    public static ApiResponse<Void> success(){
        return new ApiResponse<>("success", null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode){
        return new ApiResponse<>(errorCode.getMessage(), null);
    }
}
