package com.vgproject.vgp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * author LucasDonizeti
 */
@Entity
public class Telefone extends AbstractEntity {
    private String ddd;
    private String numero;

    @ManyToOne
    private Cliente cliente;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
