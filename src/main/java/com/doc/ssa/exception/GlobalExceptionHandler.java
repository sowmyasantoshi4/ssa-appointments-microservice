package com.doc.ssa.exception;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = AccessDeniedException.class)
    public @ResponseBody ErrorResponse handleConflict(HttpServletResponse response) throws IOException {
		log.info("IN GlobalExceptionHandler :: handleConflict");
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Invalid Token");
    }
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse handleException(GlobalException e) {
		log.info("IN ExceptionHandler :: handleException");
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
	
	@ExceptionHandler(value = GlobalException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse handleGlobalException(GlobalException e) {
		log.info("IN GlobalExceptionHandler :: handleException");
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public @ResponseBody ErrorResponse illegalArgument(IllegalArgumentException e) {
		log.info("IN GlobalExceptionHandler :: illegalArgument");
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
	}
	
	@ExceptionHandler(value=BindException.class)
	public @ResponseBody ErrorResponse validationException(BindException e) {
		log.info("IN GlobalExceptionHandler :: BindException");
		StringBuilder err = new StringBuilder();
		for(ObjectError er : e.getBindingResult().getAllErrors()) {
			err.append(er.getDefaultMessage()+"\n");
		}
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),err.toString());
	}
	
	@ExceptionHandler(value= HttpMessageNotReadableException.class)
	public @ResponseBody ErrorResponse jsonError(HttpMessageNotReadableException e) {
		System.out.println("44444444444444444");
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
	}
	
	@ExceptionHandler(value= MissingRequestHeaderException.class)
	public @ResponseBody ErrorResponse missingHeader(MissingRequestHeaderException e) {
		log.info("IN GlobalExceptionHandler :: missingHeader");
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
	}
	
	/*
	 * @ExceptionHandler(value= NullPointerException.class) public @ResponseBody
	 * ErrorResponse nullPOinterException(NullPointerException e) {
	 * log.info("IN GlobalExceptionHandler :: nullPOinterException");
	 * e.printStackTrace(); return new
	 * ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage()); }
	 */
	/*
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionWrapper> validationException(IllegalArgumentException exception) {
		ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.BAD_REQUEST.toString(),
				exception.getMessage());

		return new ResponseEntity<ExceptionWrapper>(exceptionWrapper, HttpStatus.BAD_REQUEST);
	}
*/
}
