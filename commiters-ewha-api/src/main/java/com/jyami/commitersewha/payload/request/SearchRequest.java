package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.post.PostCategory;
import com.jyami.commitersewha.domain.projectPost.ProjectPostCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by jyami on 2020/10/03
 */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SearchRequest {
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 50;

    private int page = 1;
    private int size = DEFAULT_SIZE;
    private Sort.Direction direction = Sort.Direction.DESC;
    private ProjectPostCategory projectPostCategory = null;
    private PostCategory postCategory = null;
    private String title = null;
    private List<String> hashTag = null;

    public void setToValidateValue() {
        this.page = page <= 0 ? 1 : page;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public PageRequest toPageRequest() {
        setToValidateValue();
        return PageRequest.of(page - 1, size, direction, "createdDate");
    }
}
