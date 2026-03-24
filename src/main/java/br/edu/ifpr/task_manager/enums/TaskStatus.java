package br.edu.ifpr.task_manager.enums;

public enum TaskStatus {

    IN_PROGRESS("Em andamento"),
    COMPLETED("Concluído");

    private final String descricao;

    // Construtor
    TaskStatus(String descricao) {
        this.descricao = descricao;
    }

    // Getter
    public String getDescricao() {
        return descricao;
    }
}
