package com.vgproject.vgp.controller;

import com.vgproject.vgp.model.Cliente;
import com.vgproject.vgp.repository.ClienteRepository;
import com.vgproject.vgp.repository.TelefoneRepository;
import com.vgproject.vgp.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

/**
 * author LucasDonizeti
 */
@RestController
@RequestMapping("cliente")
public class ClienteEndPoint {
    private final ClienteRepository clienteDAO;

    @Autowired
    public ClienteEndPoint(ClienteRepository ClienteDAO) {
        this.clienteDAO = ClienteDAO;
    }

    @Autowired
    public TelefoneRepository telefoneDAO;

    @Autowired
    public VideoGameRepository videoGameDAO;

    //todos os clientes
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll() {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDAO.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //cliente por id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (clienteDAO.existsById(id)) {
            return new ResponseEntity<>(clienteDAO.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //salvar cliente
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteDAO.save(cliente), HttpStatus.OK);
    }

    //delete cliente por id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (clienteDAO.existsById(id)) {
            Cliente cliente = clienteDAO.findById(id).get();
            videoGameDAO.deleteByCliente(cliente);
            telefoneDAO.deleteByCliente(cliente);
            clienteDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //update
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteDAO.save(cliente), HttpStatus.OK);
    }
}
