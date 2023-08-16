package hr.tvz.financije.controllers.models.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebhookCommand(
        @JsonProperty("event_type") String eventType,
        @JsonProperty("resource") ResourceInfo resource
) {
    public record ResourceInfo(@JsonProperty("amount") AmountInfo amount) {
        public record AmountInfo(
                @JsonProperty("currency_code") String currencyCode,
                @JsonProperty("value") double value
        ) {}
    }
}
