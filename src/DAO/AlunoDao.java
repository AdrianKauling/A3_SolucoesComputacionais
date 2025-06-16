package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BO.Aluno;
import database.Conexao;
public class AlunoDao{

	Connection conn;
	
	public AlunoDao(Connection conn) {
		this.conn = conn;
	}


	public void insert(Aluno obj) {
		Aluno aluno1 = this.findById(obj.getIdAluno());
		PreparedStatement ps = null;
		
		if (aluno1 == null) {
			try {
				String sql = "INSERT INTO Alunos (nome, cpf, dataNasc, telefone, email) VALUES  (?,?,?,?,?)";
				ps = this.conn.prepareStatement(sql);
				ps.setString(1, obj.getNomeAluno());
				ps.setString(2, obj.getCpf());
				ps.setString(3, obj.getDataNasc());
				ps.setString(4, obj.getTelefone());
				ps.setString(5, obj.getEmail());
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
				Conexao.closeStatement(ps);
			}
		}else {
			System.out.println("CPF já cadastrado!");
		}
		
	}


	public void update(Aluno obj) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			
			String sql = "UPDATE Alunos SET nome=?, cpf=?, dataNasc=?, telefone=?, email=? WHERE id=?";
			ps = this.conn.prepareStatement(sql);
			ps.setString(1,obj.getNomeAluno());
			ps.setString(2,obj.getCpf());
			ps.setString(3,obj.getDataNasc());
			ps.setString(4,obj.getTelefone());
			ps.setString(5,obj.getEmail());
			ps.setInt(6,obj.getIdAluno());
			int retorno = ps.executeUpdate();
			
			if(retorno > 0){
				System.out.println("Aluno alterado com sucesso");
			}else{
				System.out.println("Não foi possível alterar os dados do Aluno!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Conexao.closeStatement(ps);
		}
	}


	public void deleteById(Integer id) {
;		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			String sql = "DELETE from Alunos where id=?";
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, id);
			int retorno = ps.executeUpdate();
			if(retorno > 0){
				System.out.println("Aluno excluido com sucesso");
			}else{
				System.out.println("Não foi possível excluir os dados do Aluno!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			Conexao.closeStatement(ps);
		}
		
	}


	public Aluno findById(int id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM ALUNOS WHERE id=?";
			ps = this.conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("dataNasc"), rs.getString("telefone"), rs.getString("email"));
			}else {
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			Conexao.closeStatement(ps);
			Conexao.closeResultSet(rs);
		}
		
		
	}

	
	public List<Aluno> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Aluno> alunos = new ArrayList<>();
		try {
			String sql = "SELECT * FROM ALUNOS";
			if(this.conn ==null) {
				System.out.print("ta nulo");
			}
			ps = this.conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				alunos.add(new Aluno(rs.getInt("id"),rs.getString("nome"), rs.getString("cpf"), rs.getString("dataNasc"), rs.getString("telefone"), rs.getString("email")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Conexao.closeStatement(ps);
			Conexao.closeResultSet(rs);
		}
		return alunos;
		
		
	}
	
	public Aluno findByCpf(String cpf) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM ALUNOS WHERE cpf=?";
			ps = this.conn.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				return new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("dataNasc"), rs.getString("telefone"), rs.getString("email"));
			}else {
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			Conexao.closeStatement(ps);
			Conexao.closeResultSet(rs);
		}
		
		
	}
	
	public void closeConnection() throws SQLException {
		if(conn != null) {
			this.conn.close();
		}
	}

}
