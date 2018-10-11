package java.hackathon.filipe.hackathon.singleton;


public class ICMS implements Imposto {
    @Override
    public double calculaImposto(Orcamento orcamento) {
        return orcamento.getValor() + orcamento.getValor()*0.1;
    }
}
