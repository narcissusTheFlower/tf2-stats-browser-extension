package org.narcissus.tf2statsbrowserextension.logstf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogsTFAPI {

//    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    ObjectMapper objectMapper = new ObjectMapper();
    private static StringBuilder search_api = new StringBuilder("https://logs.tf/api/v1/log?player=");

    private static StringBuilder data_api = new StringBuilder("https://logs.tf/json/");
    //"https://logs.tf/json/logid"
    private final Integer steamId32Bit;
    private final Long steamId64Bit;
    private final long UNIQUE_OBJECT_ID = System.currentTimeMillis();

    //76561198106563151 =>
    // 1000100000000000000000001                               1 =>
    // 0000100010111000010100100100111 =>
    // 73148711 (steamId32Bit)

    private LogsTFAPI(long steamId64Bit) {
        this.steamId64Bit = steamId64Bit;
        String steamIdBinary = Long.toBinaryString(steamId64Bit);
        this.steamId32Bit = Integer.parseInt(steamIdBinary.substring(25, steamIdBinary.length() - 1), 2);
    }

    public static LogsTFAPI ofPlayer(long steamId64Bit) {
        return new LogsTFAPI(steamId64Bit);
    }

    public String getLogs(int logsLimit) {
        search_api.append(steamId64Bit).append("&limit=").append(logsLimit);
        //"https://logs.tf/api/v1/log?player=76561198106563151&limit=2000"
        Request request = new Request.Builder().url(search_api.toString()).build();
        String result = null;
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            result = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            List<String> test = new ArrayList<>();
            jsonNode.get("logs").elements().forEachRemaining(element -> test.add(element.elements().next().asText()));

           objectMapper.readTree(result).path("parameters").asText();
            //jsonNode.get("parameters").toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public int hashCode() {
        int result = (steamId32Bit ^ (steamId32Bit >>> 16));
        result = 31 * result + steamId32Bit.hashCode();
        result = 31 * result + steamId64Bit.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        return this.UNIQUE_OBJECT_ID == ((LogsTFAPI) obj).UNIQUE_OBJECT_ID;
    }
}
