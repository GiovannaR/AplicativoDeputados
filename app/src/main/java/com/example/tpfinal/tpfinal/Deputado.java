package com.example.tpfinal.tpfinal;

/**
 * Created by Hyalen Caldeira on 03/12/2017.
 */

public class Deputado {
    private String id;
    private String uri;
    private String nome;
    private String siglaPartido;
    private String uriPartido;
    private String siglaUF;
    private String idLegislatura;
    private String urlFoto;

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getNome() {
        return nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public String getUriPartido() {
        return uriPartido;
    }

    public String getSiglaUF() {
        return siglaUF;
    }

    public String getIdLegislatura() {
        return idLegislatura;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public void setUriPartido(String uriPartido) {
        this.uriPartido = uriPartido;
    }

    public void setSiglaUF(String siglaUF) {

        this.siglaUF = siglaUF;
    }

    public void setIdLegislatura(String idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
