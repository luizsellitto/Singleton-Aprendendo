package org.example;

import org.example.Model.Central;

public class Main {
    static void main() throws Exception {
        DatabaseInitializer.init();
        Central central = Central.getInstance();

    }
}
