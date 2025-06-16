package Application;

import BO.Aluno;
import BO.Treino;
import DAO.AlunoDao;
import DAO.FabricaDao;
import DAO.TreinoDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDao alunoDao = FabricaDao.fabricaAlunoDao();
        TreinoDao treinoDao = FabricaDao.fabricaTreinoDao();
        int resposta = 1;
        while (resposta != 0){
            System.out.println("Bem vindo ao LogFit");
            System.out.println("Selecione uma das opções");
            System.out.println("1 - Alunos \n2 - Treinos");
            System.out.println("0 - Sair do sistema ");
            resposta = scanner.nextInt();
            switch(resposta){
                case 1:
                    System.out.println("Opções de 'Alunos' ");
                    System.out.println("1 - Mostrar todos os alunos");
                    System.out.println("2 - Editar perfil de aluno");
                    System.out.println("3 - Excluir aluno do sistema");
                    System.out.println("4 - Cadastrar novo aluno");
                    System.out.println("5 - Voltar para o menu principal");
                    System.out.println("0 - Sair do sistema ");
                    resposta = scanner.nextInt();
                    switch(resposta){
                        case 1:
                            System.out.println("Alunos cadastrados: ");
                            listarAlunos(alunoDao);
                            break;
                            
                            
                            
                        case 2:
                            System.out.println("Editar perfil de aluno");

                           if(listarAlunos(alunoDao)==0) {
                        	   break;
                           }
                           try {
                               System.out.println("Id do antigo aluno");
                               int id = scanner.nextInt();
                               Aluno aluno = alunoDao.findById(id);
                               scanner.nextLine(); //descarte de nova linha
                               System.out.println("Deseja editar o nome aluno? (s/n)");
                               String editarNome = scanner.nextLine();
                               if(editarNome.equals("s") || editarNome.equals("S")) {
                            	   System.out.println("Nome do novo aluno");
                            	   aluno.setNomeAluno(scanner.nextLine());
                            	  // scanner.nextLine(); //descarte de nova linha
                               }
                               
                               System.out.println("Deseja editar o cpf aluno? (s/n)");
                               String editarCPF = scanner.nextLine();
                               if(editarCPF.equals("s") || editarCPF.equals("S")) {
                            	   
                            	   boolean validaCpf = false;
                                   String cpf ="";
                                   
                                   while(!validaCpf) {
                                   	System.out.println("CPF novo: {Formato: 000.000.000-00}");
                                       cpf = scanner.next();
                                       
                                       if(Aluno.validaCPF(cpf, alunoDao)) validaCpf = true;
                                   }
                               	   aluno.setCPF(cpf);
                               	   scanner.nextLine();
                               }
                               
                               
                               System.out.println("Deseja editar a data de nascimento aluno? (s/n)");
                               String editarDataNasc = scanner.next();
                               if(editarDataNasc.equals("s") || editarDataNasc.equals("S")) {
                            	   boolean validaData = false;
                                   String dataNasc = "";
                                   
                                   while (!validaData) {
                                   	System.out.println("Nova data de nascimento: {Formato: DIA/MÊS/ANO}");
                                       dataNasc = scanner.next();
                                       scanner.nextLine();
                                       
                                       if(Aluno.validaData(dataNasc)) validaData = true;
                                   }
                            	   
                            	   aluno.setDataNasc(Aluno.formataData(dataNasc));
                               }
                               
                               
                               System.out.println("Deseja alterar o telefone aluno? (s/n)");
                               String editarTelefone = scanner.next();
                               scanner.nextLine();
                               if(editarTelefone.equals("s") || editarTelefone.equals("S")) {
                            	   
                            	   boolean validaTelefone = false;
                                   String telefone = "";
                                   
                                   while (!validaTelefone) {
                                   	System.out.println("Telefone do Aluno: {Formato: (47) 90000-0000}");
                                       telefone = scanner.nextLine().trim();
                                       
                                       if(Aluno.validaTelefone(telefone)) validaTelefone = true;
                                   }
                                   
                            	   aluno.setTelefone(telefone);
                               }
                               
                               
                               System.out.println("Deseja alterar o email aluno? (s/n)");
                               String editarEmail = scanner.next();
                               if(editarEmail.equals("s") || editarEmail.equals("S")) {
                            	   boolean validaEmail = false;
                                   String email = "";
                                   
                                   while (!validaEmail) {
                                   	System.out.println("Novo email: {Formato: exemplo@exemplo.com ou exemplo@exemplo.com.xyz}");
                                       email = scanner.next();
                                       
                                       if(Aluno.validaEmail(email)) validaEmail=true;
                                   }
                            	   aluno.setEmail(email);
                               }

                               alunoDao.update(aluno);//Fazendo o updtade(metodo do AlunoDao) no objeto aluno que recebe como parametro o objeto novoAluno
                               
                           } catch (Exception e){
                               System.out.println("Erro ao editar os alunos: " + e.getMessage());
                           }
                            break;


                        case 3:
                            System.out.println("Excluir Aluno:");
                            try{
                            	if(listarAlunos(alunoDao) == 0) {
                            		break;
                            	};
                                
                            	System.out.println("ATENÇÂO: Os treinos do aluno também serão excluidos!\n");
                                System.out.println("Informe o Id do aluno a ser excluido");
                                int id = scanner.nextInt(); //pegando o ID do aluno a ser excluido
                                treinoDao.deleteByIdAluno(id);
                                alunoDao.deleteById(id);//Aplica o metodo deleteById da classe AlunoDao no objeto alunoParaexcluir

                            }catch (Exception e) {
                                System.out.println("Erro ao excluir aluno" + e.getMessage());
                            }
                            break;

                        case 4:
                            System.out.println("Cadastrando um Novo aluno....");
                            try{
                                //Pegando os dados do novo aluno
                                System.out.println("Cadastrar novo Aluno:");
                                System.out.println("Nome do Aluno");
                                String nome = scanner.next();
                                scanner.nextLine(); //descarte de nova linha
                                
                                boolean validaCpf = false;
                                String cpf ="";
                                
                                while(!validaCpf) {
                                	System.out.println("CPF do Aluno: {Formato: 000.000.000-00}");
                                    cpf = scanner.next();
                                    
                                    if(Aluno.validaCPF(cpf, alunoDao)) validaCpf = true;
                                }
                                
                                
                                boolean validaData = false;
                                String dataNasc = "";
                                
                                while (!validaData) {
                                	System.out.println("Data de nascimento do Aluno: {Formato: DIA/MÊS/ANO}");
                                    dataNasc = scanner.next();
                                    scanner.nextLine();
                                    
                                    if(Aluno.validaData(dataNasc)) validaData = true;
                                }

                                boolean validaTelefone = false;
                                String telefone = "";
                                
                                while (!validaTelefone) {
                                	System.out.println("Telefone do Aluno: {Formato: (47) 90000-0000}");
                                    telefone = scanner.nextLine().trim();
                                    
                                    if(Aluno.validaTelefone(telefone)) validaTelefone = true;
                                }
                                
                                boolean validaEmail = false;
                                String email = "";
                                
                                while (!validaEmail) {
                                	System.out.println("Email do Aluno: {Formato: exemplo@exemplo.com ou exemplo@exemplo.com.xyz}");
                                    email = scanner.next();
                                    
                                    if(Aluno.validaEmail(email)) validaEmail=true;
                                }
                                
                                Aluno novoAluno = new Aluno(nome,cpf,Aluno.formataData(dataNasc),telefone,email);
                                alunoDao.insert(novoAluno);
                                break;
                            } catch (Exception e){
                                System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
                            }
                            
                        case 5:
                        	break;

                        case 0:
                            System.out.println("Saindo...");
                    }
                    break;
                case 2:
                    System.out.println("Opções de 'Treinos' ");
                    System.out.println("1 - Cadastrar um novo treino");
                    System.out.println("2 - Listar treinos de um aluno");
                    System.out.println("5 - Voltar para o menu principal");
                    System.out.println("0 - Sair do sistema ");
                    resposta = scanner.nextInt();
                    switch(resposta){
                        case 1:
                            System.out.println("Cadastrando novo treino");
                            try {
                                // Solicita o ID do aluno
                                System.out.print("Digite o ID do aluno já cadastrado: ");
                                int idAluno = scanner.nextInt();

                                // Busca o aluno no banco de dados
                                Aluno aluno = alunoDao.findById(idAluno);

                                if (aluno == null) {
                                    System.out.println("Aluno com ID " + idAluno + " não encontrado.");
                                    return;
                                }

                                // Solicita dados do treino
                                System.out.print("Tipo de treino: ");
                                String tipo = scanner.next();

                                System.out.print("Descrição do treino: ");
                                String descricao = scanner.next();

                                System.out.print("Duração (minutos): ");
                                int duracao = scanner.nextInt();

                                System.out.print("Data de início (yyyy-mm-dd): ");
                                String dataInicio = scanner.next();

                                // Cria objeto treino
                                Treino treino = new Treino(aluno, tipo, descricao, duracao, dataInicio);//Objeto treino da classe Treino
                                // Cadastra o treino no banco
                                treinoDao.insert(treino);//aplica o método insert no objeto novotreino passando como parametro o objeto treino
                                System.out.println("Treino cadastrado com sucesso para o aluno: " + aluno.getNomeAluno());

                            } catch (Exception e) {
                                System.out.println(" Erro ao cadastrar treino: " + e.getMessage());
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Listando treino");

                            try {
                                // Solicita o ID do aluno já cadastrado
                                System.out.print("Digite o ID do aluno: ");
                                int idAluno = scanner.nextInt();

                                // Busca o aluno no banco de dados
                                Aluno aluno = alunoDao.findById(idAluno);

                                if (aluno == null) {
                                    System.out.println("Aluno com ID " + idAluno + " não encontrado.");
                                    return;
                                }

                                // Cria objeto treino apenas para acessar o método de busca
                                Treino treinoBusca = new Treino(aluno, "", "", 0, "");
                                // Busca os treinos do aluno
                                List<Treino> treinos = treinoDao.findAll();
                                
                                //Chance alta de erro abaixo =  fiquei confuso com os objetos    
                                if (treinos == null || treinos.isEmpty()) {
                                    System.out.println("Nenhum treino encontrado para o aluno: " + aluno.getNomeAluno());
                                    return;
                                }

                                // Exibe os treinos
                                System.out.println("\nTreinos de " + aluno.getNomeAluno() + ":\n");
                                for (Treino treinofinal : treinos) {
                                    treinofinal.exibirInfoTreino();
                                    System.out.println("--------------------------");
                                }

                            } catch (Exception e) {
                                System.out.println(" Erro ao exibir treinos: " + e.getMessage());
                                e.printStackTrace();
                            }
                            break;
                        case 5:
                        	break;
		                case 0:
		                    System.out.println("Saindo...");
							try {
								alunoDao.closeConnection();
								treinoDao.closeConnection();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                    scanner.close();
		                default:
		                    System.out.println("opção inválida");
		                    }


            }
        }
	}
    
	    public static int listarAlunos(AlunoDao alunoDao) {
	    	try {
	            List<Aluno> lista = alunoDao.findAll();
	            
	            // Verifica se a lista está vazia
	            if (lista == null || lista.isEmpty()) {
	                System.out.println("Nenhum aluno encontrado.");
	                return 0;
	            } else {
	                System.out.println("Lista de alunos:");
	                for (Aluno a : lista) {
	                    System.out.println("ID: " + a.getIdAluno());
	                    System.out.println("Nome: " + a.getNomeAluno());
	                    System.out.println("CPF: " + a.getCpf());
	                    System.out.println("Data de Nascimento: " + a.getDataNasc());
	                    System.out.println("Telefone: " + a.getTelefone());
	                    System.out.println("Email: " + a.getEmail());
	                    System.out.println("------------------------");
	                }
	                return 1;
	            }
	
	        } catch (Exception e) {
	            System.out.println("Erro ao listar alunos: ");
	            e.printStackTrace();
	            return -1;
	        }
	    }
    }