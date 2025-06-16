package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BO.Aluno;
import BO.Treino;

public class TreinoDao {
	Connection conn = null;
	
	public TreinoDao(Connection conn){
		this.conn = conn;
	}
	
	public void insert(Treino obj) {
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO Treinos (tipoDeTreino, descricao, duracao, dataDeInicio, id_aluno) VALUES (?,?,?,?,?)";
			ps = this.conn.prepareStatement(sql);
			ps.setString(1, obj.getTipoTreino());
			ps.setString(2, obj.getDescricao());
			ps.setInt(3, obj.getDuracaoMin());
			ps.setString(4, obj.getDataInicio());
			ps.setInt(5, obj.getAluno().getIdAluno());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Aluno cadastrado com sucesso!");
			}
			else {
				throw new RuntimeException("Unexpected error! No rows affected!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(Treino obj) throws SQLException {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE Treinos SET tipoDeTreino=?, descricao=?, duracao=?, dataDeInicio=?, id_aluno=? WHERE id=?";
			ps = this.conn.prepareStatement(sql);
			ps.setString(1,obj.getTipoTreino());
			ps.setString(2,obj.getDescricao());
			ps.setInt(3,obj.getDuracaoMin());
			ps.setString(4,obj.getDataInicio());
			ps.setInt(5,obj.getAluno().getIdAluno());
			ps.setInt(6,obj.getIdTreino());
			int retorno = ps.executeUpdate();
			
			if(retorno > 0){
				System.out.println("Aluno alterado com sucesso");
			}else{
				System.out.println("Não foi possível alterar os dados do Aluno!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	public void deleteByIdAluno(Integer id) {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE from Treinos where id_aluno=?";
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, id);
			int retorno = ps.executeUpdate();
			if(retorno > 0){
				System.out.println("Treino(s) excluido(s) com sucesso");
			}else{
				System.out.println("Não foi possível excluir treinos!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	public List<Treino> findByIdAluno(Aluno aluno) {
		PreparedStatement psTreino = null;
		ResultSet rsTreino = null;
		try {
			String sql = "SELECT * FROM Treinos WHERE id_aluno=?";
			psTreino = this.conn.prepareStatement(sql);
			psTreino.setInt(1, aluno.getIdAluno());
			rsTreino = psTreino.executeQuery();
			
			List <Treino> treinos = new ArrayList<>();
			
			while(rsTreino.next()) {
					
				treinos.add(new Treino(rsTreino.getInt("id"),aluno,rsTreino.getString("tipoDeTreino"),rsTreino.getString("descricao"),rsTreino.getInt("duracao"), rsTreino.getString("dataDeInicio")));
				
			}
			return treinos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				if (psTreino != null) psTreino.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Treino findById(Integer id) {
		PreparedStatement psTreino = null;
		PreparedStatement psAluno = null;
		ResultSet rsTreino = null;
		ResultSet rsAluno = null;
		try {
			String sql = "SELECT * FROM Treinos WHERE id=?";
			psTreino = this.conn.prepareStatement(sql);
			psTreino.setInt(1, id);
			rsTreino = psTreino.executeQuery();
			if(rsTreino.next()) {
				sql = "SELECT * FROM Alunos WHERE id=?";
				psAluno = this.conn.prepareStatement(sql);
				psAluno.setInt(1, rsTreino.getInt("id_aluno"));
				rsAluno = psAluno.executeQuery();
				if(rsAluno.next()) {
					
					Aluno aluno = new Aluno(rsAluno.getInt("id"), rsAluno.getString("nome"), rsAluno.getString("cpf"), rsAluno.getString("dataNasc"), rsAluno.getString("telefone"), rsAluno.getString("email"));
					return new Treino(rsTreino.getInt("id"),aluno,rsTreino.getString("tipoDeTreino"),rsTreino.getString("descricao"),rsTreino.getInt("duracao"), rsTreino.getString("dataDeInicio"));
				}else {
					System.out.println("Aluno não encontrado");
					return null;
				}
			}else {
				System.out.println("Treino não encontrado");
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				if (psTreino != null) psTreino.close();
				if (psAluno != null) psAluno.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Treino> findAll(){
		PreparedStatement psTreinos = null;
		PreparedStatement psAluno = null;
		ResultSet rsTreinos = null;
		ResultSet rsAluno = null;
		List<Treino> treinos = new ArrayList<>();
		try {
			String sql = "SELECT * FROM TREINOS";
			psTreinos = this.conn.prepareStatement(sql);
			rsTreinos = psTreinos.executeQuery();
			while(rsTreinos.next()) {
				sql = "SELECT * FROM Alunos WHERE id=?";
				psAluno = this.conn.prepareStatement(sql);
				psAluno.setInt(1, rsTreinos.getInt("id_aluno"));
				rsAluno = psAluno.executeQuery();
				if(rsAluno.next()) {
					Aluno aluno = new Aluno(rsAluno.getInt("id"), rsAluno.getString("nome"), rsAluno.getString("cpf"), rsAluno.getString("dataNasc"), rsAluno.getString("telefone"), rsAluno.getString("email"));
					treinos.add(new Treino(rsTreinos.getInt("id"),aluno,rsTreinos.getString("tipoDeTreino"),rsTreinos.getString("descricao"),rsTreinos.getInt("duracao"), rsTreinos.getString("dataDeInicio")));
				}else {
					System.out.println("Aluno não encontrado");
					return null;
				}
				
			}
			return treinos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				if (psTreinos != null) psTreinos.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeConnection() throws SQLException {
		if(conn != null) {
			this.conn.close();
		}
	}

}
