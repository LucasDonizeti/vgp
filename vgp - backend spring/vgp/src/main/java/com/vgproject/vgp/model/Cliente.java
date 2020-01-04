package com.vgproject.vgp.model;


import javax.persistence.*;
import java.util.List;


/**
 * author LucasDonizeti
 */
@Entity
public class Cliente extends AbstractEntity {
    private String nome;
    private String cpf;
    @OneToMany
    @JoinColumn(nullable = true)
    private List<Telefone> telefones;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }


}
