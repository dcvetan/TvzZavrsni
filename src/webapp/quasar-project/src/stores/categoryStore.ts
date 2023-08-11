import { defineStore } from 'pinia';
import { Category } from 'src/models/category';
import { categoryService } from 'src/services/categoryService';

export const useCategoryStore = defineStore(
  'category',
  {
    state: () => ({
      categories: [] as Category[],
      loading: false
    }),

    actions: {
      async load(): Promise<void> {
        this.loading = true
        console.log('Loading category store...')
        this.categories = await categoryService.getCategories();
        this.loading = false
      }
    },
  });
