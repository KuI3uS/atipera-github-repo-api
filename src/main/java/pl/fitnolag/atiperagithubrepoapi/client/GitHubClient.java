package pl.fitnolag.atiperagithubrepoapi.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.fitnolag.atiperagithubrepoapi.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.github.com";

    public List<Map<String, Object>> getUserRepositories(String username) {
        String url = BASE_URL + "/users/" + username + "/repos";
        try {
            ResponseEntity<List<Map<String, Object>>> response =
                    restTemplate.exchange(url, HttpMethod.GET, null,
                            new ParameterizedTypeReference<>() {});
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<Map<String, Object>> getBranches(String username, String repoName) {
        String url = BASE_URL + "/repos/" + username + "/" + repoName + "/branches";
        ResponseEntity<List<Map<String, Object>>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}