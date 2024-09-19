package pub.wii.cook.springboot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.wii.cook.springboot.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object businessException(Throwable ex, HttpServletRequest request) throws Throwable {
        log.info("ckpt; here");
        if (ex instanceof BadRequestException) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
