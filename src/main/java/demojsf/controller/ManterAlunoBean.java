package demojsf.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import demojsf.model.Aluno;
import demojsf.service.AlunoService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;



@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class ManterAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Aluno> alunos = new ArrayList<>();
	private Aluno aluno = new Aluno();

	@Inject
	private AlunoService alunoService;
	
	@PostConstruct
	public void inicializar() {
		log.info("ManterAluno inicializar()");
		this.alunos = alunoService.buscarTodos();
	}

	public void salvar() {
		log.info("salvando Aluno...");

		try {
			alunoService.salvar(this.aluno);
        } catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public void excluir() {
		log.info("excluindo Aluno...");  		
	}
	
	public void atualizar() {
		log.info("atualizando Aluno...");  		
	}	
		
	public void limpar() {
		log.info("limpar");
		this.aluno = new Aluno();
	}	
	
}
