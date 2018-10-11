package java.hackathon.filipe.hackathon.singleton;


public class CalculadorDeImposto {

    public void RealizaCalculo(Orcamento orcamento, Imposto impostoQualquer){
        double imposto = impostoQualquer.calculaImposto(orcamento);
        System.out.println(imposto);
    }
}
