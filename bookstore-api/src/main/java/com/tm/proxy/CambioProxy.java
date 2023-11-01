package com.tm.proxy;

import com.tm.response.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cambio-service", url = "http://localhost:8082")//
@Component
public interface CambioProxy {
	
		@GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
//		@RequestMapping(method = RequestMethod.GET, value = "/cambio-service/{amount}/{from}/{to}")
		Cambio getCambio(
			@PathVariable("amount") Double amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to);
}