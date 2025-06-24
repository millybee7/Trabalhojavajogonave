package modelo;

public class NaveAtaque extends NaveEspacial {

    public NaveAtaque(int x, int y) {
        super(x, y);
        this.velocidade = 12;  // mais rápida que a base
        this.dano = 2.0;       // mais dano
    }

    @Override
    public void mover(String direcao) {
        switch(direcao) {
            case "cima": posY -= velocidade; break;
            case "baixo": posY += velocidade; break;
            case "esquerda": posX -= velocidade; break;
            case "direita": posX += velocidade; break;
        }
        limitarPosicao();
    }

    @Override
    public void atirar() {
        // lógica de tiro especial, se quiser
    }

    private void limitarPosicao() {
        if (posX < 0) posX = 0;
        if (posY < 0) posY = 0;
        if (posX > 470) posX = 470;
        if (posY > 570) posY = 570;
    }
}
