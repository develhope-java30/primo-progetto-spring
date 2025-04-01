package com.example.primo_progetto_spring.Entity;

public class Studente {
    private String nome;
    private String cognome;
    private String data;
    private String codiceFiscale;

    public Studente(String nome, String cognome, String data, String codiceFiscale){
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.codiceFiscale = codiceFiscale;
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
