package org.example;

import org.example.Model.Central;
import org.example.Model.Dispositivo;
import org.example.Model.Lampada;

public class Main {
    static void main() throws Exception {
        DatabaseInitializer.init();
        Central central = Central.getInstance();

        System.out.println("=== Inserindo Dispositivos ===");

        Dispositivo tv = new Dispositivo("TV Sala Nova", "tv");
        Lampada lamp = new Lampada("Lâmpada Quarto Nova", "lampada");
        lamp.aumentarIntensidade(80);

        central.salvar(tv);
        central.salvar(lamp);

        System.out.println("Inseridos com ID:");
        System.out.println(tv.getId());
        System.out.println(lamp.getId());


        System.out.println("\n=== Listando todos ===");
        for (Dispositivo d : central.getDispositivos()) {
            if (d instanceof Lampada l) {
                System.out.println("Lampada: " + l.getNome() +
                        " | Intensidade: " + l.getIntensidade());
            } else {
                System.out.println("Dispositivo: " + d.getNome());
            }
        }


        System.out.println("\n=== Teste getById (pegando da Central) ===");
        Lampada lampSistema = (Lampada) central.getById(lamp.getId());
        System.out.println("Buscado: " + lampSistema.getNome());


        System.out.println("\n=== Teste update ===");
        lampSistema.aumentarIntensidade(10);
        lampSistema.setNome("Lâmpada Quarto Nova - Editada");

        central.atualizar(lampSistema);

        System.out.println("Novo nome: " + lampSistema.getNome());
        System.out.println("Nova intensidade: " + lampSistema.getIntensidade());

        System.out.println("\n=== Banco antes do remove ===");
        for (Dispositivo d : central.getDispositivos()) {
            System.out.println(d.getNome());
        }

        System.out.println("\n=== Teste remove ===");
        central.remover(tv.getId());
        central.remover(lampSistema.getId());


        System.out.println("\n=== Banco depois do remove ===");
        for (Dispositivo d : central.getDispositivos()) {
            System.out.println(d.getNome());
        }

        System.out.println("\nFIM DOS TESTES");
    }
}