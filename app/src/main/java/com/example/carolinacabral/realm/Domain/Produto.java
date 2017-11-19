package com.example.carolinacabral.realm.Domain;

/**
 * Created by Carolina Cabral on 13/11/2017.
 */
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Produto extends RealmObject{
    public static final String ID = "com.example.carolinacabral.domain.realm.RealmObject.ID";
    @PrimaryKey
    private int id;
    private String descricao;
    private String quantidade;
    private String categoria;

    public Produto(String descricao, String quantidade, String categoria) {
        super();
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Produto()
    {
        super();
    }

}
