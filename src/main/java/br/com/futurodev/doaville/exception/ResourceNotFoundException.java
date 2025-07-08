package br.com.futurodev.doaville.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotação faz com que o Spring retorne o status HTTP 404 (Not Found)
// sempre que esta exceção for lançada por um controller.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}