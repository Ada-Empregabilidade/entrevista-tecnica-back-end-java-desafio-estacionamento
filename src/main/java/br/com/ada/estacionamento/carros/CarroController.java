package br.com.ada.estacionamento.carros;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarroController {

    public final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCarro(@RequestBody Carro carro) {
        try {
            service.cadastrarCarro(carro);
            return ResponseEntity.created(null).body(carro);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/estacionar/{idCarro}")
    public ResponseEntity<?> estacionar(@PathVariable String idCarro,@RequestParam Integer idVaga){
        try {
            Carro carro =service.estacionar(idCarro, idVaga);
            return ResponseEntity.ok(carro);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PatchMapping("/retirar/{id}")
    public ResponseEntity<?> retirarCarro(@PathVariable String id){
        try {
            service.retirarCarro(id);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/verifica/carros/")
    public List<Carro> buscarTodos(){
        List<Carro> listaComTodos = service.encontraTodos();
        return listaComTodos;
    }

}
