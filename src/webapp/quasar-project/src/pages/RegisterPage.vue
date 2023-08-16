<template>
  <q-layout>
    <q-page-container>
      <q-page
        padding
        class="window-height"
      >
        <div class="column justify-center full-height flex">
          <div class="col-auto row justify-center">
            <div class="col-auto">
              <q-card
                square
                bordered
                class="shadow-1"
              >
                <q-card-section>
                  <div class="row q-gutter-md align-center q-py-xs">
                    <div class="text-bold">
                      Register
                    </div>
                  </div>
                </q-card-section>
                <q-form @submit.prevent="submit">
                  <q-card-section>
                    <q-input
                      v-model.trim="username"
                      square
                      label="Username"
                      maxlength="50"
                      :rules="[
                        (value) => value.trim().length !== 0 || 'Username must not be empty',
                        (value) => value.trim().length <= 50 || 'Username length must be lower than 50'
                       ]"
                    />
                    <q-input
                      v-model.trim="password"
                      square
                      type="password"
                      label="Password"
                      :rules="[ (value) => value.trim().length !== 0 || 'Password must not be empty' ]"
                    />
                    <q-input
                      v-model.trim="passwordConfirm"
                      square
                      type="password"
                      label="Confirm password"
                      :rules="[
                        (value) => value.trim().length !== 0 || 'Password must not be empty',
                        (value) => value.trim() === password || 'Passwords must match'
                      ]"
                    />

                    <div v-if="registerFailed">
                      <p class="text-negative text-body1">
                        Username taken
                      </p>
                    </div>
                  </q-card-section>

                  <q-card-actions class="q-px-md q-pb-lg">
                    <q-btn
                      color="primary"
                      type="submit"
                      :disable="loading"
                      :loading="loading"
                      size="lg"
                      class="full-width text-white"
                      label="Register"
                    />
                    <q-btn
                      flat
                      no-caps
                      :disable="loading"
                      :loading="loading"
                      size="lg"
                      class="full-width text-primary q-pt-lg"
                      label="Login"
                      :to="{name: 'login'}"
                    />
                  </q-card-actions>
                </q-form>
              </q-card>
            </div>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useAuthStore } from 'stores/authStore';

const authStore = useAuthStore()

const username = ref('')
const password = ref('')
const passwordConfirm = ref('')
const loading = ref(false)
const registerFailed = ref(false)

async function submit (): Promise<void> {
  loading.value = true

  try {
    await authStore.signUp(username.value.trim(), password.value.trim())
  } catch (_) {
    registerFailed.value = true
  }


  loading.value = false
}

</script>

<style scoped>
.q-card {
  width: 360px;
}
</style>
