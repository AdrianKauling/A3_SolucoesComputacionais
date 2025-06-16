package DAO;

import database.Conexao;

public class FabricaDao {
	
	public static AlunoDao fabricaAlunoDao (){
		return new AlunoDao(Conexao.getConnection());
	}
	
	public static TreinoDao fabricaTreinoDao (){
		return new TreinoDao(Conexao.getConnection());
	}
}
