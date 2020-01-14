package com.vgproject.vgp.repository;

import com.vgproject.vgp.model.Cliente;
import com.vgproject.vgp.model.Telefone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * author LucasDonizeti
 */
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {
    List<Telefone> findByCliente(Cliente cliente);

    @Transactional
    void deleteByCliente(Cliente cliente);
    Page<Telefone> findAll(Pageable pageable);
}
