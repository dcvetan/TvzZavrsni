package hr.tvz.financije.services;

import hr.tvz.financije.repositories.CurrencyRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;
import hr.tvz.financije.services.models.CurrencyDto;
import hr.tvz.financije.services.models.CurrencyUpdateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository repository;

    public List<CurrencyDto> getCurrencies() {
        return repository.getCurrencies().stream().map(this::mapToCurrencyDto).toList();
    }

    public void saveCurrencies(List<CurrencyUpdateEntity> currencyUpdateEntities) {
        repository.saveCurrencies(currencyUpdateEntities);
    }

    private CurrencyDto mapToCurrencyDto(CurrencyRecord record) {
        return new CurrencyDto(record.getId(),
                record.getName(),
                record.getCode(),
                record.getSymbol(),
                BigDecimal.valueOf(record.getExchangeRate())
                        .divide(BigDecimal.valueOf(1000000), RoundingMode.HALF_UP)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue(),
                record.getLastUpdateDate());
    }
}
