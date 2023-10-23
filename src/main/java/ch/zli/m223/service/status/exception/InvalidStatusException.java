package ch.zli.m223.service.status.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No Status name provided")
public class InvalidStatusException extends RuntimeException {

}
