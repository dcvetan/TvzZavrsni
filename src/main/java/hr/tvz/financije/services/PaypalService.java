package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.RecordCommand;
import hr.tvz.financije.controllers.models.commands.WebhookCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaypalService {

    private static final int PAYPAL_ACCOUNT_ID = 1;
    private static final String PAYPAL_CAPTURE = "PAYMENT.CAPTURE.COMPLETED";
    private static final String PAYPAL_SALE = "PAYMENT.SALE.COMPLETED";

    private final RecordService recordService;

    public void updatePaypalAccount(WebhookCommand command) {
        recordService.saveRecord(new RecordCommand(
                null, command.resource().amount().value(), command.eventType().compareTo(PAYPAL_CAPTURE) == 0 ? "Income" : "Expense",
                LocalDateTime.now(), null, 1, 1, null
        ));
    }
}
