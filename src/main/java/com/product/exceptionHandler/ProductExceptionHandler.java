package com.product.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.product.vo.ProductErrorVO;

@ControllerAdvice
public class ProductExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProductErrorVO handleValidationError(Exception ex) {
        ProductErrorVO v =  new ProductErrorVO(ex.getClass(),ex.getMessage());
        return v;
	}
}
