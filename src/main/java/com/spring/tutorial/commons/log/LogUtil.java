package com.spring.tutorial.commons.log;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	private static final String LINHA_HORIZONTAL = "====================================================";
	private static final String ID_LOG = "ID-LOG: ";
	private static final String METODO = "METODO: ";
	private static final String MENSAGEM = "MENSAGEM: ";
	private static final String CLASSE = "CLASSE: ";
	private static final String TIPO = "TIPO: ";

	public static void logGenerico(String id, String tipo, String classe, String metodo, Map<String, String> parametros, String mensagem) {
		if (tipo.equalsIgnoreCase("INFO")) {
			logger.info(LINHA_HORIZONTAL);
			logger.info(ID_LOG + id);
			logger.info(METODO + metodo);
			logger.info(MENSAGEM + mensagem);
			for (Entry<String, String> parametro : parametros.entrySet()) {
				logger.info(parametro.getKey().toUpperCase() + ": " + parametro.getValue().toUpperCase());
			}
			logger.info(LINHA_HORIZONTAL);
		} else if (tipo.equalsIgnoreCase("ERRO")) {
			logger.error(LINHA_HORIZONTAL);
			logger.error(ID_LOG + id);
			logger.error(TIPO + tipo);
			logger.error(CLASSE + classe);
			logger.error(METODO + metodo);
			for (Entry<String, String> parametro : parametros.entrySet()) {
				logger.error(parametro.getKey().toUpperCase() + ": " + parametro.getValue().toUpperCase());
			}
			logger.error("MENSAGEM: " + mensagem);
			logger.error(LINHA_HORIZONTAL);
		}
	}

}
