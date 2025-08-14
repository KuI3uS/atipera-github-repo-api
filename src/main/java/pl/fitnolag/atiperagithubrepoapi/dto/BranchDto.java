package pl.fitnolag.atiperagithubrepoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchDto {
    private String name;
    private String lastCommitSha;
}