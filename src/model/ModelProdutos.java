/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elvis
 */
public class ModelProdutos {
    private int idProduto;
    private String proNome;
    private double proValor;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public double getProValor() {
        return proValor;
    }

    public void setProValor(double proValor) {
        this.proValor = proValor;
    }

    public int getProEstoque() {
        return proEstoque;
    }

    public void setProEstoque(int proEstoque) {
        this.proEstoque = proEstoque;
    }
    private int proEstoque;
    
    
}
