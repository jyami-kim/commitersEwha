package com.jyami.commitersewha.payload.request;

import lombok.Getter;
import org.springframework.data.domain.Sort;

/**
 * Created by jyami on 2020/09/30
 */
@Getter
public final class PageRequest {
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 50;

    private int page;
    private int size;
    private Sort.Direction direction;

    public PageRequest(Integer page, Integer size, Sort.Direction direction) {
        this.page = (page == null) ? 1 : page;
        this.size = (size == null) ? DEFAULT_SIZE : size;
        this.direction = (direction == null) ? Sort.Direction.DESC : direction;
        validatePagenation();
    }

    private void validatePagenation() {
        this.page = page <= 0 ? 1 : page;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "createdDate");
    }

}

