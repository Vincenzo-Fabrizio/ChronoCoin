package service;

import model.Coin;
import model.NumismaticRarity;
import model.OptionConservation;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class CoinApiClient {

    private static final String BASE_URL = "http://localhost:8080/coin";

    // ✅ Inserimento nuova moneta (POST)
    public Coin createCoin(Coin coin) throws IOException {
        HttpURLConnection conn = createConnection("/create", "POST");

        String json = coinToJson(coin);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        return parseCoin(readResponse(conn));
    }

    // ✅ Ottieni tutte le monete (GET)
    public List<Coin> getAllCoins() throws IOException {
        HttpURLConnection conn = createConnection("", "GET");
        String response = readResponse(conn);
        return parseCoinList(response);
    }

    // ✅ Ottieni moneta per ID (GET)
    public Coin getCoinById(String id) throws IOException {
        HttpURLConnection conn = createConnection("/read/" + id, "GET");
        return parseCoin(readResponse(conn));
    }

    // ✅ Elimina moneta (DELETE)
    public Coin deleteCoin(String id) throws IOException {
        HttpURLConnection conn = createConnection("/delete/" + id, "DELETE");
        return parseCoin(readResponse(conn));
    }

    // 🔧 Crea connessione
    private HttpURLConnection createConnection(String path, String method) throws IOException {
        URL url = URI.create(BASE_URL + path).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        if (method.equals("POST")) {
            conn.setDoOutput(true);
        }
        return conn;
    }

    // 🔧 Leggi la risposta
    private String readResponse(HttpURLConnection conn) throws IOException {
        if (conn.getResponseCode() >= 400) {
            throw new IOException("Errore API: " + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            builder.append(line);
        }

        in.close();
        return builder.toString();
    }

    // ✅ Parsing JSON semplice → Coin
    private Coin parseCoin(String json) {
        Coin coin = new Coin();
        coin.setID(extract(json, "id"));
        coin.setName(extract(json, "name"));
        coin.setMaterial(extract(json, "material"));
        coin.setNote(extract(json, "note"));

        coin.setYear(extractInt(json, "year"));
        coin.setPrice(extractDouble(json, "price"));
        coin.setWeight(extractDouble(json, "weight"));
        coin.setDiameter(extractDouble(json, "diameter"));
        coin.setHeight(extractDouble(json, "height"));

        String obv = extract(json, "conservationObverse");
        if (obv != null) {
            coin.setConservationObverse(OptionConservation.valueOf(obv));
        }

        String rev = extract(json, "conservationReverse");
        if (rev != null) {
            coin.setConservationReverse(OptionConservation.valueOf(rev));
        }

        String rarity = extract(json, "degree");
        if (rarity != null) {
            coin.setDegree(NumismaticRarity.valueOf(rarity));
        }

        coin.setPhotoPathObverse(extract(json, "photoPathObverse"));
        coin.setPhotoPathReverse(extract(json, "photoPathReverse"));

        return coin;
    }

    // ✅ Parsing JSON → lista monete (molto basic)
    private List<Coin> parseCoinList(String json) {
        List<Coin> coins = new ArrayList<>();
        if (!json.startsWith("[")) return coins;

        String[] parts = json.split("\\},\\{"); // Split per oggetto
        for (String part : parts) {
            String coinJson = part;

            if (!coinJson.startsWith("{")) coinJson = "{" + coinJson;
            if (!coinJson.endsWith("}")) coinJson = coinJson + "}";

            coins.add(parseCoin(coinJson));
        }

        return coins;
    }

    // 🔎 Estrazione campi
    private String extract(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return null;
        start += search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    private double extractDouble(String json, String key) {
        String search = "\"" + key + "\":";
        int start = json.indexOf(search);
        if (start == -1) return 0;
        start += search.length();
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return Double.parseDouble(json.substring(start, end));
    }

    private int extractInt(String json, String key) {
        return (int) extractDouble(json, key);
    }

    private String coinToJson(Coin coin) {
        return "{"
                + "\"name\":\"" + coin.getName() + "\","
                + "\"year\":" + coin.getYear() + ","
                + "\"material\":\"" + coin.getMaterial() + "\","
                + "\"weight\":" + coin.getWeight() + ","
                + "\"diameter\":" + coin.getDiameter() + ","
                + "\"height\":" + coin.getHeight() + ","
                + "\"price\":" + coin.getPrice() + ","
                + "\"conservationObverse\":\"" + coin.getConservationObverse() + "\","
                + "\"conservationReverse\":\"" + coin.getConservationReverse() + "\","
                + "\"degree\":\"" + coin.getDegree() + "\","
                + "\"note\":\"" + coin.getNote() + "\","
                + "\"photoPathObverse\":\"" + coin.getPhotoPathObverse() + "\","
                + "\"photoPathReverse\":\"" + coin.getPhotoPathReverse() + "\""
                + "}";
    }
    
};