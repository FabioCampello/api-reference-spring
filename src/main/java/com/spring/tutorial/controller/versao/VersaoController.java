package com.spring.tutorial.controller.versao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/versao")
public class VersaoController {
	
	@Value("${application.versionAPI}")
	private String versao;
	
	@Value("${paginacao}")
	private int paginacao;
	
	@GetMapping("")
	public ResponseEntity<String> recuperaVersao() {
		return ResponseEntity.ok().body("Version API: " + versao);
	}
	
	@GetMapping("/recuperaPaginacao")
	public ResponseEntity<String> recuperaPaginacao() {
		return ResponseEntity.ok().body("Paginação: " + paginacao);
	} 

}
