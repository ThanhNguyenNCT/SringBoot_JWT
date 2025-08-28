package Cybersoft.ExJPA_Security.exception;

import Cybersoft.ExJPA_Security.payload.respone.BaseRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralExceptions {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(500);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);
        return ResponseEntity.ok(baseRespone);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(404);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);
        return ResponseEntity.ok(baseRespone);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(DuplicateException e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(400);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);
        return ResponseEntity.ok(baseRespone);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInvalidException(InvalidException e) {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(402);
        baseRespone.setMessage(e.getMessage());
        baseRespone.setData(null);
        return ResponseEntity.ok(baseRespone);
    }

}
