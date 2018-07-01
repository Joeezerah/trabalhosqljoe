package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bean.EstoquesBean;
import connection.ConnectionFactory;

public class EstoquesDao {
	

	private Connection conexao;

	/**
	 *  Classe que faz a contagem de produtos em estoque
  	 * 
	 * @author joe
	 * */
	public EstoquesDao() {


		this.conexao = new ConnectionFactory().obterConexao();
	}


	public void cadastrarEstoque(EstoquesBean cb) {

		//Comando SQL
		String sql = "INSERT INTO totalpecas(idModelo,totalPecas) VALUES (?,?)";
         
		
		//Tentar realizar o comando SQL
		try {

			//Enviando os parametros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);	
			pstmt.setInt(1, cb.getIdModelo());
			pstmt.setInt(2, cb.getTotal());
			pstmt.execute();


			//Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			//caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao inserir dados");


		}


	}

	public DefaultTableModel listarEstoques() {

		//Criando defaulttablemodel
		DefaultTableModel modelo = new DefaultTableModel();

		//Definir as colunas do DefaultTableModel
		modelo.addColumn("Id Pe�as");
		modelo.addColumn("Total De pe�as");

		//COMANDO SQL
		String sql = "SELECT * FROM totalpecas";

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
						rs.getInt("totalPecas")
						
				});

			}

			//Fechar a conex�o
			stmt.close();



		} catch (Exception e) {
			//caso haja falhas na sele�ao
			JOptionPane.showMessageDialog(null, "Falha ao selecionar");
		}
		return modelo;
	}
	
	

	public void ExcluirEstoques(EstoquesBean cb) {

		//Comando SQL
		String sql = "DELETE FROM totalpecas WHERE totalPecas = ? AND idModelo = ? ";

		//Tentar realizar o comando SQL
		try {

			//Enviando os parametros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);
			pstmt.setInt(1, cb.getTotal());
			pstmt.setInt(2, cb.getIdModelo());
			pstmt.execute();


			//Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			//caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao excluir dados");


		}


	}
	
	public void AlterarEstoque(EstoquesBean cb) {

		//Comando SQL
		String sql = "UPDATE totalpecas SET totalPecas = ?  WHERE idModelo = ? ";
		
		//Tentar realizar o comando SQL
		try {

			//Enviando os parametros e executando
			PreparedStatement pstmt = this.conexao.prepareStatement(sql);	
			pstmt.setInt(1, cb.getTotal());
			pstmt.setInt(2, cb.getIdModelo());
			pstmt.execute();


			//Fechar a conex�o
			pstmt.close();

		} catch (Exception e) {

			//caso haja falhas
			JOptionPane.showMessageDialog(null, "Falha ao alterar dados"+e.getMessage());


		}

	}
}
