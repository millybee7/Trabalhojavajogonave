package banco;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {

    public static void inserir(String nome, int pontos) throws SQLException {
        try (Connection con = Conexao.conectar()) {
            // Cria a tabela com a coluna "data"
            String sql = "CREATE TABLE IF NOT EXISTS recordes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "pontos INTEGER, " +
                    "data DATE)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.execute();
            }

            // Insere os dados com a data atual
            sql = "INSERT INTO recordes (nome, pontos, data) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setInt(2, pontos);
                stmt.setDate(3, Date.valueOf(LocalDate.now())); // Data atual
                stmt.executeUpdate();
            }
        }
    }

    public static List<String> top5() throws SQLException {
        List<String> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar()) {
            String sql = "SELECT nome, pontos, data FROM recordes ORDER BY pontos DESC LIMIT 5";
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String nome = rs.getString("nome");
                    int pontos = rs.getInt("pontos");
                    Date data = rs.getDate("data");
                    lista.add(nome + ": " + pontos + " pontos - " + data.toString());
                }
            }
        }
        return lista;
    }
}
