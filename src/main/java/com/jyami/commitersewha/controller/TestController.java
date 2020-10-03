package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jyami.commitersewha.payload.ResponseMessage.GET_POST_LIST;

/**
 * Created by jyami on 2020/10/03
 */
@RequestMapping("test")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {


    @GetMapping("posts")
    public ResponseEntity<?> getAllPostWithPage(SearchRequest searchRequest) {
        log.info("---getAllPage : parameter = {}", searchRequest);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, GET_POST_LIST));
    }
}