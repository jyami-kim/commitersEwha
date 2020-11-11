package com.jyami.commitersewha.githubRestTemplate;

import com.jyami.commitersewha.exception.RestTemplateResponseException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * Created by jyami on 2020/11/01
 */

@Slf4j
@NoArgsConstructor
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return hasServerError(httpResponse) || hasClientError(httpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (hasError(httpResponse)) {
            String responseBody = IOUtils.toString(httpResponse.getBody(), StandardCharsets.UTF_8.name());
            log.info("API CALL ERROR {}: {}", httpResponse.getRawStatusCode(), httpResponse.getStatusText());
            throw new RestTemplateResponseException(responseBody, httpResponse.getRawStatusCode());
        }
    }

    private boolean hasServerError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == SERVER_ERROR;
    }

    private boolean hasClientError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == CLIENT_ERROR;
    }

}