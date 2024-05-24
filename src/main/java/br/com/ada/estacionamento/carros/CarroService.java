package br.com.ada.estacionamento.carros;

import br.com.ada.estacionamento.vagas.Vaga;
import br.com.ada.estacionamento.vagas.VagaRepository;
import br.com.ada.estacionamento.vagas.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {


    public CarroRepository carroRepository;
    public VagaRepository vagaRepository;
    public VagaService vagaService;

    public CarroService(CarroRepository carroRepository, VagaRepository vagaRepository, VagaService vagaService){
        this.carroRepository=carroRepository;
        this.vagaRepository=vagaRepository;
        this.vagaService =vagaService;
    }

    public void cadastrarCarro(Carro carro){
        if(vagaService.verificarSeExisteVagaDisponivel()) {
            carroRepository.save(carro);
            return;
        }
        throw new RuntimeException("Não existem vagas disponíveis.");
    }

    public Carro estacionar(String idCarro, Integer idVaga){
        Optional<Vaga> byIdVaga = vagaRepository.findById(idVaga);
        Optional<Carro> byIdCarro = carroRepository.findById(idCarro);
        if(byIdVaga.isPresent()&&byIdCarro.isPresent()) {
            Vaga vaganova = byIdVaga.get();
            if (!vaganova.isOcupada()) {
                Carro carro=byIdCarro.get();
                carro.setVaga(vaganova);
                carroRepository.save(carro);
                vaganova.setOcupada(true);
                vagaRepository.save(vaganova);
                return carro;
            }
        }
        throw new RuntimeException("Vaga já está ocupada ou não foi localizada.");

    }


    public void retirarCarro(String id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if(carroOptional.isPresent()){
            Carro carro= carroOptional.get();
            Optional<Vaga> vagaCarro= vagaRepository.findById(carro.getVaga().getNumero());
            if(vagaCarro.isPresent()) {
                carro.setVaga(null);
                vagaCarro.get().setOcupada(false);
                carroRepository.save(carro);
                vagaRepository.save(vagaCarro.get());

            }
        }

    }

    public List<Carro> encontraTodos() {
        List<Carro> listaComTodos =  carroRepository.findAll();
        return listaComTodos;
    }
}
