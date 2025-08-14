package pl.fitnolag.atiperagithubrepoapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.fitnolag.atiperagithubrepoapi.dto.RepositoryDto;
import pl.fitnolag.atiperagithubrepoapi.service.GitHubService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping("/users/{username}/repos")
    public List<RepositoryDto> getRepositories(@PathVariable String username) {
        return gitHubService.getNonForkRepositories(username);
    }
}