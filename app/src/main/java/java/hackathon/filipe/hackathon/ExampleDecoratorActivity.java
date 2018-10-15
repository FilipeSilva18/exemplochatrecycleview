package java.hackathon.filipe.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.hackathon.filipe.hackathon.decorator.CalculadorDeImposto;
import java.hackathon.filipe.hackathon.decorator.ICMS;
import java.hackathon.filipe.hackathon.decorator.ISS;
import java.hackathon.filipe.hackathon.decorator.Imposto;
import java.hackathon.filipe.hackathon.decorator.Orcamento;
import java.hackathon.filipe.hackathon.view.PadroesProjetoActivity;

public class ExampleDecoratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_example_decorator);


        Imposto iss = new ISS(new ICMS());
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
