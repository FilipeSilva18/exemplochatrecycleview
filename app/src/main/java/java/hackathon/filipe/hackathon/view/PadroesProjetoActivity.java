package java.hackathon.filipe.hackathon.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.hackathon.filipe.hackathon.ExampleDecoratorActivity;
import java.hackathon.filipe.hackathon.ExampleSingletonActivity;
import java.hackathon.filipe.hackathon.ExampleStrategyActivity;
import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.model.PadraoProjeto;

public class PadroesProjetoActivity extends AppCompatActivity implements PadroesAdapter.AdapterListener {

    private RecyclerView recyclerView;
    private PadroesAdapter padroesAdapter;
    private Button btnExemplo;
    private TextView nome;
    private TextView descricao;
    private LinearLayout infoPadraoProjeto;
    private LinearLayoutManager layoutManager;
    private PadraoProjeto item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_padroes_projeto);

        recyclerView = findViewById(R.id.list_padroes);
        btnExemplo = findViewById(R.id.btn_exemplo);
        nome = findViewById(R.id.tv_nome);
        descricao = findViewById(R.id.tv_descricao);
        infoPadraoProjeto = findViewById(R.id.info_padrao);

        padroesAdapter = new PadroesAdapter(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(padroesAdapter);
    }

    @Override
    public void setOnClickItem(PadraoProjeto item) {
        this.item = item;
        System.out.println(item.getNome());
        if(infoPadraoProjeto.getVisibility() == View.GONE)
        infoPadraoProjeto.setVisibility(View.VISIBLE);
        if(item.getNome().equals("singleton")){
            nome.setText("Singleton");
            descricao.setText("O padrão Singleton garante que uma classe tenha apenas uma instância de si mesma, fornecendo um" +
                    "ponto global de acesso a ela. Ou seja, a classe gerencia sua própria instância, evitando que qualquer" +
                    "outra classe crie um instância dela. Normalmente o ponto global de acesso a instância da classe chama-se: getInstance" +
                    " e é através dele que as demais classes receberão a instância da classe com o padrão Singleton, caso não exista a instância" +
                    " a classe cria, se existir a classe retorna a instância existente.");
            btnExemplo.setText("Exemplo Singleton");
        }else if(item.getNome().equals("strategy")){
            nome.setText("Strategy");
            descricao.setText("O padrão Strategy, por meio de polimorfismo, permite adicionar/alterar implementações de um método" +
                    " comum, deixando-as encapsuladas, sem que impacte as chamadas realizadas pelo cliente. Ele permite que os algoritmos" +
                    "variem independentemente entre os clientes que os utilizam");
            btnExemplo.setText("Exemplo Strategy");
        }else if(item.getNome().equals("decorator")){
            nome.setText("Decorator");
            descricao.setText("O padrão Decorator nos permite criar objetos complexos, ou seja, anexa responsabilidades adicionais para um objeto" +
                    " dinamicamente. A proposta do Decorator é encapsular um objeto de destino para que se possam adicionar dinamicamente novas responsabilidades" +
                    " em tempo de execução. Cada Decorator pode encapsular um no outro, o que permite teoricamente ilimitado de Decorators de objetos de destino");
            btnExemplo.setText("Exemplo Decorator");
        }
    }

    public void Exemplo(View view) {
        if(item.getNome().equals("strategy")){
            Intent it = new Intent(this, ExampleStrategyActivity.class);
            startActivity(it);
        } else if(item.getNome().equals("singleton")){
            Intent it = new Intent(this, ExampleSingletonActivity.class);
            startActivity(it);
        }else if(item.getNome().equals("decorator")){
            Intent it = new Intent(this, ExampleDecoratorActivity.class);
            startActivity(it);
        }
    }

    public void backToMenu(View view) {
        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);
    }
}
