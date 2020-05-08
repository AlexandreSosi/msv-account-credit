package enums;


public enum StepStatus {
    START("Iniciado"), PROCESS("Processando"), COMPLETE("Processado");

    private String descricao;

    StepStatus(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
