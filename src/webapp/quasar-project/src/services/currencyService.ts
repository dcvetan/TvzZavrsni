import { axios } from 'boot/axios';
import { Currency } from 'src/models/currency';

const baseUrl = 'http://localhost:8080/api'


export const currencyService = {
  getCurrencies: async (): Promise<Currency[]> => {
    return (await axios.get(`${baseUrl}/currency`)).data
  }
}
