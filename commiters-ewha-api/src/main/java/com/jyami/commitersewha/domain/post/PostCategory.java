package com.jyami.commitersewha.domain.post;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by jyami on 2020/10/02
 */
@Getter
public enum PostCategory {
    CHAT(0, "잡담"),
    EMPLOYMENT(1, "취업/이직"),
    REVIEW(2, "후기"),
    ADVERTISING(3, "홍보");

    PostCategory(Integer ordinal, String name) {
        this.ordinal = ordinal;
        this.name = name;
    }

    private Integer ordinal;
    private String name;

    public static Optional<PostCategory> getCategory(String category) {
        if (category == null) {
            return Optional.empty();
        }
        return Arrays.stream(PostCategory.values())
                .filter(c -> c.name.equals(category) || c.ordinal.toString().equals(category))
                .findAny();
    }
}
