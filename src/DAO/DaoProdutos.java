/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMysql;
import model.ModelProdutos;

/**
 *
 * @author Elvis
 */
public class DaoProdutos extends ConexaoMysql {
    public int salvarProdutoDAO(ModelProdutos pModelProdutos){
        try{
            this.conectar();
            return this.insertSQL("INSERT INTO tbl_produto ("
            +"pro_nome,"
            +"pro_valor,"
            +"pro_estoque"
            +") VALUES ("
            +"'"+pModelProdutos.getProNome()+"',"
            +"'"+pModelProdutos.getProValor()+"',"
            +"'"+pModelProdutos.getProEstoque()+"'"
            
            );
        }catch(Exception e){
            e.printStackTrace();
            return 0;
            
        }finally{
            this.fecharConexao();
        }
        
        
    }
}
