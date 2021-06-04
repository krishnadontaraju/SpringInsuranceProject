package com.morganstanley.iwp.exceptionhandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.morganstanley.iwp.exception.CategoryException;
import com.morganstanley.iwp.exception.PurchaseException;
import com.morganstanley.iwp.exception.UserException;
import com.morganstanley.iwp.response.ResponseDTO;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class ExceptionHandler {
	
	private static final String message = "Exception while processing request";
	
	@org.springframework.web.bind.annotation.ExceptionHandler(CategoryException.class)
    public ResponseEntity<ResponseDTO> handleInsuranceCategoryException (CategoryException exception){

        log.error("Contact Exception ," ,exception);

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing ", exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseDTO> handleUserExceptions (UserException exception){

        log.error("Contact Exception ," ,exception);

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing ", exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PurchaseException.class)
    public ResponseEntity<ResponseDTO> handleInsuranceCreationExceptions (PurchaseException exception){

        log.error("Contact Exception ," ,exception);

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing ", exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleHttpNotReadableException(HttpMessageNotReadableException exception){

        log.error("Wrong HTTP regquested" , exception);
        ResponseDTO responseDTO = new ResponseDTO(message , "HTTP method requested was not correct");
        return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleArgumentNotValidExceptionEntity (MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        log.error(String.valueOf(errorMessage));

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing ", errorMessage);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }

     @org.springframework.web.bind.annotation.ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity<ResponseDTO> handleInCorrectQueryException(SQLGrammarException exception){

        ResponseDTO responseDTO = new ResponseDTO(exception.getMessage() ,"The Sql Query you gave has a problem please check and do again" );

        log.error("Sql Query exception " , exception);
        return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DateTimeParseException.class)
    public  ResponseEntity<ResponseDTO> handleIncorrectDateTimeException(DateTimeParseException exception){

        ResponseDTO responseDTO = new ResponseDTO(exception.getMessage() , "Date should be of the format dd MMM yyyy");
        log.error("Date format exception " ,exception);
        return new ResponseEntity<ResponseDTO>(responseDTO , HttpStatus.BAD_REQUEST);

    }

}
