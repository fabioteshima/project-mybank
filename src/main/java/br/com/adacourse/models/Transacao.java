package br.com.adacourse.models;

import br.com.adacourse.enums.TipoTransacao;

import java.time.LocalDateTime;

public class Transacao {

    private Long id;
    private TipoTransacao tipo;
    private Double valor;
    private LocalDateTime dataHora;
    private Conta conta_origem_id;
    private Conta conta_destino_id;

    public Transacao() {
    }

    public Transacao(Long id, TipoTransacao tipo, Double valor, LocalDateTime dataHora, Conta conta_origem_id, Conta conta_destino_id) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.conta_origem_id = conta_origem_id;
        this.conta_destino_id = conta_destino_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Conta getConta_origem_id() {
        return conta_origem_id;
    }

    public void setConta_origem_id(Conta conta_origem_id) {
        this.conta_origem_id = conta_origem_id;
    }

    public Conta getConta_destino_id() {
        return conta_destino_id;
    }

    public void setConta_destino_id(Conta conta_destino_id) {
        this.conta_destino_id = conta_destino_id;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", dataHora=" + dataHora +
                ", conta_origem_id='" + conta_origem_id + '\'' +
                ", conta_destino_id='" + conta_destino_id + '\'' +
                '}';
    }
}
