package algar.desafio.api.dto;

public class ProdutoDTO {
    
    private String nome;
    private String descricao;
    private double valor;
    private int quantiade;

    
    public ProdutoDTO(String nome, String descricao, double valor, int quantiade) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantiade = quantiade;
    }


    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantiade() {
        return this.quantiade;
    }
    public void setQuantiade(int quantiade) {
        this.quantiade = quantiade;
    }

    

}
