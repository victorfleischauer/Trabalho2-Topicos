package edu.ifsul.trabalho2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deputado {
    @SerializedName("nome")
    @Expose
    private String nome;
}
