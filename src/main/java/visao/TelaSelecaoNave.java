package visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaSelecaoNave extends JFrame {

    private final JFrame menuInicial;

    public TelaSelecaoNave(JFrame menuInicial) {
        this.menuInicial = menuInicial;

        setTitle("Selecionar Nave");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setBackground(new Color(25, 25, 25));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel label = new JLabel("Escolha sua Nave");
        label.setForeground(Color.CYAN);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnBase = criarBotao("Nave Base");
        JButton btnAtaque = criarBotao("Nave Ataque");
        JButton btnDefesa = criarBotao("Nave Defesa");

        btnBase.addActionListener(e -> selecionarNave("base"));
        btnAtaque.addActionListener(e -> selecionarNave("ataque"));
        btnDefesa.addActionListener(e -> selecionarNave("defesa"));

        painel.add(label);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(btnBase);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(btnAtaque);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(btnDefesa);

        add(painel);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setFont(new Font("Arial", Font.PLAIN, 16));
        botao.setBackground(new Color(60, 60, 60));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(180, 40));
        botao.setMaximumSize(new Dimension(180, 40));
        return botao;
    }

    private void selecionarNave(String tipo) {
        MenuInicial.tipoNaveSelecionada = tipo;
        JOptionPane.showMessageDialog(this, "Nave '" + tipo + "' selecionada!");
        menuInicial.setVisible(true);
        dispose();
    }
}
