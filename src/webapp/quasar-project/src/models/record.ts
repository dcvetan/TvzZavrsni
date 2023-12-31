export interface Record {
  id: number,
  amount: number,
  type: string,
  recordDate: Date,
  description: string | undefined,
  categoryId: number,
  accountId: number,
  receivingAccountId: number | undefined
}
