package org.example.Model;

public class Lampada extends Dispositivo {
    private int intensidade;

    public Lampada(String nome, String tipo) {
        super(nome, tipo);
        intensidade = 0;
    }

    public Lampada(int id, String nome, int status, String tipo, int intensidade) {
        super(id, nome, status, tipo);
        this.intensidade = intensidade;
    }

    public void aumentarIntensidade(int variacao) {
        ajustarIntensidade(this.intensidade + variacao);
    }

    public void diminuirIntensidade(int variacao) {
        ajustarIntensidade(this.intensidade - variacao);
    }

    private void ajustarIntensidade(int novaIntensidade) {
        if (novaIntensidade >= 0 && novaIntensidade <= 100) {
            this.intensidade = novaIntensidade;
        }
    }

    public int getIntensidade() {
        return intensidade;
    }
}
