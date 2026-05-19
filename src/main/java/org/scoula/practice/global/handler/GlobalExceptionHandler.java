package org.scoula.practice.global.handler;

import org.scoula.practice.domain.ApiResponse;
import org.scoula.practice.global.exception.CustomException;
import org.scoula.practice.global.exception.error.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e){
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(ApiResponse.error(errorCode));
    }

    //원래는 spring security (filter)에서 처리
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingHeader(){
        return ResponseEntity.status(ErrorCode.INVALID_PARAMETER.getStatus()).body(ApiResponse.error(ErrorCode.INVALID_PARAMETER));
    }
}
