package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GoldPriceClient {

    public static class GoldPrice {
        public String currency;
        public double price_gram_24k;
        public double price_gram_22k; // 👈 aggiunto questo campo
    }

    public GoldPrice getGoldPrice(String currency) throws Exception {
        String urlStr = "https://data-asg.goldprice.org/dbXRates/" + currency.toUpperCase();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlStr))
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Errore HTTP: " + response.statusCode());
        }

        String json = response.body();

        GoldPrice result = new GoldPrice();
        result.currency = currency.toUpperCase();
        double pricePerOunce = parsePrice(json, "xauPrice");
        result.price_gram_24k = pricePerOunce / 31.1035;

        return result;
    }

    private double parsePrice(String json, String key) {
        String search = "\"" + key + "\":";
        int start = json.indexOf(search);
        if (start == -1) throw new RuntimeException("Chiave '" + key + "' non trovata nella risposta JSON");
        start += search.length();
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return Double.parseDouble(json.substring(start, end).trim());
    }

};
