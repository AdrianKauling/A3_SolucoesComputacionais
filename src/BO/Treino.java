package BO;

import java.sql.SQLException;
import java.util.List;

import DAO.FabricaDao;
import DAO.TreinoDao;

public class Treino {
	
	private int idTreino;
	private Aluno aluno;
    private String tipoTreino; 
    private String descricao;  
    private int duracaoMin;    
    private String dataInicio; 
    private TreinoDao treinoDao;

    
    public Treino(Aluno aluno, String tipoTreino, String descricao, int duracaoMin, String dataInicio) throws SQLException {
        this.aluno = aluno;
        this.tipoTreino = tipoTreino;
        this.descricao = descricao;
        this.duracaoMin = duracaoMin;
        this.dataInicio = dataInicio;
        this.treinoDao = new FabricaDao().fabricaTreinoDao();
    }
    
    public Treino(int idTreino, Aluno aluno, String tipoTreino, String descricao, int duracaoMin, String dataInicio) throws SQLException {
        this.idTreino = idTreino;
    	this.aluno = aluno;
        this.tipoTreino = tipoTreino;
        this.descricao = descricao;
        this.duracaoMin = duracaoMin;
        this.dataInicio = dataInicio;
        this.treinoDao = new FabricaDao().fabricaTreinoDao();
    }
    
    public void cadastrarTreino() {
    	this.treinoDao.insert(this);
    }
    
    public List<Treino> buscarTreinosAluno(){
    	return this.treinoDao.findByIdAluno(this.aluno);
    }

    public int getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getTipoTreino() {
        return tipoTreino;
    }

    public void setTipoTreino(String tipoTreino) {
        this.tipoTreino = tipoTreino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    
    public void exibirInfoTreino() {
        System.out.println("Treino para: " + aluno.getNomeAluno());
        System.out.println("Tipo: " + tipoTreino);
        System.out.println("Descrição: " + descricao);
        System.out.println("Duração: " + duracaoMin + " minutos");
        System.out.println("Início: " + dataInicio);
    }
    
    

}
