package com.example.carolinacabral.realm;

import android.content.Intent;
import android.icu.text.SelectFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carolinacabral.realm.Domain.Produto;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddUpdateProduto extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Produto> produtos;

    private Produto produto;
    private EditText descricao;
    private EditText quantidade;
    private Spinner categorias;
    private CheckBox perecivel;

    private Button adicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_produto);


        final String[] categoria = {"Supermercado", "Padaria", "Açougue", "Farmácia"};
        final Spinner spnCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        final ArrayAdapter<String> adapterCategoria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoria);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnCategoria.setAdapter(adapterCategoria);


        produto = new Produto();
        descricao = (EditText) findViewById(R.id.editTextDescricao);
        quantidade = (EditText) findViewById(R.id.editTextQuantidade);
        categorias = (Spinner) findViewById(R.id.spinnerCategoria);
        perecivel = (CheckBox) findViewById(R.id.cbPerecivel);
        adicionar = (Button) findViewById(R.id.buttonAdicionat);


        realm = Realm.getInstance(this);
        produtos = realm.where(Produto.class).findAll();

        if (getIntent() != null && getIntent().getIntExtra(Produto.ID, 0) > 0) {
            produto.setId((getIntent().getIntExtra(Produto.ID, 0)));
            produto = produtos.where().equalTo("id", produto.getId()).findAll().get((0));
            descricao.setText(produto.getDescricao());
            quantidade.setText(produto.getQuantidade());

            //categorias.getSelectedItem().toString();

            adicionar.setText("Atualizar");


        }

    }
    public void callAddUpdateProduto(View view)
    {
                boolean perecivelChecked = perecivel.isChecked();
                if (produto.getId()==0)
                {
                    produtos.sort("id", RealmResults.SORT_ORDER_DESCENDING);
                    int id = produtos.size() == 0 ? 1 : produtos.get(0).getId()+1;
                    produto.setId(id);

                }
                try{
                    realm.beginTransaction();
                    produto.setDescricao(descricao.getText().toString());
                    produto.setQuantidade(quantidade.getText().toString());
                    produto.setCategoria(categorias.getSelectedItem().toString());
                    realm.copyToRealmOrUpdate(produto);
                    realm.commitTransaction();
                    Toast.makeText(AddUpdateProduto.this,"Produto adicionado",Toast.LENGTH_SHORT).show();
                    if (perecivelChecked)
                    {
                        Toast.makeText(AddUpdateProduto.this,"Produto Perecível",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(AddUpdateProduto.this,"Erro",Toast.LENGTH_SHORT).show();

                }
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



