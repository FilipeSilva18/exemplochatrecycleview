package java.hackathon.filipe.hackathon.strategy;

public class Orcamento {
    private double valor;
    private static Orcamento instance;

    public Orcamento(double valor) {
        this.valor = valor;

    }
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
