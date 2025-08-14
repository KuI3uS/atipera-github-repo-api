package pl.fitnolag.atiperagithubrepoapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.fitnolag.atiperagithubrepoapi.dto.ErrorResponseDto;
import pl.fitnolag.atiperagithubrepoapi.exception.UserNotFoundException;

@SpringBootApplication
public class AtiperaGithubRepoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtiperaGithubRepoApiApplication.class, args);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(404, ex.getMessage()));
    }
}
