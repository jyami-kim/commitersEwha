package com.jyami.commitersewha.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by jyami on 2020/09/22
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultResponse<T> {


    public static final DefaultResponse FAIL_DEFAULT_RES
            = DefaultResponse.of(ResponseCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
    private int status;
    private String message;
    private T response;

    public DefaultResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private DefaultResponse(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public static DefaultResponse<?> of(@NotNull int status, String message) {
        return new DefaultResponse<>(status, message, null);
    }

    public static <T> DefaultResponse<T> of(@NotNull int status, String message, T response) {
        return new DefaultResponse<>(status, message, response);
    }
}
