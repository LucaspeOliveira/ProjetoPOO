package projeto.model;

public class EntityUtensilios {
// Variáveis:
    private int id;
    private String nome;
    private String tipo;
    private int quantidade;
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public String getTipo() {
        return tipo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
