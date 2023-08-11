<template>
  <router-view />
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { useAccountStore } from 'stores/accountStore';
import { useCategoryStore } from 'stores/categoryStore';
import { useCurrencyStore } from 'stores/currencyStore';

const accountStore = useAccountStore()
const categoryStore = useCategoryStore()
const currencyStore = useCurrencyStore()

onMounted( async () => {
  await Promise.all([accountStore.load(), categoryStore.load(), currencyStore.load()])
})

</script>
