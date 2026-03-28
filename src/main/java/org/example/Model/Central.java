package org.example.Model;

import java.util.*;

public class Central {

    private static Central instance;
    private Map<Integer, Dispositivo> dispositivos;

    private Central() {
        dispositivos = new HashMap<>();
    }

    public static Central getInstance() {
        if (instance == null) {
            instance = new Central();
        }
        return instance;
    }

    public void addDispositivo(Dispositivo d) {
        dispositivos.put(d.getId(), d);
    }

    public void removeDispositivo(int id) {
        dispositivos.remove(id);
    }

    public Dispositivo getById(int id) {
        return dispositivos.get(id);
    }

    public Dispositivo getByNome(String nome) {
        for (Dispositivo dispositivo : dispositivos.values()) {
            if (dispositivo.getNome().equals(nome)) {
                return dispositivo;
            }
        }
        return null;
    }

    public Collection<Dispositivo> getDispositivos() {
        return dispositivos.values();
    }
}