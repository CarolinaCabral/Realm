package com.example.carolinacabral.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carolinacabral.realm.Adapter.ProdutoAdapter;
import com.example.carolinacabral.realm.Domain.Produto;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ProdutoActivity extends AppCompatActivity {

    private Realm realm;
    private RealmResults <Produto> produtos;
    private RealmChangeListener realmChangeListener;
    private ListView lstProduto;

    @Override
    protected void onDestroy() {

        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String txt = bundle.getString("txt");
        TextView resultado = (TextView) findViewById(R.id.txtResultado);
        resultado.setText(txt);

        realm = Realm.getInstance(this);
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                ((ProdutoAdapter) lstProduto.getAdapter()).notifyDataSetChanged();
            }
        };

        realm.addChangeListener(realmChangeListener);
        produtos = realm.where(Produto.class).findAll();


        lstProduto = (ListView)findViewById(R.id.listId);
        lstProduto.setAdapter(new ProdutoAdapter(this,produtos,false));

    }
    public void callAddProduto( View view){
        Intent it = new Intent( this, AddUpdateProduto.class );
        startActivity(it);
    }
}
