package br.com.mtonon.siclientes.controller.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.mtonon.siclientes.exception.ApiErrors;

@RestControllerAdvice(basePackages = "br.com.mtonon.siclientes.controller")
public class ValidationControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErrors( MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<String> messages = result.getAllErrors()
			.stream()
			.map(objectError -> objectError.getDefaultMessage())
			.collect(Collectors.toList());
		return new ApiErrors(messages);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseExceptionErrors( ResponseStatusException ex ) {
		String msg = ex.getMessage();
		HttpStatusCode codigoStatus = ex.getStatusCode();
		ApiErrors apiErrors = new ApiErrors(msg);
		return new ResponseEntity(msg, codigoStatus);
	}

}
