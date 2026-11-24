package br.com.mvdelivery.model;
public class Entregador {
    public int idEntregador;
    public String nome;
    public String contato;
    public String placaMoto;
    public boolean disponivel;
    @Override public String toString() { return String.valueOf(idEntregador) + " - " + nome; }
}
