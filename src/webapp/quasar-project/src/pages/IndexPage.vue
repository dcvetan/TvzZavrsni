<template>
  <q-page class="row items-center justify-evenly">
    <div class="q-pa-lg">
      <q-list bordered padding class="q-pa-md" style="min-width: 300px">

        <q-item-label header>Last records overview</q-item-label>

        <q-item clickable v-for="record in recordOverviews" :key="record.id">
          <q-item-section avatar>
            <q-icon :name="record.categoryIcon" :color="record.categoryColor" />
          </q-item-section>

          <q-item-section>
            <q-item-label>{{record.categoryName}}</q-item-label>
            <q-item-label caption>{{record.accountType}}</q-item-label>
          </q-item-section>

          <q-item-section side top>
            <q-item-label>{{record.currencySymbol}} {{record.amount}}</q-item-label>
            <q-item-label caption>{{record.recordDate}}</q-item-label>
          </q-item-section>
        </q-item>

        <q-inner-loading
          :showing="loading"
          size="sm"
          label="Please wait..."
          label-class="text-primary"
          label-style="font-size: 0.8em"
        />

      </q-list>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { recordService } from 'src/services/recordService';
import { useAccountStore } from 'stores/accountStore';
import { useCategoryStore } from 'stores/categoryStore';

interface RecordOverview {
  id: number,
  amount: number,
  type: string,
  recordDate: Date,
  description: string,
  categoryName: string,
  categoryIcon: string,
  categoryColor: string,
  accountType: string,
  currencySymbol: string
}

const accountStore = useAccountStore()
const categoryStore = useCategoryStore()

const recordOverviews = ref<RecordOverview[]>([])
const loading = ref<boolean>(false)

onMounted(async () => {
  await fetchData()
})

async function fetchData () {
  loading.value = true

  const records = (await recordService.getRecords())

  recordOverviews.value = records.map(record => {

    const account = accountStore.accounts.find(account => account.id === record.accountId)
    const category = categoryStore.categories.find(category => category.id === record.categoryId)

    return {
      ...record,
      categoryName: category?.name,
      categoryIcon: category?.icon,
      categoryColor: category?.color,
      accountType: account?.type,
      currencySymbol: account?.currencySymbol
    } as RecordOverview
  })

  loading.value = false
}

</script>
