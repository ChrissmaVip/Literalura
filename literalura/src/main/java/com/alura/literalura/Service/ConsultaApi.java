package com.alura.literalura.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {


    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private String titulo;


    public ConsultaApi(String titulo) {
        this.titulo = titulo.replace(" ", "%20");

    }

    public String consulta() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE + titulo))
                .build();

        HttpResponse<String> response;

        {
            try {
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        String consulta = response.body();

        return consulta;

    }
}


