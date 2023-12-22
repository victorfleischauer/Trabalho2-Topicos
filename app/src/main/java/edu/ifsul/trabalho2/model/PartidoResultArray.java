package edu.ifsul.trabalho2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PartidoResultArray {
    @SerializedName("dados")
    private List<Partido> partidos;

    public List<Partido> getPartidos() {
        return partidos;
    }
}
