package com.tm.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.tm.response.Cambio;

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