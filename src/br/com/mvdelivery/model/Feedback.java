package br.com.mvdelivery.model;
public class Feedback {
    public int idFeedback;
    public Pedido pedido;
    public int nota;
    public String comentario;
    public java.time.LocalDateTime dataHora;
    @Override public String toString() { return String.valueOf(idFeedback); }
}
