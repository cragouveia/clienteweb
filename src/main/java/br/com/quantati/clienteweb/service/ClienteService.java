package br.com.quantati.clienteweb.service;

import br.com.quantati.clienteweb.repository.ClienteRepository;
import br.com.quantati.clienteweb.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by carlos on 01/06/2017.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository dao;

    @Transactional
    public Cliente save(Cliente cliente) {
        return dao.save(cliente);
    }

    @Transactional
    public Long deleteClienteById(long id) {
        return dao.deleteClienteById(id);
    }

    public List<Cliente> findByChave(String chave) {
        return dao.findByChave(chave);
    }
}
