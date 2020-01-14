package com.vgproject.vgp.repository;

import com.vgproject.vgp.model.Cliente;
import com.vgproject.vgp.model.VideoGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * author LucasDonizeti
 */
public interface VideoGameRepository extends CrudRepository<VideoGame, Long> {
    List<VideoGame> findByCliente(Cliente cliente);

    @Transactional
    void deleteByCliente(Cliente cliente);
    Page<VideoGame> findAll(Pageable pageable);
}
