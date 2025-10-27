package com.example.mapfood.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name="mapfood_Vendedor")
public class Vendedor {

    @Id
    private String cpf;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @OneToOne(mappedBy = "vendedor")
    @JsonBackReference
    private Loja loja;



    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Vendedor() {}

}
