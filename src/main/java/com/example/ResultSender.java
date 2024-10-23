package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ResultSender {
    public void sendResult(Result result) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/v1/result");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = httpClient.execute(post);
        response.close();
        httpClient.close();
    }
}

