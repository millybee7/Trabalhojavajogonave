package modelo;

public class NaveBase extends NaveEspacial {
    public NaveBase(int x, int y) {
        super(x, y);
        this.velocidade = 10;
        this.dano = 1.0;
    }

    @Override
    public void mover(String direcao) {
        switch(direcao) {
            case "cima": posY -= velocidade; break;
            case "baixo": posY += velocidade; break;
            case "esquerda": posX -= velocidade; break;
            case "direita": posX += velocidade; break;
        }
        // Limitar posição para não sair da tela (exemplo 0 a 470 e 0 a 570)
        if (posX < 0) posX = 0;
        if (posX > 470) posX = 470;
        if (posY < 0) posY = 0;
        if (posY > 570) posY = 570;
    }
}
