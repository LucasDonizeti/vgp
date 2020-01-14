package com.vgproject.vgp.controller;

import com.vgproject.vgp.model.Cliente;
import com.vgproject.vgp.model.VideoGame;
import com.vgproject.vgp.repository.ClienteRepository;
import com.vgproject.vgp.repository.VideoGameRepository;
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
@RequestMapping("videogame")
public class VideoGameEndPoint {
    private final VideoGameRepository videoGameDAO;

    @Autowired
    public VideoGameEndPoint(VideoGameRepository videoGameDAO) {
        this.videoGameDAO = videoGameDAO;
    }

    @Autowired
    private ClienteRepository clienteDAO;

    //retorna tds os videogames (paginação suportada)
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<VideoGame> videogames = (Page<VideoGame>) videoGameDAO.findAll(pageable);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    //retorna videogame do cliente
    @GetMapping(path = "/{clienteId}")
    public ResponseEntity<?> getByClienteId(@PathVariable("clienteId") Cliente cliente) {
        return new ResponseEntity<>(videoGameDAO.findByCliente(cliente), HttpStatus.OK);
    }

    //salva videogame
    @PostMapping(path = "/{id}")
    public ResponseEntity<?> salvar(@RequestBody VideoGame videoGame, @PathVariable("id") Long id) {
        if (clienteDAO.existsById(id)) {
            Cliente cliente = clienteDAO.findById(id).get();
            videoGame.setCliente(cliente);
            return new ResponseEntity<>(videoGameDAO.save(videoGame), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //deleta videogame por id
    @DeleteMapping(path = "videoGameId")
    public ResponseEntity<?> delete(@PathVariable("videoGameId") Long id) {
        if (videoGameDAO.existsById(id)) {
            videoGameDAO.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //atualiza videogame
    @PutMapping
    public ResponseEntity<?> update(@RequestBody VideoGame videoGame) {
        if (videoGameDAO.existsById(videoGame.getId()))
            return new ResponseEntity<>(videoGameDAO.save(videoGame), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
