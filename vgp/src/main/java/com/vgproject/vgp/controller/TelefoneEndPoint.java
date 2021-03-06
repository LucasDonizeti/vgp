package com.vgproject.vgp.controller;

import com.vgproject.vgp.model.Cliente;
import com.vgproject.vgp.model.Telefone;
import com.vgproject.vgp.repository.ClienteRepository;
import com.vgproject.vgp.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * author LucasDonizeti
 */
@RestController
@RequestMapping("telefone")
public class TelefoneEndPoint {
    private final TelefoneRepository telefoneDAO;

    @Autowired
    public TelefoneEndPoint(TelefoneRepository telefoneDAO) {
        this.telefoneDAO = telefoneDAO;
    }

    @Autowired
    private ClienteRepository clienteDAO;

    //todos os telefones (paginação suportada)
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Telefone> telefones = (Page<Telefone>) telefoneDAO.findAll(pageable);
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    //telefones por id cliente
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Cliente cliente) {
        return new ResponseEntity<>(telefoneDAO.findByCliente(cliente), HttpStatus.OK);
    }

    //salvar telefone
    @PostMapping(path = "/{clienteId}")
    public ResponseEntity<?> salvar(@RequestBody Telefone telefone, @PathVariable("clienteId") Long clienteId) {
        if (clienteDAO.existsById(clienteId)) {
            Cliente cliente = clienteDAO.findById(clienteId).get();
            telefone.setCliente(cliente);
            return new ResponseEntity<>(telefoneDAO.save(telefone), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //excluir telefone
    @DeleteMapping(path = "/{telefoneId}")
    public ResponseEntity<?> delete(@PathVariable("telefoneId") Long id) {
        if (telefoneDAO.existsById(id)) {
            telefoneDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //update telefone
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Telefone telefone) {
        if (telefoneDAO.existsById(telefone.getId()))
            return new ResponseEntity<>(telefoneDAO.save(telefone), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
