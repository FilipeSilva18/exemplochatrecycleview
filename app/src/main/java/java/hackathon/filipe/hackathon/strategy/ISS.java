package java.hackathon.filipe.hackathon.strategy;

public class ISS implements Imposto {
    @Override
    public double calculaImposto(Orcamento orcamento) {
        return orcamento.getValor() + orcamento.getValor()*0.06;
    }
}
