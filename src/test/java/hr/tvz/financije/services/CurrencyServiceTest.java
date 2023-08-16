package hr.tvz.financije.services;

import hr.tvz.financije.repositories.CurrencyRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;
import hr.tvz.financije.services.models.CurrencyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    private final List<CurrencyRecord> CURRENCY_RECORDS = List.of(
            new CurrencyRecord(1, "name", "eur", "e", 1000000L, LocalDateTime.of(2023, 12, 12, 1, 1)),
            new CurrencyRecord(2, "name2", "usd", "d", 2000000L, LocalDateTime.of(2023, 12, 12, 1, 1))
    );

    private final List<CurrencyDto> CURRENCY_DTOS = List.of(
            new CurrencyDto(1, "name", "eur", "e", 1L, LocalDateTime.of(2023, 12, 12, 1, 1)),
            new CurrencyDto(2, "name2", "usd", "d", 2L, LocalDateTime.of(2023, 12, 12, 1, 1))
    );

    @Test
    void getCurrencies() {
        when(currencyRepository.getCurrencies()).thenReturn(CURRENCY_RECORDS);
        List<CurrencyDto> currencyDtos = currencyService.getCurrencies();
        assertEquals(currencyDtos, CURRENCY_DTOS);
    }
}