package org.example.Model;

public class Dispositivo {
    private Integer id;
    private String nome;
    private int status;
    private String tipo;

    public Dispositivo(String nome, String tipo) {
        this.nome = nome;
        this.status = 0;
        this.tipo = tipo;
    }
    public Dispositivo(int id, String nome, int status, String tipo) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
    }

    public void ligar() { status = 1; }
    public void desligar() { status = 0; }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isLigado() {
        if (status == 1) {
            return true;
        }
        return false;
    }

    public int getStatus() {
        return status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
