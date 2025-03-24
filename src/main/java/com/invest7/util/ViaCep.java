package com.invest7.util;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.invest7.model.Endereco;

public class ViaCep {
    public static Endereco buscarCep(String cep) throws Exception {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        // Criando o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Criando a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Enviando a requisição e capturando a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Convertendo JSON para objeto Java
        return new Gson().fromJson(response.body(), Endereco.class);
    }
}
