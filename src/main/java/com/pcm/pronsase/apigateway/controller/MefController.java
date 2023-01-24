package com.pcm.pronsase.apigateway.controller;
import com.pcm.pronsase.apigateway.request.MefServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/mef")
public class MefController {
    @Autowired
    private MefServiceRequest mefServiceRequest;

    @PostMapping("gasto")
    public ResponseEntity<?> saveGasto(@RequestBody Object gasto)
    {
        return new ResponseEntity<>(mefServiceRequest.saveGasto(gasto), HttpStatus.CREATED);
    }

    @DeleteMapping("gasto/{gastoId}")
    public ResponseEntity<?> deleteGasto(@PathVariable("gastoId") Long gastoId)
    {
        mefServiceRequest.deleteGasto(gastoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("gasto")
    public ResponseEntity<?> getAllGasto()
    {
        return ResponseEntity.ok(mefServiceRequest.getAllGasto());
    }
    @GetMapping("gastoDiarioPorAnioMes/{anoEje}/{mesEje}/{page}/{size}")
    public ResponseEntity<?> getGastoDiarioPorAnioMes(@PathVariable Integer anoEje,@PathVariable Integer mesEje,@PathVariable Integer page,@PathVariable Integer size)
    {
        return ResponseEntity.ok(mefServiceRequest.getGastoDiarioPorAnioMes(anoEje,mesEje,page,size));
    }

}
