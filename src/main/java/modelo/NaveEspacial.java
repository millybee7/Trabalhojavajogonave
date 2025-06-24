package modelo;

public abstract class NaveEspacial {
    protected int posX, posY;
    protected int velocidade;
    protected double dano;

    public NaveEspacial(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public abstract void mover(String direcao);

    // Adicione este método abstrato ou método vazio para que possa ser sobrescrito
    public void atirar() {
        // Pode deixar vazio ou abstrato se quiser forçar subclasses a implementar
    }
}
