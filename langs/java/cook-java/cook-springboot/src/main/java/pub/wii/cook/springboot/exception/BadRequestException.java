package pub.wii.cook.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
    HttpStatus status;
}
