package com.example.primo_progetto_spring.Entity;

import jakarta.persistence.*;

@Entity
public class Studente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private String data;
    private String codiceFiscale;

    public Studente(){}

    public Studente(Long id, String nome, String cognome, String data, String codiceFiscale){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.codiceFiscale = codiceFiscale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String studentInfo(){
        return (this.nome + "\n" + this.cognome + "\n" + this.data + "\n" + this.codiceFiscale);
    }
}
