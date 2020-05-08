package enums;

public enum StepsName {

    INCLUDE("Cadastro Account"),FRAUD("Analise de Fraude"), RISK("Analise de Risco"), DOCUMENT("Analise documentos");

    private String descricao;

    StepsName(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
