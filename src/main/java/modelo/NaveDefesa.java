package modelo;

public class NaveDefesa extends NaveEspacial {

    public NaveDefesa(int x, int y) {
        super(x, y);
        this.velocidade = 8;  // mais lenta
        this.dano = 0.5;      // menos dano, mas pode ser mais resistente (pode adicionar vida depois)
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
        // lógica de tiro ou defesa especial
    }

    private void limitarPosicao() {
        if (posX < 0) posX = 0;
        if (posY < 0) posY = 0;
        if (posX > 470) posX = 470;
        if (posY > 570) posY = 570;
    }
}
