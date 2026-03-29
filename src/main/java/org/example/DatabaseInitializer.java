package org.example;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() throws Exception {

        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("PRAGMA foreign_keys = ON");

            st.execute("""
                CREATE TABLE IF NOT EXISTS dispositivo (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT,
                    status INTEGER,
                    tipo TEXT
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS lampada (
                    id INTEGER PRIMARY KEY,
                    intensidade INTEGER,
                    FOREIGN KEY (id) REFERENCES dispositivo(id)
                )
            """);
        }
    }
}