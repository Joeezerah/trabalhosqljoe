package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bean.ModeloBean;
import connection.ConnectionFactory;

public class ModeloDao {
	
	
			private Connection conexao;

			/**
			 *  Classe que faz referencia as pe�as do programa
		  	 * 
			 * @author joe
			 * */
			public ModeloDao() {
				

				this.conexao = new ConnectionFactory().obterConexao();
			}

			
			public void cadastrarModelo(ModeloBean cb) {

				//Comando SQL
				String sql = "INSERT INTO modelo(modeloPeca) VALUES (?)";
		         
				
				//Tentar realizar o comando SQL
				try {

					//Enviando os parametros e executando
					PreparedStatement pstmt = this.conexao.prepareStatement(sql);
					pstmt.setString(1, cb.getModeloPeca());
					
				
					pstmt.execute();


					//Fechar a conex�o
					pstmt.close();

				} catch (Exception e) {

					//caso haja falhas
					JOptionPane.showMessageDialog(null, "Falha ao inserir dados");


				}


			}

			//Metodo para selecionar um curso
			public DefaultTableModel listarModelos() {

				//Criando defaulttablemodel
				DefaultTableModel modelo = new DefaultTableModel();

				//Definir as colunas do DefaultTableModel
				modelo.addColumn("C�digo");
				modelo.addColumn("Nome Modelo Pe�a");

				//COMANDO SQL
				String sql = "SELECT * FROM modelo";

				//Tentar realizar o comando SQL
				try {
					//Conectar e selecionar o comando SQL
					Statement stmt = conexao.createStatement();

					//Executando comando SQL e obtendo dados
					ResultSet rs = stmt.executeQuery(sql);

					//Listando cursos
					while(rs.next()) {
						modelo.addRow(new Object[]{
								rs.getInt("idModelo"),
								rs.getString("modeloPeca"),
								
						});

					}

					//Fechar a conex�o
					stmt.close();



				} catch (Exception e) {
					//caso haja falhas na sele�ao
					JOptionPane.showMessageDialog(null, "Falha ao selecionar os cursos");
				}
				return modelo;
			}

			public void ExcluirModelos(ModeloBean cb) {

				//Comando SQL
				String sql = "DELETE FROM modelo WHERE modeloPeca = ? ";

				//Tentar realizar o comando SQL
				try {

					//Enviando os parametros e executando
					PreparedStatement pstmt = this.conexao.prepareStatement(sql);
					pstmt.setString(1, cb.getModeloPeca());
					pstmt.execute();


					//Fechar a conex�o
					pstmt.close();

				} catch (Exception e) {

					//caso haja falhas
					JOptionPane.showMessageDialog(null, "Falha ao excluir dados");


				}


			}
			public void AlterarModelos(ModeloBean cb) {

				//Comando SQL
				String sql = "UPDATE modelo SET modeloPeca = ? WHERE idModelo = ? ";
				
				//Tentar realizar o comando SQL
				try {

					//Enviando os parametros e executando
					PreparedStatement pstmt = this.conexao.prepareStatement(sql);	
					pstmt.setString(1, cb.getModeloPeca());
					pstmt.setInt(2, cb.getIdModelo());
					pstmt.execute();


					//Fechar a conex�o
					pstmt.close();

				} catch (Exception e) {

					//caso haja falhas
					JOptionPane.showMessageDialog(null, "Falha ao alterar dados");


				}


}
}
