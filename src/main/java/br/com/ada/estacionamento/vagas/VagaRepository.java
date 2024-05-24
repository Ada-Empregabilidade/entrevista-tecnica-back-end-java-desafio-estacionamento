package br.com.ada.estacionamento.vagas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {
    @Query("SELECT count(v.numero) FROM Vaga v where v.ocupada = false")
    Integer numeroDisponivel();

}
