package br.com.resGuincho.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.resGuincho.model.Pessoa;
import br.com.resGuincho.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepo;

	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@GetMapping("/pessoa/cadastro")
	public String cadastrar(Model model){
		Pessoa pessoa = new Pessoa();
		model.addAttribute(pessoa);
		return "pessoa/cadastro";
	}
	
	@PostMapping("/pessoa/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa){
		pessoaRepo.save(pessoa);	
		return "index";
	}
	
	@GetMapping("/pessoa/listar")
	public ModelAndView listarPessoas(Model model) {
		ModelAndView mv = new ModelAndView("pessoa/lista");
		mv.addObject("listaDePessoas", pessoaRepo.findAll());
		return mv;
	}
	
	@GetMapping("/pessoa/editar/{id}")
	public ModelAndView editarPessoa(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);
		ModelAndView mv = new ModelAndView("pessoa/editar");
		mv.addObject("pessoa", pessoa);
		return mv;
	}
	
	@PostMapping("/pessoa/editar")
	public String atualizarPessoa(@ModelAttribute ("pessoa") Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return "redirect:/pessoa/listar";
	}
	
	@GetMapping("pessoa/remove/{id}")
	public String removerPessoa(@PathVariable Long id) {
		pessoaRepo.deleteById(id);
		return "redirect:/pessoa/listar";
	}
}
