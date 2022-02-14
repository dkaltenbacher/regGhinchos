package br.com.resGuincho.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import br.com.resGuincho.model.Pessoa;

public interface PessoaRepository extends JpaRepositoryImplementation<Pessoa, Long>{

}
