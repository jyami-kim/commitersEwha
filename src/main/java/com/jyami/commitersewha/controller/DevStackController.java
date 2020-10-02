package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.tag.DevStack;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jyami.commitersewha.payload.ResponseMessage.GET_ALL_DEV_STACK_LIST;

/**
 * Created by jyami on 2020/09/30
 */
@RequestMapping("api/devStack")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DevStackController {

    private final TagService tagService;

    @GetMapping("")
    public ResponseEntity<?> getAllDevStackList() {
        List<DevStack> allDevStacks = tagService.getAllDevStacks();
        return ResponseEntity.ok().body(DefaultResponse.of(HttpStatus.OK, GET_ALL_DEV_STACK_LIST, allDevStacks));
    }
}
