package br.ufrn.jardim.modelo;

import java.io.Serializable;

/**
 * Created by wanderson on 21/04/15.
 */
public class Vaso implements Serializable {

    public static String[] COLUNAS = new String[]{"ID","DESCRICAO","UMIDADE_AR","TEMP","UMIDADE_SOLO","LUZ","MAC"};

    //valore configurados
    private int id;
    private int umidadeSolo;
    private int umidadeAr;
    private int temperatura;
    private int luminosidade;

    //valores recebidos do vaso
    private int atUmidadeSolo;
    private int atUmidadeAr;
    private int atTemperatura;
    private int atLuminosidade;

    private String imagem;
    private String descricao;

    private String MAC;


    public Vaso(){

        this.umidadeSolo = 0;
        this.umidadeAr = 0;
        this.temperatura = 0;
        this.luminosidade = 0;
        this.atUmidadeAr = 0;
        this.atTemperatura = 0;
        this.atUmidadeSolo = 0;
        this.atLuminosidade = 0;

        this.descricao = "";
        this.imagem = "";

    }


    public Vaso(String aDescricao){

        this.umidadeSolo = 0;
        this.umidadeAr = 0;
        this.temperatura = 0;
        this.luminosidade = 0;
        this.atUmidadeAr = 0;
        this.atTemperatura = 0;
        this.atUmidadeSolo = 0;
        this.atLuminosidade = 0;

        this.descricao = aDescricao;
        this.imagem = "";

    }

    public int getUmidadeSolo() {
        return umidadeSolo;
    }

    public void setUmidadeSolo(int umidadeSolo) {
        this.umidadeSolo = umidadeSolo;
    }

    public int getUmidadeAr() {
        return umidadeAr;
    }

    public void setUmidadeAr(int umidadeAr) {
        this.umidadeAr = umidadeAr;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }

    public int getAtUmidadeSolo() {
        return atUmidadeSolo;
    }

    public void setAtUmidadeSolo(int atUmidadeSolo) {
        this.atUmidadeSolo = atUmidadeSolo;
    }

    public int getAtumidadeAr() {
        return atUmidadeAr;
    }

    public void setAtumidadeAr(int atumidadeAr) {
        this.atUmidadeAr = atumidadeAr;
    }

    public int getAtTemperatura() {
        return atTemperatura;
    }

    public void setAtTemperatura(int attemperatura) {
        this.atTemperatura = attemperatura;
    }

    public int getAtLuminosidade() {
        return atLuminosidade;
    }

    public void setAtLuminosidade(int atluminosidade) {
        this.atLuminosidade = atluminosidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
