import { axios } from 'boot/axios';
import { Account } from 'src/models/account';
import { AccountRequest } from 'src/models/requests/accountRequest';

const baseUrl = 'http://localhost:8080/api'

export const accountService = {
  getAccounts: async (): Promise<Account[]> => {
    return (await axios.get(`${baseUrl}/account`)).data
  },

  saveAccount: async (accountRequest: AccountRequest): Promise<Account[]> => {
    return (await axios.post(`${baseUrl}/account`, accountRequest)).data
  },

  deleteAccountById: async (id: number): Promise<void> => {
    await axios.delete(`${baseUrl}/account/${id}`)
  }
}
