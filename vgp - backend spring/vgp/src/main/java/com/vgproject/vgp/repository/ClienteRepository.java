package com.vgproject.vgp.repository;

import com.vgproject.vgp.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * author LucasDonizeti
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Page<Cliente> findAll(Pageable pageable);
}
