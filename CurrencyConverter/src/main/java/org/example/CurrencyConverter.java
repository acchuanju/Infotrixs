package org.example;

import netscape.javascript.JSObject;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type Currency to Convert From: ");
        String concertFrom = scanner.nextLine();
        System.out.println("Type Currency to Convert To: ");
        String concertTo = scanner.nextLine();
        System.out.println("Type Quantity to Convert:");
        BigDecimal quantity = scanner.nextBigDecimal();

        String urlString = "https://api.exchangerate.host/latest?base=" + concertFrom.toUpperCase();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObject = jsonObject.getJSONObject("rates");
        BigDecimal rate = ratesObject.getBigDecimal(concertTo.toUpperCase());

        BigDecimal result = rate.multiply(quantity);
        System.out.println(result);
    }

}
