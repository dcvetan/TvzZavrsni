import { Record } from 'src/models/record';
import { axios } from 'boot/axios';
import { RecordRequest } from 'src/models/requests/recordRequest';

const baseUrl = 'http://localhost:8080/api'

export const recordService = {
  getRecords: async (): Promise<Record[]> => {
    return (await axios.get(`${baseUrl}/record`)).data
  },

  saveRecord: async (record: RecordRequest): Promise<Record[]> => {
    return (await axios.post(`${baseUrl}/record`, record)).data
  },

  deleteRecordById: async (id: number): Promise<void> => {
    await axios.delete(`${baseUrl}/record/${id}`)
  }
}
