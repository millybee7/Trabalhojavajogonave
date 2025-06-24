package modelo;

import java.awt.Rectangle;

public class Meteoro {
    private int x, y;
    private int tamanho = 30;
    public boolean ativo = true;
    private int velocidade = 5;

    public Meteoro() {
        this.x = (int)(Math.random() * 470);
        this.y = 0;
    }

    public void mover() {
        y += velocidade;
        if (y > 600) ativo = false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getTamanho() { return tamanho; }

    public Rectangle getBounds() {
        return new Rectangle(x, y, tamanho, tamanho);
    }
}
