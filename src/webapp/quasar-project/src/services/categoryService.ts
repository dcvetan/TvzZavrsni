import { axios } from 'boot/axios';
import { Category } from 'src/models/category';

const baseUrl = 'http://localhost:8080/api'

export const categoryService = {
  getCategories: async (): Promise<Category[]> => {
    return (await axios.get(`${baseUrl}/category`)).data
  },

  saveCategory: async (category: Category): Promise<Category[]> => {
    return (await axios.post(`${baseUrl}/category`, category)).data
  },

  deleteCategoryById: async (id: number): Promise<void> => {
    await axios.delete(`${baseUrl}/category/${id}`)
  }
}
