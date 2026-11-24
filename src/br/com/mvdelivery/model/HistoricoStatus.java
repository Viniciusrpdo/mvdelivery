package br.com.mvdelivery.model;
public class HistoricoStatus {
    public int idHist;
    public Pedido pedido;
    public StatusPedido status;
    public java.time.LocalDateTime dataHora;
    public String observacoes;
    @Override public String toString() { return String.valueOf(idHist); }
}
