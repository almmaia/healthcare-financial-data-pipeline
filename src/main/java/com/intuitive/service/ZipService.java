package com.intuitive.service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

    public class ZipService {

        public void extrairArquivoZip(String arquivoZip, String pastaDestino) {
            try {
                // Cria a pasta de destino se ela não existir
                File diretorio = new File(pastaDestino);
                if (!diretorio.exists()) diretorio.mkdirs();

                ZipInputStream zis = new ZipInputStream(new FileInputStream(arquivoZip));
                ZipEntry entrada = zis.getNextEntry();

                while (entrada != null) {
                    String caminhoArquivo = pastaDestino + File.separator + entrada.getName();

                    if (!entrada.isDirectory()) {
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(caminhoArquivo));
                        byte[] bytesIn = new byte[4096];
                        int read = 0;
                        while ((read = zis.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                        bos.close();
                    }
                    zis.closeEntry();
                    entrada = zis.getNextEntry();
                }
                zis.close();
                System.out.println("Arquivo extraído com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao extrair ZIP: " + e.getMessage());
            }
        }
    }

