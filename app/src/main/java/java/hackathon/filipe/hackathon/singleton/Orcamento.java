package java.hackathon.filipe.hackathon.singleton;

public class Orcamento {

    private double valor;
    private static Orcamento instance;

    private Orcamento() {
        this.valor = 0;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static Orcamento getInstance() {
        if (instance == null) {
            instance = new Orcamento();
        }
        return instance;
    }
}
