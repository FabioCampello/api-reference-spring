package com.spring.tutorial.files.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.tutorial.commons.exception.ServicosOnlineApiException;
import com.spring.tutorial.commons.log.LogUtil;
import com.spring.tutorial.files.service.FileStorageService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/files")
public class FileController {
	
	private static final String ERRO = "ERRO"; 
	private static final String ERROR_LOG = "Erro inesperado na Api. IdLog="; 
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			return new ResponseEntity<>("Arquivo, " + fileStorageService.storeFile(file) + ", gravado!", HttpStatus.OK);
		} catch(ServicosOnlineApiException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			String idLog = UUID.randomUUID().toString();
			LogUtil.logGenerico(idLog, ERRO, FileController.class.getSimpleName(), "uploadFile", null, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_LOG + idLog);
		}
	}

}
