/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexoes;
    
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author Elvis
 */
public class ConexaoMysql {
    private boolean status = false;
    private String mensagem = "";
    private Connection con = null;
    private Statement statement;
    private ResultSet resultSet;
    
    
    
    private String servidor = "localhost";
    private String nomeDoBanco = "vendasdb";
    private String usuario = "root";
    private String senha = "";
    
    
    public ConexaoMysql(){}
    
    public ConexaoMysql(String servidor, String nomeDoBanco, String usuario,String senha){
        this.servidor =servidor;
        this.nomeDoBanco = nomeDoBanco;
        this.usuario = usuario;
        this.senha = senha;
        
    }
    
    public Connection conectar(){
        try{
            
            //driver mysql
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            
            String url = "jdbc:musql://"+ servidor+ "/"+nomeDoBanco;
            this.setCon((Connection) DriverManager.getConnection(url, usuario, senha));
            
            this.status = true;
            
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.getCon();
        
    }
    
    public boolean executarSQL(String sql){
        try{
            this.setStatement((Statement) getCon().createStatement());
            
            this.setResultSet(getStatement().executeQuery(sql));
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
            
        }
        return true;
    }
    
    public boolean executarUpdateDeleteSQL(String pSQL){
        try {
            
            //createStatement de con para criar o Statement
            this.setStatement((Statement) getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            getStatement().executeUpdate(pSQL);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Executa insert SQL
     * @param pSQL
     * @return boolean
     */
    public int insertSQL(String pSQL){
        int status = 0;
        try {
            //createStatement de con para criar o Statement
            this.setStatement((Statement) getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            this.getStatement().executeUpdate(pSQL);
            
            //consulta o ultimo id inserido
            this.setResultSet(this.getStatement().executeQuery("SELECT last_insert_id();"));
            
            //recupera o ultimo id inserido
            while(this.resultSet.next()){
                status = this.resultSet.getInt(1);
            }
            
            //retorna o ultimo id inserido
            return status;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return status;
        }
    }

    /**
     * encerra a conexão corrente
     * @return boolean
     */
    public boolean fecharConexao(){
       try {
           if((this.getResultSet() != null) && (this.statement != null)){
               this.getResultSet().close();
               this.statement.close();
           }
           this.getCon().close();
           return true;
       } catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       return false;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    public void setNomeDoBanco(String nomeDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
