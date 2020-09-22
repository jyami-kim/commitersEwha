package com.jyami.commitersewha.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by jyami on 2020/09/22
 */
@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
