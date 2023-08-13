export interface RecordRequest {
  id: number | undefined,
  amount: number,
  type: string,
  recordDate: Date,
  description: string | undefined,
  categoryId: number,
  accountId: number,
  receivingAccountId: number | undefined
}
