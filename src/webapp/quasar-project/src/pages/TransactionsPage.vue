<template>
  <q-page class="row items-center justify-evenly">
    <q-table
      title="Transactions"
      :rows="recordOverviews"
      :columns="columns"
      row-key="name"
      style="min-width: 1000px"
      :loading="loading"
    >

    </q-table>
  </q-page>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { recordService } from 'src/services/recordService';
import { Record } from 'src/models/record';
import { useAccountStore } from 'stores/accountStore';
import { useCategoryStore } from 'stores/categoryStore';
import { useCurrencyStore } from 'stores/currencyStore';

interface RecordOverview {
  id: number,
  amount: number,
  recordDate: Date,
  categoryName: string,
  accountName: string,
  currencyCode: string
}

const columns = [
  {
    name: 'account',
    label: 'Account name',
    align: 'left',
    field: row => row.accountName,
    sortable: true
  },
  {
    name: 'amount',
    label: 'Amount',
    align: 'left',
    field: row => row.amount,
    sortable: true
  },
  {
    name: 'currency',
    label: 'Currency',
    align: 'left',
    field: row => row.currencyCode,
    sortable: true
  },
  {
    name: 'category',
    label: 'Category',
    align: 'left',
    field: row => row.categoryName,
    sortable: true
  },
]

const accountStore = useAccountStore()
const categoryStore = useCategoryStore()
const currencyStore = useCurrencyStore()

const loading = ref<boolean>(false)
const records = ref<Record[]>([])
const recordOverviews = ref<RecordOverview[]>([])

const recordAmountPerCategory = ref<Map<string, number>>()

onMounted(async () => {
  await fetchData()
})

async function fetchData () {
  loading.value = true

  records.value = (await recordService.getRecords())

  recordOverviews.value = records.value.map(record => {

    const account = accountStore.accounts.find(account => account.id === record.accountId)
    const category = categoryStore.categories.find(category => category.id === record.categoryId)
    const currency = currencyStore.currencies.find(currency => currency.id === account?.currencyId)

    return {
      ...record,
      categoryName: category?.name,
      accountName: account?.name,
      currencyCode: currency?.code
    } as RecordOverview
  })

  recordAmountPerCategory.value = new Map<string, number>()

  recordOverviews.value.forEach(recordOverview => {
    if (recordAmountPerCategory.value?.has(recordOverview.categoryName)) {
      recordAmountPerCategory.value?.set(recordOverview.categoryName, recordAmountPerCategory.value.get(recordOverview.categoryName)! + recordOverview.amount)
    } else {
      recordAmountPerCategory.value?.set(recordOverview.categoryName, recordOverview.amount)
    }
  })

  loading.value = false
}

</script>

<style scoped>

</style>
