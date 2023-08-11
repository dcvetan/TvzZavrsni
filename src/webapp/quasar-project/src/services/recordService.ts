import { Record } from 'src/models/record';
import { axios } from 'boot/axios';

const baseUrl = 'http://localhost:8080/api'

export const recordService = {
  getRecords: async (): Promise<Record[]> => {
    return (await axios.get(`${baseUrl}/record`)).data
  },

  saveRecord: async (record: Record): Promise<Record[]> => {
    return (await axios.post(`${baseUrl}/record`, record)).data
  },

  deleteRecordById: async (id: number): Promise<void> => {
    await axios.delete(`${baseUrl}/record/${id}`)
  }
}
