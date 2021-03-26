package com.greve.Model;

public class Family {

    private long id;
    private String nome;
    private String sobrenome;
    private String docto;
    private int idade;
    private String estadoCivil;
    private int quantidadeResidente;
    private long idAddress;
    private String profissao;
    private double rendaMensal;
    private int quantidadeFilho;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getQuantidadeResidente() {
        return quantidadeResidente;
    }

    public void setQuantidadeResidente(int quantidadeResidente) {
        this.quantidadeResidente = quantidadeResidente;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(long idAddress) {
        this.idAddress = idAddress;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public int getquantidadeFilho() {
        return quantidadeFilho;
    }

    public void setQuantidadeCrianca(int quantidadeFilho) {
        this.quantidadeFilho = quantidadeFilho;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDocto() {
        return docto;
    }

    public void setDocto(String docto) {
        this.docto = docto;
    }
}
