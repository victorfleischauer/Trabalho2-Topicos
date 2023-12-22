package edu.ifsul.trabalho2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerfilPartidoResult {
    @SerializedName("dados")
    @Expose
    Partido partido;

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
