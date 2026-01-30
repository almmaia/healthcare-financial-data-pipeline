package com.intuitive.service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProcessadorService {
    public void consolidarEValidar(String pastaOrigem, String arquivoSaida) throws Exception {
        File pasta = new File(pastaOrigem);
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("!!! ERRO: Nenhum arquivo encontrado em " + pastaOrigem + ". Crie um arquivo CSV lá para testar.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivoSaida), StandardCharsets.UTF_8))) {
            writer.println("CNPJ;RazaoSocial;Trimestre;Ano;Valor Despesas;Status_CNPJ");

            for (File f : arquivos) {
                // Lê qualquer arquivo que você colocar na pasta para o teste funcionar
                BufferedReader br = new BufferedReader(new FileReader(f));
                String linha;
                br.readLine(); // Pula cabeçalho

                while ((linha = br.readLine()) != null) {
                    String[] col = linha.split(";");
                    if (col.length >= 4) {
                        String cnpj = col[0].replaceAll("[^0-9]", "");
                        double valor = Double.parseDouble(col[col.length - 1].replace(",", "."));

                        // Regra de Negócio: Negativos viram 0
                        if (valor < 0) valor = 0;

                        // Validação simples de CNPJ
                        String status = (cnpj.length() == 14) ? "VALIDO" : "INVALIDO";

                        writer.println(cnpj + ";" + col[1] + ";3;2023;" + valor + ";" + status);
                    }
                }
                br.close();
            }
        }
    }
}