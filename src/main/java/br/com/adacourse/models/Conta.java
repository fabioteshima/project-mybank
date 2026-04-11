package br.com.adacourse.models;

import br.com.adacourse.enums.TipoConta;

import java.util.Objects;

public class Conta {

    private Long id;
    private String conta;
    private TipoConta tipo;
    private Long clienteId;

    public Conta() {
    }

    public Conta(Long id, String conta, TipoConta tipo, Long clienteId) {
        this.id = id;
        this.conta = conta;
        this.tipo = tipo;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return id == conta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", conta='" + conta + '\'' +
                ", tipo=" + tipo +
                ", clienteId=" + clienteId +
                '}';
    }
}
