package com.avijeet.openshelf.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean isError;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(true,message,null);
    }
}
