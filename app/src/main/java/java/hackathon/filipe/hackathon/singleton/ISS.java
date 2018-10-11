package java.hackathon.filipe.hackathon.singleton;


public class ISS implements Imposto {
    @Override
    public double calculaImposto(Orcamento orcamento) {
        return orcamento.getValor() + orcamento.getValor()*0.06;
    }
}
