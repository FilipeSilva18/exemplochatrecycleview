package java.hackathon.filipe.hackathon.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.hackathon.filipe.hackathon.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.menu);
    }

    public void teoriaPOO(View view) {
        Intent it = new Intent(this, PooOrientacaoActivity.class);
        startActivity(it);
    }

    public void historiaJava(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    public void padroesProjeto(View view) {
        Intent it = new Intent(this, PadroesProjetoActivity.class);
        startActivity(it);
    }
}
