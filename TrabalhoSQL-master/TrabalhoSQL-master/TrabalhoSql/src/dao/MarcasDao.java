package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bean.MarcasBean;
import connection.ConnectionFactory;


public class MarcasDao {
	
	

		private Connection conexao;

		/**
		 *  Classe que faz a referencia as marcas no qual estamos trabalhando
	  	 * 
		 * @author joe
		 * */
		
		public MarcasDao() {
			

			this.conexao = new ConnectionFactory().obterConexao();
		}



		
		public void cadastrarMarcas(MarcasBean cb) {

			//Comando SQL
			String sql = "INSERT INTO marca(nomeMarca) VALUES (?)";
	         
			
			//Tentar realizar o comando SQL
			try {

				//Enviando os parametros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
				pstmt.setString(1, cb.getNomeMarca());
				
			
				pstmt.execute();


				//Fechar a conex�o
				pstmt.close();

			} catch (Exception e) {

				//caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao inserir dados");


			}


		}

		public DefaultTableModel listarMarcas() {

			//Criando defaulttablemodel
			DefaultTableModel modelo = new DefaultTableModel();

			//Definir as colunas do DefaultTableModel
			modelo.addColumn("C�digo");
			modelo.addColumn("Nome Marca");

			//COMANDO SQL
			String sql = "SELECT * FROM marca";

			//Tentar realizar o comando SQL
			try {
				//Conectar e selecionar o comando SQL
				Statement stmt = conexao.createStatement();

				//Executando comando SQL e obtendo dados
				ResultSet rs = stmt.executeQuery(sql);

				//Listando cursos
				while(rs.next()) {
					modelo.addRow(new Object[]{
							rs.getInt("idMarca"),
							rs.getString("nomeMarca"),
							
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

		
	
		public void ExcluirMarcas(MarcasBean cb) {

			//Comando SQL
			String sql = "DELETE FROM marca WHERE nomeMarca = ? ";

			//Tentar realizar o comando SQL
			try {

				//Enviando os parametros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);
				pstmt.setString(1, cb.getNomeMarca());
				pstmt.execute();


				//Fechar a conex�o
				pstmt.close();

			} catch (Exception e) {

				//caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao excluir dados");


			}


		}
		
	
		public void AlterarMarcas(MarcasBean cb) {

			//Comando SQL
			String sql = "UPDATE marca SET nomeMarca = ? WHERE idMarca = ? ";
			
			//Tentar realizar o comando SQL
			try {

				//Enviando os parametros e executando
				PreparedStatement pstmt = this.conexao.prepareStatement(sql);	
				pstmt.setString(1, cb.getNomeMarca());
				pstmt.setInt(2, cb.getIdMarca());
				pstmt.execute();


				//Fechar a conex�o
				pstmt.close();

			} catch (Exception e) {

				//caso haja falhas
				JOptionPane.showMessageDialog(null, "Falha ao alterar dados");


			}

		}
	}



