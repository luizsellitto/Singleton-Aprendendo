package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Central {

    private static Central instance;
    private List<Dispositivo> dispositivos;

    private Central() {
        dispositivos = new ArrayList<>();
    }

    public static Central getInstance() {
        if (instance == null) {
            instance = new Central();
        }
        return instance;
    }

    public void addDispositivo(Dispositivo dispositivo) {
        dispositivos.add(dispositivo);
    }

    public void removeDispositivo(Dispositivo dispositivo) {
        dispositivos.remove(dispositivo);
    }

    public Dispositivo getByNome(String nome) {
        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo.getNome().equals(nome)) {
                return dispositivo;
            }
        }
        return null;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }
}
