package entities;

import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "arquivo")
@Getter
public class Arquivo {

    @Id
    private String id;
    private String data;
    private String nome;
    private String conteudo;

    public Arquivo(String nome, String conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", nome='" + nome + '\'' +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}