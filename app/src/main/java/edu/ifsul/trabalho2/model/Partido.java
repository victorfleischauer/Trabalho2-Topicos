package edu.ifsul.trabalho2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partido {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("sigla")
    @Expose
    private String sigla;

    @SerializedName("nome")
    @Expose
    private String nome;

    //----
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
