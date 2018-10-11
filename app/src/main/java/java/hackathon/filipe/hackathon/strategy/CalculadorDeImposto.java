package java.hackathon.filipe.hackathon.strategy;

public class CalculadorDeImposto {

    public void RealizaCalculo(Orcamento orcamento, Imposto impostoQualquer){
        double imposto = impostoQualquer.calculaImposto(orcamento);
        System.out.println(imposto);
    }
}
