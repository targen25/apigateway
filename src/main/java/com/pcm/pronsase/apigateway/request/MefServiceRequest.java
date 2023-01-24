package com.pcm.pronsase.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value="registrounico-service",
        path="/api/mef",
        url="${mef.service.url}",
        configuration = FeignConfiguration.class
)
public interface MefServiceRequest {

    @PostMapping("gasto")
    Object saveGasto(@RequestBody Object requestBody);
    @DeleteMapping("gasto/{gastoId}")
    void deleteGasto(@PathVariable("gastoId") Long gastoId);
    @GetMapping("gasto")
    List<Object> getAllGasto();

    @GetMapping("gastoDiarioPorAnioMes/{anoEje}/{mesEje}/{page}/{size}")
    List<Object> getGastoDiarioPorAnioMes(@PathVariable Integer anoEje, @PathVariable Integer mesEje,@PathVariable Integer page,@PathVariable Integer size);

}
