package edu.ifsul.trabalho2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeputadoResultArray {
    @SerializedName("dados")
    @Expose
    List<Deputado> deputadoList;

    public List<Deputado> getDeputadoList() {
        return deputadoList;
    }
}
