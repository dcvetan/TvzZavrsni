import { defineStore } from 'pinia';
import { Currency } from 'src/models/currency';
import { currencyService } from 'src/services/currencyService';

export const useCurrencyStore = defineStore(
  'currency',
  {
    state: () => ({
      currencies: [] as Currency[],
      loading: false
    }),

    actions: {
      async load(): Promise<void> {
        this.loading = true
        console.log('Loading currency store...')
        this.currencies = await currencyService.getCurrencies();
        this.loading = false
      }
    },
  });
