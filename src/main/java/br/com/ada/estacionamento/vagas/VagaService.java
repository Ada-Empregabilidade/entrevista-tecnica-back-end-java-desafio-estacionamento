package br.com.ada.estacionamento.vagas;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    @Autowired
    public VagaRepository repository;

    @PostConstruct
    public void criarVagas(){
        for (int i = 0; i < 10; i++){
            repository.save(new Vaga());
        }
    }
    public Boolean verificarSeExisteVagaDisponivel(){
//        Iterable<Vaga> all = repository.findAll();
//        for(Vaga v: all){
//            if(!v.isOcupada()){
//               return true;
//            }
//        }
//        return false;
        return repository.numeroDisponivel()>0;
    }


    public List<Vaga> encontraTodas() {
        List<Vaga> listaComTodos =  repository.findAll();
        return listaComTodos;
    }
}
