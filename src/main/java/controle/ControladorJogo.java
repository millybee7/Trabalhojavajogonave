package controle;

import modelo.*;
import banco.RankingDAO;
import visao.MenuInicial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ControladorJogo extends JPanel {
    private NaveEspacial nave;
    private List<Meteoro> meteoros;
    private List<Tiro> tiros;
    private Timer timer;
    private int pontos = 0;
    private JFrame janela;

    public ControladorJogo(JFrame janela, NaveEspacial nave) {
        this.janela = janela;
        this.nave = nave;
        this.meteoros = new ArrayList<>();
        this.tiros = new ArrayList<>();
        setBackground(Color.BLACK);
    }

    public void iniciar() {
        timer = new Timer(50, e -> atualizarJogo());
        timer.start();
    }

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> nave.mover("esquerda");
                    case KeyEvent.VK_RIGHT -> nave.mover("direita");
                    case KeyEvent.VK_UP -> nave.mover("cima");
                    case KeyEvent.VK_DOWN -> nave.mover("baixo");
                    case KeyEvent.VK_SPACE -> tiros.add(new Tiro(nave.getX() + 12, nave.getY()));
                }
                repaint();
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fundo com gradiente azul escuro
        Color corInicio = new Color(5, 10, 40);
        Color corFim = new Color(0, 0, 0);
        GradientPaint gp = new GradientPaint(0, 0, corInicio, 0, getHeight(), corFim);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Desenha a nave como triângulo colorido por tipo
        g2d.setColor(getCorNave());
        int x = nave.getX();
        int y = nave.getY();
        int largura = 30;
        int altura = 30;
        Polygon naveTriangulo = new Polygon();
        naveTriangulo.addPoint(x + largura / 2, y);        // topo
        naveTriangulo.addPoint(x, y + altura);             // canto esquerdo
        naveTriangulo.addPoint(x + largura, y + altura);   // canto direito
        g2d.fill(naveTriangulo);

        // Desenha meteoros como círculos laranja
        g2d.setColor(new Color(255, 140, 0));
        for (Meteoro m : meteoros) {
            g2d.fillOval(m.getX(), m.getY(), m.getTamanho(), m.getTamanho());
        }

        // Desenha tiros como retângulos amarelos
        g2d.setColor(Color.YELLOW);
        for (Tiro t : tiros) {
            g2d.fillRect(t.x, t.y, 5, 10);
        }

        // Pontuação
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Pontos: " + pontos, 10, 20);
    }

    private Color getCorNave() {
        if (nave instanceof NaveAtaque) return Color.RED;
        if (nave instanceof NaveDefesa) return Color.GREEN;
        return Color.BLUE; // NaveBase padrão
    }

    private void atualizarJogo() {
        if (Math.random() < 0.05) meteoros.add(new Meteoro());

        Iterator<Meteoro> it = meteoros.iterator();
        while (it.hasNext()) {
            Meteoro m = it.next();
            m.mover();
            if (m.getBounds().intersects(new Rectangle(nave.getX(), nave.getY(), 30, 30))) {
                timer.stop();
                String nome = JOptionPane.showInputDialog("Game Over!\nDigite seu nome:");
                try {
                    RankingDAO.inserir(nome, pontos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new MenuInicial().setVisible(true);
                janela.dispose();
                return;
            }
            if (!m.ativo) {
                it.remove();
                pontos++;
            }
        }

        Iterator<Tiro> itTiro = tiros.iterator();
        while (itTiro.hasNext()) {
            Tiro t = itTiro.next();
            t.mover();
            if (t.y < 0) {
                itTiro.remove();
                continue;
            }
            for (Meteoro m : meteoros) {
                if (m.getBounds().intersects(t.getBounds())) {
                    m.ativo = false;
                    itTiro.remove();
                    pontos++;
                    break;
                }
            }
        }

        repaint();
    }
}
