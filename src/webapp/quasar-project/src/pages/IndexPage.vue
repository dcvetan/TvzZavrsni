<template>
  <q-page class="row items-center justify-evenly">
    <div class="q-pa-lg">
      <q-list bordered padding class="q-pa-md" style="min-width: 300px">

        <q-item-label header>List of accounts</q-item-label>

        <div v-for="account in accountStore.accounts" :key="account.id" class="q-pb-md">
          <q-item
            clickable
            :class="'bg-' + account.color + ' q-pa-none rounded-borders'"
            @click="onEditAccount(account)"
          >
            <q-item-section class="q-pa-md">
              <q-item-label>{{account.name}}</q-item-label>
              <q-item-label caption>{{account.currencySymbol}} {{account.amount}}</q-item-label>
            </q-item-section>

            <q-item-section side>
              <q-icon name="delete" color="black" class="q-pr-md" @click="onDeleteAccount(account.id)" />
            </q-item-section>
          </q-item>
        </div>


        <q-item
          clickable
          class="q-pa-none rounded-borders"
          style="border: 1px solid #1976d2"
          @click="accountFormDialogVisible = true"
        >
          <q-item-section class="q-pa-md">
            <div class="q-pr-md text-primary text-bold">
              <q-item-label>ADD</q-item-label>
              <q-item-label>ACCOUNT</q-item-label>
            </div>
          </q-item-section>

          <q-item-section side>
            <q-icon name="add_circle" color="primary" class="q-pr-md" />
          </q-item-section>
        </q-item>

      </q-list>
    </div>

    <div class="q-pa-lg">
      <q-list bordered padding class="q-pa-md" style="min-width: 300px">

        <q-item-label header>Last records overview</q-item-label>

        <q-item clickable v-for="record in recordOverviews" :key="record.id">
          <q-item-section avatar>
            <q-icon :name="record.categoryIcon" :color="record.categoryColor" />
          </q-item-section>

          <q-item-section>
            <q-item-label>{{record.accountName}}</q-item-label>
            <q-item-label caption>{{record.accountType}}</q-item-label>
          </q-item-section>

          <q-item-section side top>
            <q-item-label>{{record.currencySymbol}} {{record.amount}}</q-item-label>
            <q-item-label caption>{{record.recordDate}}</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </div>

    <q-inner-loading
      :showing="loading"
      size="sm"
      label="Please wait..."
      label-class="text-primary"
      label-style="font-size: 0.8em"
    />
    <q-dialog
      v-model="accountFormDialogVisible"
      persistent
    >
      <q-card style="width: 700px; max-width: 80vw;">
        <q-card-section>
          <div class="text-h6">{{ selectedAccountId === undefined ? 'New account' : 'Edit account' }}</div>
        </q-card-section>

        <q-form class="q-pt-none q-px-lg" @submit.prevent="onAccountFormSubmit">
          <q-input
            v-model.trim="accountName"
            label="Account name"
            class="q-pb-md"
            maxlength="50"
            :rules="[
              (value) => value.trim().length !== 0 || 'Account name must not be empty',
              (value) => value.trim().length <= 50 || 'Account name length must be lower than 50'
            ]"
          />
          <q-select
            v-model="accountType"
            label="Type"
            class="q-pb-md"
            :options="['General', 'Cash', 'Card', 'Investment', 'Savings']"
            :rules="[
              (value) => value.trim().length !== 0 || 'Account type must be selected',
            ]"
          />
          <q-input
            v-model.trim="accountInitialValue"
            label="Initial value"
            type="number"
            class="q-pb-md"
            :rules="[
              (value) => value !== undefined || 'Account initial value must be set',
            ]"
          />
          <q-select
            v-model="accountCurrency"
            label="Currency"
            class="q-pb-md"
            :options="currencyOptions"
            :rules="[
              (value) => value !== undefined || 'Account currency must be set',
            ]"
          />
          <q-select
            v-model="accountColor"
            label="Color"
            class="q-pb-md"
            :options="['primary', 'red', 'pink', 'purple', 'deep-purple', 'indigo', 'cyan', 'teal', 'green', 'lime', 'yellow', 'orange', 'deep-orange', 'brown', 'blue-grey']"
            :rules="[
              (value) => value !== undefined || 'Account color must be set',
            ]"
          >
            <template #option="{ itemProps, opt }">
              <q-item v-bind="itemProps">
                <q-item-section :class="'bg-' + opt + ' q-pa-md rounded-borders'"/>
              </q-item>
            </template>
            <template #selected-item="{ opt }">
              <q-item-section :class="'bg-' + opt + ' q-pa-md rounded-borders'"/>
            </template>
          </q-select>

          <q-card-actions align="right">
            <q-btn
              flat
              label="Save"
              color="primary"
              type="submit"
              :loading="accountFormDialogLoading"
            />
            <q-btn
              flat
              label="Cancel"
              color="dark"
              :loading="accountFormDialogLoading"
              @click="onAccountDialogClose"
            />
          </q-card-actions>
        </q-form>
      </q-card>
    </q-dialog>
    <q-dialog
      v-model="accountDeleteDialogVisible"
    >
      <q-card>
        <q-card-section class="row items-center">
          <q-avatar icon="delete" text-color="negative" />
          <span class="q-ml-sm text-body1">Are you sure you want to delete the account?</span>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat label="Confirm" color="primary" @click="onConfirmDeleteAccount" />
          <q-btn flat label="Cancel" color="black" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { recordService } from 'src/services/recordService';
import { useAccountStore } from 'stores/accountStore';
import { useCategoryStore } from 'stores/categoryStore';
import { useCurrencyStore } from 'stores/currencyStore';
import { accountService } from 'src/services/accountService';
import { useQuasar } from 'quasar';
import { Account } from 'src/models/account';

interface RecordOverview {
  id: number,
  amount: number,
  type: string,
  recordDate: Date,
  description: string,
  categoryIcon: string,
  categoryColor: string,
  accountName: string,
  accountType: string,
  currencySymbol: string
}

interface CurrencyOption {
  label: string,
  value: number
}

const $q = useQuasar()
const accountStore = useAccountStore()
const categoryStore = useCategoryStore()
const currencyStore = useCurrencyStore()

const recordOverviews = ref<RecordOverview[]>([])
const loading = ref<boolean>(false)
const accountFormDialogLoading = ref<boolean>(false)
const accountFormDialogVisible = ref<boolean>(false)
const accountDeleteDialogLoading = ref<boolean>(false)
const accountDeleteDialogVisible = ref<boolean>(false)
const selectedAccountId = ref<number>()

const currencyOptions = ref<object[]>([])

const accountName = ref<string>('')
const accountType = ref<string>('')
const accountInitialValue = ref<number>(0)
const accountCurrency = ref<CurrencyOption>()
const accountColor = ref<string>('pink')

onMounted(async () => {
  await fetchData()
})

async function fetchData () {
  loading.value = true

  const records = (await recordService.getRecords())

  recordOverviews.value = records.map(record => {

    const account = accountStore.accounts.find(account => account.id === record.accountId)
    const category = categoryStore.categories.find(category => category.id === record.categoryId)
    const currency = currencyStore.currencies.find(currency => currency.id === account?.currencyId)

    return {
      ...record,
      categoryIcon: category?.icon,
      categoryColor: category?.color,
      accountName: account?.name,
      accountType: account?.type,
      currencySymbol: currency?.symbol
    } as RecordOverview
  })

  currencyOptions.value = currencyStore.currencies.map(
    currency => ({ label: currency.code, value: currency.id })
  )

  loading.value = false
}

async function onAccountFormSubmit() {
  accountFormDialogLoading.value = true

  if (accountCurrency.value !== undefined) {
    await accountService.saveAccount({
      id: selectedAccountId.value,
      color: accountColor.value,
      type: accountType.value,
      amount: accountInitialValue.value,
      currencyId: accountCurrency.value.value,
      name: accountName.value,
      source: 'Manual'
    })
  }

  showNotif('Account saved successfully!')

  await accountStore.load()

  accountFormDialogVisible.value = false
  accountFormDialogLoading.value = false
  selectedAccountId.value = undefined
}

function onEditAccount(account: Account) {
  selectedAccountId.value = account.id

  const currency = currencyStore.currencies.find(currency => currency.id === account.currencyId)

  if (currency !== undefined) {
    accountName.value = account.name
    accountType.value = account.type
    accountInitialValue.value = account.amount
    accountCurrency.value = {
      label: currency.code,
      value: account.currencyId
    }
    accountColor.value = account.color
  }

  accountFormDialogVisible.value = true
}

function onAccountDialogClose() {
  accountFormDialogVisible.value = false
  selectedAccountId.value = undefined
  resetFormValues()
}

function resetFormValues() {
  accountName.value = ''
  accountType.value = ''
  accountInitialValue.value = 0
  accountCurrency.value = undefined
  accountColor.value = 'primary'
}

function onDeleteAccount (id: number) {
  selectedAccountId.value = id
  accountDeleteDialogVisible.value = true
}

async function onConfirmDeleteAccount () {
  accountDeleteDialogLoading.value = true

  if (selectedAccountId.value !== undefined) {
    await accountService.deleteAccountById(selectedAccountId.value)
  }

  showNotif('Account deleted successfully!')

  await accountStore.load()

  accountDeleteDialogVisible.value = false
  selectedAccountId.value = undefined
  accountDeleteDialogLoading.value = false
}

function showNotif (message: string) {
  $q.notify({
    color: 'green',
    textColor: 'white',
    icon: 'done',
    message,
    position: 'top-right',
    actions: [
      { label: 'Dismiss', color: 'yellow-4' }
    ],
    timeout: 5000
  })
}

</script>
