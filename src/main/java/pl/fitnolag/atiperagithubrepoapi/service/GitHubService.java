package pl.fitnolag.atiperagithubrepoapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fitnolag.atiperagithubrepoapi.client.GitHubClient;
import pl.fitnolag.atiperagithubrepoapi.dto.BranchDto;
import pl.fitnolag.atiperagithubrepoapi.dto.RepositoryDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GitHubService {

    private final GitHubClient gitHubClient;

    public List<RepositoryDto> getNonForkRepositories(String username) {
        List<Map<String, Object>> repos = gitHubClient.getUserRepositories(username);

        return repos.stream()
                .filter(repo -> !(Boolean) repo.get("fork"))
                .map(repo -> {
                    String repoName = (String) repo.get("name");
                    String ownerLogin = (String) ((Map<String, Object>) repo.get("owner")).get("login");
                    List<BranchDto> branches = gitHubClient.getBranches(username, repoName)
                            .stream()
                            .map(branch -> new BranchDto(
                                    (String) branch.get("name"),
                                    (String) ((Map<String, Object>) branch.get("commit")).get("sha")
                            ))
                            .collect(Collectors.toList());
                    return new RepositoryDto(repoName, ownerLogin, branches);
                })
                .collect(Collectors.toList());
    }
}
