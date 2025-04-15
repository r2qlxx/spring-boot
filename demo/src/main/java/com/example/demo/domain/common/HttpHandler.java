package com.example.demo.domain.common;

import com.example.demo.config.WebProps;
import com.example.demo.domain.object.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HttpHandler {
    private final RestClient restClient;  // WebClient is recommended for async process.
    private final WebProps webProps;

    public ResponseEntity<Post> get(int id) {
        ResponseEntity<Post> res = restClient.get()
                .uri(webProps.getPath(), id)    // path = webProps.getPath()
                .retrieve()
                .toEntity(Post.class);

        // res.getStatusCode();
        // res.getHeaders();
        // res.getBody();
        return res;
    }

    public ResponseEntity<Post> post(Post post) {
        return restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .toEntity(Post.class);
    }

    public ResponseEntity<Post> put(int id, Post post) {
        return restClient.put()
                .uri(webProps.getPath(), id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .toEntity(Post.class);
    }

    public ResponseEntity<Post> patch(int id, Map<String, Object> body) {
        return restClient.patch()
                .uri(webProps.getPath(), id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toEntity(Post.class);
    }

    public ResponseEntity<Post> delete(int id) {
        return restClient.delete()
                .uri(webProps.getPath(), id)
                .retrieve()
                .toEntity(Post.class);
    }
}
