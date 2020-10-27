package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.payload.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyami on 2020/10/27
 */
@RestController
public class ErrorController {

    @GetMapping("/api/error")
    public DefaultResponse<?> handleError(@RequestParam("message") String message, @RequestParam("code") int code) {
        return DefaultResponse.of(code, message);
    }

}
