package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadPDFFromDynamicURL {
    public static void main(String[] args) {
        // URL da página que contém os links
        for(int i=0;i<=10;i++) {
            downloadAll(i++);
        }
    }

    private static void downloadAll(int page) {
        String pageURL = "https://www.teses.usp.br/index.php?option=com_jumi&fileid=19&Itemid=87&lang=pt-br&g=1&b6=2024&c6=a&o6=AND&b7=computa%C3%A7%C3%A3o&c7=c&o7=AND&pagina="+page;
        try {
            // Carregar o conteúdo da página
            Document doc = Jsoup.connect(pageURL).get();

            // Procurar links para arquivos PDF na página
            for (Element link : doc.select("a[href]")) {
                String href = link.attr("abs:href");
                String id = link.parent().attributes().get("class");
                if (id.equals("dadosDocNome")) {
                    System.out.println("Encontrado link para o PDF: " + href);

                    // Carregar o conteúdo da página
                    Document doc2 = Jsoup.connect(href).get();
                    // Procurar links para arquivos PDF na página
                    for (Element link2 : doc2.select("a[href]")) {
                        String href2 = link2.attr("abs:href");
                        if (href2.endsWith(".pdf")) {
                            System.out.println("Encontrado PDF: " + href);
                            // Fazer download do PDF
                            String saveDir = "/media/joao9aulo/dados/Dropbox/teses"; // Mude para o caminho desejado
                            downloadFile(href2, saveDir);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        int responseCode = httpConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            String saveFilePath = saveDir + File.separator + fileName;

            try (InputStream inputStream = httpConnection.getInputStream(); FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("Arquivo salvo em: " + saveFilePath);
            }
        } else {
            System.out.println("Falha ao baixar o arquivo. Código HTTP: " + responseCode);
        }
        httpConnection.disconnect();
    }
}
