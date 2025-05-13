package com.stockmanager.stockmanager.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LatexInvoiceService {

    private static final String TEMPLATE_PATH = "C:\\Users\\midou\\OneDrive\\Bureau\\stockmanager\\src\\main\\resources\\templates\\invoice_template.tex";

    public File generateLatexFile(String billId, Map<String, String> placeholders) throws IOException {
        String content = Files.readString(Paths.get(TEMPLATE_PATH));

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }

        Path latexFile = Paths.get("invoices", billId + ".tex");
        Files.createDirectories(latexFile.getParent());
        Files.writeString(latexFile, content);
        return latexFile.toFile();
    }

    public File compileToPdf(File texFile) throws IOException, InterruptedException {
        // Lancer la commande dans une nouvelle fenêtre de terminal
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "pdflatex -output-directory=invoices " + texFile.getAbsolutePath() + " &&  exit");
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Lire la sortie du processus en temps réel
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Affiche la sortie dans la console
            }
        }



        ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "pdflatex -output-directory=invoices " + texFile.getAbsolutePath() + " &&  exit");
        pb1.redirectErrorStream(true);
        Process process1 = pb1.start();

        // Lire la sortie du processus en temps réel
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process1.getInputStream()))) {
            String line1;
            while ((line1 = reader.readLine()) != null) {
                System.out.println(line1); // Affiche la sortie dans la console
            }
        }


        String pdfPath = texFile.getAbsolutePath().replace(".tex", ".pdf");
        return new File(pdfPath);
    }
}
