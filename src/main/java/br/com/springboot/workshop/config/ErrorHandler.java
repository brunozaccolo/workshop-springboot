package br.com.springboot.workshop.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.springboot.workshop.exception.BadRequestException;
import br.com.springboot.workshop.exception.BusinessException;
import br.com.springboot.workshop.exception.ErrorDTO;
import br.com.springboot.workshop.exception.ErrorDetailDTO;



/**
 * Esta classe configura tipo de saída padrão caso haja algum erro (esperado ou inesperado)
 */
@ControllerAdvice //intercepta errors
public class ErrorHandler {

	public static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(BusinessException.class)
	public @ResponseBody ErrorDTO handleBusinessException(HttpServletRequest req, Exception ex) {
		log.error(ex.getMessage());
		return new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class, })
	public @ResponseBody ErrorDTO handleBadRequest(HttpServletRequest req, Exception ex) {
		final List<ErrorDetailDTO> details = this.buildItems(((MethodArgumentNotValidException) ex).getBindingResult());
		log.error(details.toString());
		return new ErrorDTO(HttpStatus.BAD_REQUEST, "Bad Request", details);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorDTO handleInternalException(HttpServletRequest req, Exception ex) {
		log.error(ex.getMessage());
		return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
	private List<ErrorDetailDTO> buildItems(final BindingResult bindingResult) {
		
		if (bindingResult.getFieldErrors().isEmpty())
			return null;
		
		final List<ErrorDetailDTO> details = new ArrayList<ErrorDetailDTO>();
		
		bindingResult.getFieldErrors().stream().forEach(error -> {
			details.add(new ErrorDetailDTO(error.getField(), error.getDefaultMessage()));
		});
		
		return details;
	}
}
