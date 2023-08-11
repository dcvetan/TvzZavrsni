export interface Record {
  id: number,
  amount: number,
  type: string,
  recordDate: Date,
  description: string,
  categoryId: number,
  accountId: number,
  receivingAccountId: number | undefined
}
