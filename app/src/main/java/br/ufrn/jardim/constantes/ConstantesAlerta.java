package br.ufrn.jardim.constantes;

/**
 * Created by wanderson on 21/04/15.
 */
public class ConstantesAlerta {

    //TIPO ALERTA
    public static final int ALERTA_TIPO_DATA = 1;
    public static final int ALERTA_TIPO_VALOR = 2;

    //TIPO DE REPETICAO

    public static final int ALERTA_REP_DIARIO = 1;
    public static final int ALERTA_REP_SEMANAL = 2;
    public static final int ALERTA_REP_MENSAL = 3;
    public static final int ALERTA_REP_ANUAL = 4;


    public static String getTipoAlerta(int constante) throws Exception {

        switch (constante){
            case ALERTA_TIPO_DATA: return "Alerta por data";
            case ALERTA_TIPO_VALOR: return "Alerta por Valor";
            default:throw new Exception("Tipo de alerta inixistente!");
        }

    }

    public static String getTipoRepeticao(int constante) throws Exception {

        switch (constante){
            case ALERTA_REP_DIARIO: return "Diariamente";
            case ALERTA_REP_SEMANAL: return "Semanalmente";
            case ALERTA_REP_MENSAL: return "Mensalmente";
            case ALERTA_REP_ANUAL: return "Anualmente";
            default:throw new Exception("Tipo de repetição inixistente!");
        }

    }

}
