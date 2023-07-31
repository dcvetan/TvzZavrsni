package hr.tvz.financije.services;

import hr.tvz.financije.repositories.CurrencyRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;
import hr.tvz.financije.services.models.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository repository;

    public List<Currency> getCurrencies() {
        return repository.getCurrencies().stream().map(this::mapToCurrency).toList();
    }

    private Currency mapToCurrency(CurrencyRecord record) {
        return new Currency(record.getId(),
                record.getName(),
                record.getCode(),
                record.getSymbol(),
                record.getExchangeRate(),
                record.getLastUpdateDate());
    }
}
