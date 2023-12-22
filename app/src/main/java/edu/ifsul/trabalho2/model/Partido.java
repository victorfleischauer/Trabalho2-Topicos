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

    @SerializedName("urlLogo")
    @Expose
    private String urlLogo;

    @SerializedName("status")
    @Expose
    private Status status;

    public static class Status {
        private String totalMembros;
        public String getTotalMembros() {
            return totalMembros;
        }
        public void setTotalMembros(String totalMembros) {
            this.totalMembros = totalMembros;
        }
    }

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

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
