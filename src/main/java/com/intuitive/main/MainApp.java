package com.intuitive.main;

import com.intuitive.service.*;
import java.io.File;

public class MainApp {
    public static void main(String[] args) {
        DownloaderService downloader = new DownloaderService();
        ProcessadorService processador = new ProcessadorService();

        String pastaBase = "arquivos_ans";
        String pastaExtraida = pastaBase + "/extraido";
        String arquivoConsolidado = pastaBase + "/consolidado.csv";

        // Cria as pastas necessárias automaticamente
        new File(pastaExtraida).mkdirs();

        try {
            System.out.println("--- INICIANDO TESTE INTUITIVE CARE ---");

            // 1. Tenta baixar
            downloader.baixarArquivo("http://dadosabertos.ans.gov.br/FTP/PDA/demonstracoes_contabeis/2023/3T/202303.zip", pastaBase + "/dados.zip");

            // 2. Processa (Questão 1.3 e 2.1)
            System.out.println("Processando dados...");
            processador.consolidarEValidar(pastaExtraida, arquivoConsolidado);

            System.out.println("--- PROCESSO CONCLUÍDO ---");
            System.out.println("Verifique o resultado em: " + arquivoConsolidado);

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}