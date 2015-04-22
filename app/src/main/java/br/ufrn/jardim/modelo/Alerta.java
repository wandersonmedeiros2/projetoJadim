package br.ufrn.jardim.modelo;

import br.ufrn.jardim.constantes.ConstantesAlerta;

/**
 * Created by wanderson on 21/04/15.
 */
public class Alerta {

    private int tipoAlerta;
    private boolean ativo;
    private String descricao;
    private String imagem;
    private Vaso vaso;


    public Alerta(){

        this.tipoAlerta = ConstantesAlerta.ALERTA_TIPO_VALOR;
        this.ativo = true;
        this.descricao = "";
        this.imagem = "";
        this.vaso = null;

    }

    public Alerta(String aDescricao){

        this.tipoAlerta = ConstantesAlerta.ALERTA_TIPO_VALOR;
        this.ativo = true;
        this.descricao = aDescricao;
        this.imagem = "";
        this.vaso = null;

    }


    public int getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(int tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Vaso getVaso() {
        return vaso;
    }

    public void setVaso(Vaso vaso) {
        this.vaso = vaso;
    }
}
