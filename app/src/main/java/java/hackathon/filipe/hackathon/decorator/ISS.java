package java.hackathon.filipe.hackathon.decorator;

public class ISS extends Imposto {

    public ISS(Imposto outroImposto) {
        super(outroImposto);
    }

    public ISS() {
    }

    @Override
    public double calculaImposto(Orcamento orcamento) {
        return orcamento.getValor() * 0.06 + calculoOutroImposto(orcamento);
    }
}
