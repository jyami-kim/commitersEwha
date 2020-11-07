package com.jyami.commitersewha.job.reader;

import com.jyami.commitersewha.config.payload.RepositoryResponse;
import com.jyami.commitersewha.config.restTemplate.GithubRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/11/07
 */
@RequiredArgsConstructor
public class GitRESTRepositoryReader implements ItemReader<List<RepositoryResponse>> {

    private final GithubRestTemplate githubRestTemplate;
    private final String accessToken;

    private int nextPageIndex = 0;
    private List<RepositoryResponse> repositoryResponse = new ArrayList<>();
    private boolean next = true;

    @Override
    public List<RepositoryResponse> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (next) {
            ResponseEntity<List<RepositoryResponse>> userRepositories = githubRestTemplate.getUserRepositories(accessToken, nextPageIndex);
            if (userRepositories.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("status not OK");
            }
            List<RepositoryResponse> body = userRepositories.getBody();
            if (body.size() == 0)
                next = false;
            repositoryResponse = body;
            nextPageIndex++;
        }
        return repositoryResponse;
    }
}
