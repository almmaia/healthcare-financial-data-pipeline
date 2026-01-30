package com.intuitive.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloaderService {
    public void baixarArquivo(String enderecoWeb, String nomeLocal) {
        try {
            URL url = new URL(enderecoWeb);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, Paths.get(nomeLocal), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("-> Download finalizado com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("-> Aviso: Site da ANS offline. O sistema tentará usar arquivos locais na pasta 'extraido'.");
        }
    }
}