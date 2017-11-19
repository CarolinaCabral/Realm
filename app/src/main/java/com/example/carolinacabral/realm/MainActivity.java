package com.example.carolinacabral.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.carolinacabral.realm.Adapter.ProdutoAdapter;
import com.example.carolinacabral.realm.Domain.Produto;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private EditText listanome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button adicionar = (Button)findViewById(R.id.btnAdicionar);

        listanome = (EditText) findViewById(R.id.edtDescricao);
        Realm realm = Realm.getInstance(this);
        RealmResults<Produto>produtos = realm.where(Produto.class).findAll();
        adicionar.setText("Produtos ("+produtos.size()+")");
        realm.close();




        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProdutoActivity.class);
                String txt="";
                txt = listanome.getText().toString();
                Bundle bundle = new Bundle();

                bundle.putString("txt",txt);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }







}
