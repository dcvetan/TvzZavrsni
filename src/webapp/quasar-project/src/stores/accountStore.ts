import { defineStore } from 'pinia';
import { Account } from 'src/models/account';
import { accountService } from 'src/services/accountService';

export const useAccountStore = defineStore(
  'account',
  {
    state: () => ({
      accounts: [] as Account[],
      loading: false
    }),

    actions: {
      async load(): Promise<void> {
        this.loading = true
        console.log('Loading account store...')
        this.accounts = await accountService.getAccounts();
        this.loading = false
      }
    },
  });
