package hr.tvz.financije.services;

import hr.tvz.financije.repositories.CurrencyRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;
import hr.tvz.financije.services.models.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository repository;

    public List<CurrencyDto> getCurrencies() {
        return repository.getCurrencies().stream().map(this::mapToCurrencyDto).toList();
    }

    private CurrencyDto mapToCurrencyDto(CurrencyRecord record) {
        return new CurrencyDto(record.getId(),
                record.getName(),
                record.getCode(),
                record.getSymbol(),
                record.getExchangeRate(),
                record.getLastUpdateDate());
    }
}
