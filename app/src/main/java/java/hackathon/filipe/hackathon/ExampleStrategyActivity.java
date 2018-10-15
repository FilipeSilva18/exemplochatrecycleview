package java.hackathon.filipe.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.hackathon.filipe.hackathon.strategy.CalculadorDeImposto;
import java.hackathon.filipe.hackathon.strategy.ICMS;
import java.hackathon.filipe.hackathon.strategy.ISS;
import java.hackathon.filipe.hackathon.strategy.Imposto;
import java.hackathon.filipe.hackathon.strategy.Orcamento;
import java.hackathon.filipe.hackathon.view.PadroesProjetoActivity;

public class ExampleStrategyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_example_strategy);

        Imposto iss = new ISS();
        Imposto icms = new ICMS();
        System.out.println("----------------SINGLETON----------------");
        Orcamento orcamento = new Orcamento(500);
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
