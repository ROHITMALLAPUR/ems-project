package Spring.API.EMS_Project.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String,String> FieldeErrors= new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error->FieldeErrors.put(error.getField(), error.getDefaultMessage()));

        ApiError apiError=new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setMessage("Validation Failed");
        apiError.setErrors(FieldeErrors);
        apiError.setPath(request.getRequestURI());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){

        ApiError apiError=new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.NOT_FOUND.value());
        apiError.setMessage(ex.getMessage());
        apiError.setPath(request.getRequestURI());
        logger.error("Resource not found : {}", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
