package com.app.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Custom Exception Handler
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myLoginException(LoginException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> myUserException(UserException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BlogException.class)
	public ResponseEntity<MyErrorDetails> myBlogException(BlogException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommentsException.class)
	public ResponseEntity<MyErrorDetails> myCommentsException(CommentsException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	

//	Default Exception
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerException(NoHandlerFoundException e,WebRequest re){
			MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
			
			return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> anyException(Exception e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> manvException(MethodArgumentNotValidException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
}
