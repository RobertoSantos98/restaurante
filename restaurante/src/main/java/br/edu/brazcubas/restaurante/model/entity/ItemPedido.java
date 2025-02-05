package br.edu.brazcubas.restaurante.model.entity;

public class ItemPedido {
    private int id;
    private Pedido pedido;
    private int quantidade;
    private Prato prato;

    public ItemPedido(int id, Pedido pedido, int quantidade, Prato prato) {
        this.id = id;
        this.pedido = pedido;
        this.prato = prato;
        this.quantidade = quantidade;
    }

    public ItemPedido(int quantidade, Prato prato) {
        this.prato = prato;
        this.quantidade = quantidade;
    }

    public ItemPedido(int id) {
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }
}