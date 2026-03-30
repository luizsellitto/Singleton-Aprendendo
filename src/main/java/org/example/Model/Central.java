package org.example.Model;

import org.example.DAO.DispositivoDAO;
import org.example.DAO.LampadaDAO;

import java.util.*;

public class Central {

    private static Central instance;

    private final Map<Integer, Dispositivo> dispositivos = new HashMap<>();

    private final DispositivoDAO dispositivoDAO;
    private final LampadaDAO lampadaDAO;

    private Central() throws Exception {
        dispositivoDAO = new DispositivoDAO();
        lampadaDAO = new LampadaDAO();
        carregarDoBanco();
    }

    public static synchronized Central getInstance() throws Exception {
        if (instance == null) {
            instance = new Central();
        }
        return instance;
    }

    private void carregarDoBanco() throws Exception {
        dispositivos.clear();

        for (Dispositivo d : dispositivoDAO.getAll()) {

            if (d.getTipo().equalsIgnoreCase("lampada")) {
                Lampada lampada = lampadaDAO.getById(d.getId());
                dispositivos.put(d.getId(), lampada);
            } else {
                dispositivos.put(d.getId(), d);
            }
        }
    }

    public void salvar(Dispositivo d) throws Exception {
        dispositivoDAO.insert(d);

        if (d instanceof Lampada lampada) {
            lampadaDAO.insert(lampada);
        }

        carregarDoBanco();
    }

    public void atualizar(Dispositivo d) throws Exception {
        dispositivoDAO.update(d);

        if (d instanceof Lampada lampada) {
            lampadaDAO.update(lampada);
        }

        carregarDoBanco();
    }

    public void remover(int id) throws Exception {
        Dispositivo d = dispositivos.get(id);

        if (d != null) {
            if (d instanceof Lampada) {
                lampadaDAO.delete(id);
            }

            dispositivoDAO.delete(d);
            carregarDoBanco();
        }
    }

    public Dispositivo getById(int id) {
        return dispositivos.get(id);
    }

    public Collection<Dispositivo> getDispositivos() {
        return Collections.unmodifiableCollection(dispositivos.values());
    }
}