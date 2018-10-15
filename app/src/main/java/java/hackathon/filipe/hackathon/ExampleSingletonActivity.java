package java.hackathon.filipe.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.hackathon.filipe.hackathon.singleton.CalculadorDeImposto;
import java.hackathon.filipe.hackathon.singleton.ICMS;
import java.hackathon.filipe.hackathon.singleton.ISS;
import java.hackathon.filipe.hackathon.singleton.Imposto;
import java.hackathon.filipe.hackathon.singleton.Orcamento;
import java.hackathon.filipe.hackathon.view.PadroesProjetoActivity;


public class ExampleSingletonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_example_singleton);


        Imposto iss = new ISS();
        Imposto icms = new ICMS();
        System.out.println("----------------SINGLETON----------------");
        Orcamento orcamento = Orcamento.getInstance();
        orcamento.setValor(500);
        CalculadorDeImposto calculador = new CalculadorDeImposto();
        calculador.realizaCalculo(orcamento, iss);
        calculador.realizaCalculo(orcamento, icms);
        System.out.println("----------------SINGLETON----------------");


    }

    public void backPadroesProjeto(View view) {
        Intent it = new Intent(this, PadroesProjetoActivity.class);
        startActivity(it);
    }
}
