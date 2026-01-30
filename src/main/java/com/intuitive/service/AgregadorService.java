package com.intuitive.service;

import java.io.*;
import java.util.*;

public class AgregadorService {
    // Agrupa dados por Razão Social e UF para calcular totais e médias [cite: 80, 81, 83]
    public void gerarAgregado(String caminhoConsolidado, String caminhoSaida) throws Exception {
        Map<String, List<Double>> dadosAgrupados = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoConsolidado))) {
            br.readLine(); // Pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] col = linha.split(";");
                // Chave: RazaoSocial + UF [cite: 80]
                String chave = col[1] + ";" + "RJ"; // UF fixo como exemplo ou vindo do join [cite: 72]
                double valor = Double.parseDouble(col[4]);

                dadosAgrupados.computeIfAbsent(chave, k -> new ArrayList<>()).add(valor);
            }
        }

        try (PrintWriter writer = new PrintWriter(new File(caminhoSaida))) {
            writer.println("RazaoSocial;UF;Total;Media;DesvioPadrao");
            for (String chave : dadosAgrupados.keySet()) {
                List<Double> valores = dadosAgrupados.get(chave);
                double total = valores.stream().mapToDouble(d -> d).sum();
                double media = total / valores.size();
                // Cálculo de Desvio Padrão solicitado [cite: 84]
                double somaQuadrados = valores.stream().mapToDouble(v -> Math.pow(v - media, 2)).sum();
                double desvio = Math.sqrt(somaQuadrados / valores.size());

                writer.println(chave + ";" + total + ";" + media + ";" + desvio);
            }
        }
    }
}