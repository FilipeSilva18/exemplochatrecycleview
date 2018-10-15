package java.hackathon.filipe.hackathon.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.view.MenuActivity;

public class PooOrientacaoActivity extends AppCompatActivity {
    Toolbar toolbar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_poo_orientacao);

    }


    public void backToMenu(View view) {
        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);
    }
}
