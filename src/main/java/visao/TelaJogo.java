package visao;

import modelo.*;
import banco.RankingDAO;
import controle.ControladorJogo;

import javax.swing.*;
import java.awt.*;

public class TelaJogo extends JFrame {
    public TelaJogo() {
        setTitle("Jogo em Andamento");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        NaveEspacial nave;
        switch (MenuInicial.tipoNaveSelecionada) {
            case "ataque" -> nave = new NaveAtaque(300, 400);
            case "defesa" -> nave = new NaveDefesa(300, 400);
            default -> nave = new NaveBase(300, 400);
        }

        ControladorJogo controlador = new ControladorJogo(this, nave);
        add(controlador);
        addKeyListener(controlador.getKeyAdapter());
        setFocusable(true);
        controlador.iniciar();
    }
}