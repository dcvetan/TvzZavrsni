package hr.tvz.financije.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.financije.services.models.responses.ExchangeRatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private final String BASE_URL = "http://api.exchangeratesapi.io/v1/";
    @Value("${exchange-rates.api-key}")
    private String exchangeRatesApiKey;

    private final ObjectMapper objectMapper;

    public ExchangeRatesResponse getLatestExchangeRates() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/latest?access_key=" + exchangeRatesApiKey))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != HttpStatus.OK.value()) {
            throw new RuntimeException("ExchangeRates API call failed");
        }

        return objectMapper.readValue(response.body(), ExchangeRatesResponse.class);
    }
}
