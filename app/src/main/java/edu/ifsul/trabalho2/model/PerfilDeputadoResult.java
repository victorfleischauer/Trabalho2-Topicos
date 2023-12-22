package edu.ifsul.trabalho2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerfilDeputadoResult {
    @SerializedName("dados")
    @Expose
    Deputado deputado;

    public Deputado getDeputado() {
        return deputado;
    }

    public void setDeputado(Deputado deputado) {
        this.deputado = deputado;
    }
}
