package com.vgproject.vgp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * author LucasDonizeti
 */
@Entity
public class VideoGame extends AbstractEntity {
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private Boolean desbloqueado;
    @ManyToOne
    private Cliente cliente;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Boolean getDesbloqueado() {
        return desbloqueado;
    }

    public void setDesbloqueado(Boolean desbloqueado) {
        this.desbloqueado = desbloqueado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
