package br.com.mvdelivery.model;
public class Cliente {
    public int idCliente;
    public String nome;
    public String telefone;
    public String endereco;
    public String observacoes;
    @Override public String toString() { return String.valueOf(idCliente) + " - " + nome; }
}
