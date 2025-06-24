package visao;

import controle.ControladorJogo;
import modelo.*;
import banco.RankingDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MenuInicial extends JFrame {

    public static String tipoNaveSelecionada = "base";

    public MenuInicial() {
        setTitle("Jogo de Nave Espacial");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setBackground(new Color(30, 30, 30));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel("Jogo de Nave");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.CYAN);

        JButton btnIniciar = criarBotao("Iniciar");
        JButton btnSelecionar = criarBotao("Selecionar Nave");
        JButton btnRecordes = criarBotao("Recordes");
        JButton btnSair = criarBotao("Sair");

        btnIniciar.addActionListener(e -> {
            NaveEspacial nave;
            switch (tipoNaveSelecionada) {
                case "ataque" -> nave = new NaveAtaque(100, 500);
                case "defesa" -> nave = new NaveDefesa(100, 500);
                default -> nave = new NaveBase(100, 500);
            }
            abrirJogo(nave);
        });

        btnSelecionar.addActionListener(e -> {
            new TelaSelecaoNave(this).setVisible(true);
            setVisible(false);
        });

        btnRecordes.addActionListener(this::mostrarRecordes);

        btnSair.addActionListener(e -> System.exit(0));

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(btnIniciar);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(btnSelecionar);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(btnRecordes);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setBackground(new Color(60, 60, 60));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(200, 40));
        botao.setMaximumSize(new Dimension(200, 40));
        return botao;
    }

    private void abrirJogo(NaveEspacial nave) {
        JFrame frame = new JFrame("Jogo");
        ControladorJogo jogo = new ControladorJogo(frame, nave);
        frame.add(jogo);
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(jogo.getKeyAdapter());
        frame.setVisible(true);
        jogo.iniciar();
        dispose();
    }

    private void mostrarRecordes(ActionEvent e) {
        try {
            List<String> recordes = RankingDAO.top5();
            StringBuilder sb = new StringBuilder("Top 5 Recordes:\n\n");
            for (String r : recordes) {
                sb.append(r).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Recordes", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar recordes.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuInicial().setVisible(true));
    }
}
