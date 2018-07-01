package connection;

//Importar Classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *  Classe que faz a conex�o
	 * 
 * @author joe
 * */
public class ConnectionFactory {
	
	//Atributos
	private final String url = "jdbc:mysql://localhost:3306/trabalhosql";
	private final String user = "root";
	private final String password = "";
	
	//M�todo para obter a conex�o
	public Connection obterConexao() {
		
		//Variavel contendo a conexao
		Connection conexao = null;
		
		//Tentar Realizar a conexao
		try {
			conexao = DriverManager.getConnection(url, user, password);
			//JOptionPane.showMessageDialog(null,"Tudo Okay");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null,"Deu Merda Aqui"+e.getMessage());
		    throw new RuntimeException(e);
		    
		}
		//Retornar a conex�o
		return conexao;
		
	}
	
	
}
