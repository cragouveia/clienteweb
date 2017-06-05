package br.com.quantati.clienteweb.repository;

import br.com.quantati.clienteweb.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by carlos on 31/05/2017.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByChave(String chave);

    Long deleteClienteById(Long id);

}
