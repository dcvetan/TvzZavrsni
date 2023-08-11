package hr.tvz.financije.jobs;

import hr.tvz.financije.services.CurrencyService;
import hr.tvz.financije.services.ExchangeRatesService;
import hr.tvz.financije.services.models.CurrencyUpdateEntity;
import hr.tvz.financije.services.models.responses.ExchangeRatesResponse;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class ExchangeRatesUpdateJob implements Job {

    private final ExchangeRatesService exchangeRatesService;
    private final CurrencyService currencyService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int maxRetries = 3;
        int retryDelayMillis = 10000;

        for (int retryCount = 1; retryCount <= maxRetries; retryCount++) {
            try {
                ExchangeRatesResponse exchangeRatesResponse = exchangeRatesService.getLatestExchangeRates();
                currencyService.saveCurrencies(mapToCurrencyUpdateEntities(exchangeRatesResponse));
                return;
            } catch (Exception e) {
                System.err.println("Retry attempt " + retryCount + " failed: " + e.getMessage());

                if (retryCount < maxRetries) {
                    System.out.println("Retrying in " + retryDelayMillis + " milliseconds...");
                    try {
                        Thread.sleep(retryDelayMillis);
                    } catch (InterruptedException interruptedException) {
                        System.err.println("Job execution interrupted with\n" + interruptedException.getMessage());
                    }
                }
            }
        }

        throw new JobExecutionException("Job execution failed after " + maxRetries + " retries.");
    }

    private List<CurrencyUpdateEntity> mapToCurrencyUpdateEntities(ExchangeRatesResponse exchangeRatesResponse) {
        List<CurrencyUpdateEntity> currencyUpdateEntities = new ArrayList<>();

        LocalDateTime fetchTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(exchangeRatesResponse.timestamp()),
                TimeZone.getDefault().toZoneId());

        for (Map.Entry<String, Double> rate : exchangeRatesResponse.rates().entrySet()) {
            currencyUpdateEntities.add(new CurrencyUpdateEntity(
                    rate.getKey(),
                    BigDecimal.valueOf(rate.getValue())
                            .setScale(6, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(1000000))
                            .longValueExact(),
                    fetchTime
            ));
        }

        return currencyUpdateEntities;
    }
}