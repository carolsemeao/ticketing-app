package ch.zli.m223.service.status.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Status not found")
public class InvalidIdException extends RuntimeException {
    
}
