package br.com.mvdelivery.model;
public class Pedido {
    public int idPedido;
    public Cliente cliente;
    public Entregador entregador;
    public java.time.LocalDateTime dataPedido;
    public java.math.BigDecimal valor;
    public StatusPedido status;
    public String enderecoEntrega;
    @Override public String toString() { return String.valueOf(idPedido); }
}
