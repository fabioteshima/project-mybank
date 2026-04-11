package br.com.adacourse.models;

import br.com.adacourse.enums.TipoConta;

import java.util.Objects;

public class Conta {

    private Long id;
    private String conta;
    private TipoConta tipo;
    private Double saldo;
    private Cliente titular;

    public Conta() {
    }

    public Conta(Long id, String conta, TipoConta tipo, Double saldo, Cliente titular) {
        this.id = id;
        this.conta = conta;
        this.tipo = tipo;
        this.saldo = saldo;
        this.titular = titular;
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
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
                ", saldo=" + saldo +
                ", titular=" + titular +
                '}';
    }
}
