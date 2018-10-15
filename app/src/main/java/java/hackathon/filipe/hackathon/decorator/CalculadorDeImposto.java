package java.hackathon.filipe.hackathon.decorator;

public class CalculadorDeImposto {

    public void realizaCalculo(Orcamento orcamento, Imposto impostoQualquer){
        double imposto = impostoQualquer.calculaImposto(orcamento);
        System.out.println(imposto);
    }
}
