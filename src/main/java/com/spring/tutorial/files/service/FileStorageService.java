package com.spring.tutorial.files.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.tutorial.commons.exception.ServicosOnlineApiException;
import com.spring.tutorial.files.config.FileStorageProperties;

@Service
public class FileStorageService {

	private Path fileStorageLocation;
	
	@Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new ServicosOnlineApiException("Não foi possível criar o diretório em que os arquivos enviados serão armazenados.", ex);
        }
    }

	public String storeFile(MultipartFile file) {
		
		if(file.isEmpty()) {
			throw new ServicosOnlineApiException("Selecione um arquivo.");
		} 
		
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Verifique se o nome do arquivo contém caracteres inválidos
			if (fileName.contains("..")) {
				throw new ServicosOnlineApiException("Desculpe! Nome do arquivo contém sequência de caminho inválida: " + fileName);
			}

			// Copiar arquivo para o local de destino (Substituindo arquivo existente com o mesmo nome)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException e) {
			throw new ServicosOnlineApiException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", e);
		}
	}

}
