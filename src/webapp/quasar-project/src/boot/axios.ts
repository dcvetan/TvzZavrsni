import AxiosStatic, { AxiosError, AxiosResponse, HttpStatusCode, InternalAxiosRequestConfig } from 'axios';
import { boot } from 'quasar/wrappers';
import { AxiosInstance } from 'axios';
import { App } from 'vue';
import { Router } from 'vue-router';
import { useAuthStore } from 'stores/authStore';

export let axios: AxiosInstance

export default boot ( ({ app, router }) => {
  console.log('Initiating axios...')
  axios = initAxios(app, router)
})

function initAxios (app: App, router: Router): AxiosInstance {
  const axiosInstance = AxiosStatic.create()

  axiosInstance.interceptors.request.use(
    <T>(request: InternalAxiosRequestConfig<T>) => {
      const { user } = useAuthStore();
      const isLoggedIn = !!user?.data.token;
      if (isLoggedIn) {
        request.headers.setAuthorization(`Bearer ${user.data.token}`)
      }
      return request
    }
  )

  axiosInstance.interceptors.response.use(
    <T>(response: AxiosResponse<T>) => response,
    (error: AxiosError) => {
      if (error.response?.status === HttpStatusCode.Unauthorized) {
        if (router.currentRoute.value.name !== 'login') {
          void router.push({ name: 'login' })
          localStorage.removeItem('user');
        }
      }
      return Promise.reject(error)
    }
  )

  return axiosInstance
}
