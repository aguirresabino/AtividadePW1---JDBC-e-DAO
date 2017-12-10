/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.model;

/**
 *
 * @author aguirre
 */
public class Cliente {
    private int id;
    private String nome;
    private String documento;
    private double saldo;
    private ClienteAtivoEnum ativo;
    
    public Cliente(){}

    public Cliente(int id, String nome, String documento, double saldo, ClienteAtivoEnum ativo) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.saldo = saldo;
        this.ativo = ativo;
    }

    public Cliente(String nome, String documento, double saldo, ClienteAtivoEnum ativo) {
        this.nome = nome;
        this.documento = documento;
        this.saldo = saldo;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ClienteAtivoEnum getAtivo() {
        return ativo;
    }

    public void setAtivo(ClienteAtivoEnum ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != cliente.id) return false;
        return documento != null ? documento.equals(cliente.documento) : cliente.documento == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (documento != null ? documento.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", saldo=" + saldo +
                ", ativo=" + ativo +
                '}';
    }
}
