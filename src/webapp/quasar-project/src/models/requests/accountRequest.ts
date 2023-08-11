export interface AccountRequest {
  id: number | undefined,
  name: string,
  amount: number,
  type: string,
  color: string,
  source: string,
  currencyId: number
}
