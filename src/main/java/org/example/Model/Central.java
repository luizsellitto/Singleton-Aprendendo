package org.example.Model;

import org.example.DAO.DispositivoDAO;

import java.util.*;

public class Central {

    private static Central instance;
    private Map<Integer, Dispositivo> dispositivos = new HashMap<>();
    private DispositivoDAO dao;
    private boolean carregado = false;

    private Central() throws Exception {
        dao = new DispositivoDAO();
        carregarDoBanco();
    }

    public static Central getInstance() throws Exception {
        if (instance == null) {
            instance = new Central();
        }
        return instance;
    }

    private void carregarDoBanco() throws Exception {
        if (carregado) return;

        for (Dispositivo d : dao.getAll()) {
            dispositivos.put(d.getId(), d);
        }

        carregado = true;
    }

    public void salvar(Dispositivo d) throws Exception {
        dao.insert(d);
        dispositivos.put(d.getId(), d);
    }

    public void atualizar(Dispositivo d) throws Exception {
        dao.update(d);
    }

    public void remover(int id) throws Exception {
        Dispositivo d = dispositivos.get(id);
        if (d != null) {
            dao.delete(d);
            dispositivos.remove(id);
        }
    }

    public Collection<Dispositivo> getDispositivos() {
        return Collections.unmodifiableCollection(dispositivos.values());
    }
}