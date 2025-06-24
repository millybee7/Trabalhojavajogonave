package modelo;

import java.awt.Rectangle;

public class Tiro {
    public int x, y;
    private int velocidade = 10;

    public Tiro(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        y -= velocidade;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 10);
    }
}
