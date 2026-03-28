package org.example;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() throws Exception {

        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS dispositivo (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT,
                    status INTEGER,
                    tipo TEXT
                );
            """);
        }
    }
}