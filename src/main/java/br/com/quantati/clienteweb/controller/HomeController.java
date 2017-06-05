package br.com.quantati.clienteweb.controller;

import br.com.quantati.clienteweb.domain.Cliente;
import br.com.quantati.clienteweb.infra.FileSaver;
import br.com.quantati.clienteweb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by carlos on 31/05/2017.
 */
@Controller
public class HomeController {

    @Autowired
    private ClienteService service;

    @Autowired
    private FileSaver fileSaver;

    @RequestMapping("/")
    public String index(){
        return "home";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @CacheEvict(value = "listaCliente", allEntries = true)
    public synchronized String save(@RequestBody Cliente cliente) {
        try {
            cliente = service.save(cliente);
            fileSaver.store( cliente.getId() + ".png", Base64Utils.decodeFromString(cliente.getImage()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return String.format("{\"id\": %d}", cliente.getId());
    }

    @RequestMapping(value = "/findByChave/{chave}", method = RequestMethod.GET)
    @Cacheable(value = "listaCliente")
    @ResponseBody
    public List<Cliente> findByChave(@PathVariable("chave") String chave){
        List<Cliente> clientes = service.findByChave(chave);
        return clientes;
    }

    @RequestMapping(value = "/deleteById//{id}", method = RequestMethod.GET)
    @CacheEvict(value = "listaCliente", allEntries = true)
    @ResponseBody
    public String deleteById(@PathVariable("id") long id){
        long qtde = service.deleteClienteById(id);
        if (qtde > 0) {
            fileSaver.delete(id + ".png");
        }
        return String.format("{\"qtde\": %d}", qtde);
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resource getImage(@PathVariable("id") long id) throws IOException {
        return fileSaver.loadAsResource(id + ".png");
    }
}
