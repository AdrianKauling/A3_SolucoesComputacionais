package BO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Year;

import database.Conexao;
import DAO.AlunoDao;
import DAO.FabricaDao;
import java.util.List;
import java.util.regex.*;

public class Aluno {
	private int idAluno;
    private String nomeAluno;
    private String cpf;
    private String dataNasc;
    private String telefone;
    private String email;

    // Construtor
    public Aluno(String nomeAluno, String cpf, String dataNasc, String telefone, String email) throws SQLException {
        this.nomeAluno = nomeAluno;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
    }
    
 // Construtor
    public Aluno(int idAluno,String nomeAluno, String cpf, String dataNasc, String telefone, String email) throws SQLException {
    	this.idAluno = idAluno;
    	this.nomeAluno = nomeAluno;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
    }
    
    // Getters e Setters (opcional)
    
    public int getIdAluno() {
        return idAluno;
    }
    
    public String getNomeAluno() {
        return nomeAluno;
    }
    
    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }
    
    public String setDataNasc(String dataNasc) {
    	return this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public String setTelefone(String telefone) {
    	return this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    
    public String setEmail(String email) {
    	return this.email = email;
    }

    public void exibirInfo() {
        System.out.println("Aluno: " + nomeAluno + ", CPF: " + cpf);
    }
    
    public static boolean validaCPF(String cpf, AlunoDao alunoDao) {
    	String padrao1 = "^[0-9][0-9][0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9][0-9]$";
    	Pattern pattern = Pattern.compile(padrao1);
        Matcher matcher = pattern.matcher(cpf);
        
        if(!matcher.matches()) {
        	System.out.println("Formato INCORRETO!");
        	System.out.println("Informe o CPF no formato: 000.000.000-00");
        	return false;
        }
        
        if(alunoDao.findByCpf(cpf) != null) {
        	System.out.println("CPF INFORMADO JÁ EXISTE!");
        	return false;
        }
        
        return true;
        
    }
    
    public static String formataData(String data) {
    	String dia = data.substring(0,2);
    	String mes = data.substring(3,5);
    	String ano = data.substring(6,data.length());
    	
    	return ano+"-"+mes+"-"+dia;
    }
    
    public static boolean validaData(String data) {
    	String padrao1 = "^[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]$";
    	Pattern pattern = Pattern.compile(padrao1);
        Matcher matcher = pattern.matcher(data);
        if(!matcher.matches()) {
        	System.out.println("Formato INCORRETO!");
        	System.out.println("Insira a data no formato: DIA/MÊS/ANO");
        	return false;
        }
        
        int anoAtual = Year.now().getValue();
        if(Integer.parseInt(data.substring(0,2))>31 || Integer.parseInt(data.substring(3,5))>12 || Integer.parseInt(data.substring(6,data.length()))>anoAtual) {
        	System.out.println("Data INCORRETO!");
        	System.out.println("Informe uma data válida");
        	return false;
        }
        
    	
    	return true;
    }
    
    public static boolean validaTelefone(String telefone) {
        String padrao1 = "^\\([0-9]{2}\\) 9[0-9]{4}-[0-9]{4}$";
        Pattern pattern = Pattern.compile(padrao1);
        Matcher matcher = pattern.matcher(telefone);

        if (!matcher.matches()) {
            System.out.println("Formato INCORRETO!");
            System.out.println("Insira o telefone no formato: (47) 90000-0000");
            return false;
        }

        return true;
    }
    
    public static boolean validaEmail(String email) {
    	
    	String padrao1 = "^.*@.*\\.com$";
    	String padrao2 = "^.*@.*\\.com\\..*$";
    	Pattern pattern = Pattern.compile(padrao1);
    	Pattern pattern2 = Pattern.compile(padrao2);
    	Matcher matcher = pattern.matcher(email);
        Matcher matcher2 = pattern2.matcher(email);
        if(!matcher.matches() && !matcher2.matches()) {
        	System.out.println("Formato INCORRETO!");
        	System.out.println("Insira o email no formato: exemplo@exemplo.com");
        	return false;
        }
    	
    	return true;
    }


}
