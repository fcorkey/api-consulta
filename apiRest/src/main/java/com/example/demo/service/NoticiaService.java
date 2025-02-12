package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.demo.model.Noticia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class NoticiaService {

    /**
     * @param query
     * @return
     * @throws IOException
     */
    public List<String> buscarNoticias(String query) throws IOException {      
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<String> noticiasJson = new ArrayList<>();
        String url = "https://www.ultimahora.com/buscador?q=" + query;
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc.toString());
        Elements elementos = doc.select("li.SearchResultsModule-results-item"); // Selector depende de la estructura HTML de la página
        

        for (Element elemento : elementos) {
            Noticia noticia = new Noticia();             
            String fechaTexto = elemento.select(".PagePromo-date").text().trim();
            String fechaISO = convertirFecha(fechaTexto);
            
            noticia.setFecha(fechaISO); 
            noticia.setEnlace(elemento.select("a").attr("href"));
            noticia.setEnlaceFoto(elemento.select("img").attr("src"));
            noticia.setTitulo(elemento.select(".PagePromo-title a").text() );
            noticia.setResumen(elemento.select(".PagePromo-description").text() );

             // Convertir la noticia a JSON
            try {
                String jsonNoticia = gson.toJson(noticia);
                noticiasJson.add(jsonNoticia);
            } catch (Exception e) {
                System.err.println("Error al convertir Noticia a JSON: " + e.getMessage());
            }
                  
        }
        System.out.println(noticiasJson.toString());
        return noticiasJson;
    }

    private static final Map<String, String> MESES = new HashMap<>();

    static {
        MESES.put("Enero", "January");
        MESES.put("Febrero", "February");
        MESES.put("Marzo", "March");
        MESES.put("Abril", "April");
        MESES.put("Mayo", "May");
        MESES.put("Junio", "June");
        MESES.put("Julio", "July");
        MESES.put("Agosto", "August");
        MESES.put("Septiembre", "September");
        MESES.put("Octubre", "October");
        MESES.put("Noviembre", "November");
        MESES.put("Diciembre", "December");
    }

    // Método para convertir la fecha a formato ISO-8601
    
    private static String convertirFecha(String fechaTexto) {
        try {
            // Reemplazar el mes en español por inglés
            for (Map.Entry<String, String> entry : MESES.entrySet()) {
                if (fechaTexto.contains(entry.getKey())) {
                    fechaTexto = fechaTexto.replace(entry.getKey(), entry.getValue());
                    break;
                }
            }

            // Reemplazar "p. m." y "a. m." por "PM" y "AM"
            fechaTexto = fechaTexto.replace(" p. m.", " PM").replace(" a. m.", " AM");

            // Formato esperado después de la traducción
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a", Locale.ENGLISH);

            // Parsear la fecha
            LocalDateTime fecha = LocalDateTime.parse(fechaTexto, formatter);

            // Formatear en ISO-8601
            return fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return "Error al convertir la fecha: " + e.getMessage();
        }
    }
}
