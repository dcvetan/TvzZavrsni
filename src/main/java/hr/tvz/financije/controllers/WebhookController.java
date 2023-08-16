package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.commands.WebhookCommand;
import hr.tvz.financije.services.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final PaypalService service;

    @PostMapping
    public HttpStatus handleWebhook(@RequestBody WebhookCommand webhookCommand) {
        service.updatePaypalAccount(webhookCommand);
        return HttpStatus.OK;
    }
}
