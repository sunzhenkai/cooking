package pub.wii.cook.springboot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.wii.cook.springboot.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UnexpectedExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object businessException(Throwable ex, HttpServletRequest request) throws Throwable {
        log.error("unexpected exception occurred", ex);
//        throw new RuntimeException(ex);
        throw ex;
    }
}
