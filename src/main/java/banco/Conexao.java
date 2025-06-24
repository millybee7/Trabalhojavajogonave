package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:sqlite:recordes.db";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // força carregar o driver
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do SQLite não encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL);
    }
}
