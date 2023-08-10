import { defineStore } from 'pinia';
import { axios } from 'boot/axios';

const baseUrl = 'http://localhost:8080/api/authenticate'

export const useAuthStore = defineStore(
  'auth',
  {
    state: () => ({
      user: localStorage.getItem('user') === null ? null : JSON.parse(<string>localStorage.getItem('user')),
      returnUrl: '/'
    }),
    actions: {
      async signIn(username: string, password: string) {
        const user = await axios.post(`${baseUrl}/signin`, { username, password })

        this.user = user;

        localStorage.setItem('user', JSON.stringify(user));

        await this.router.push(this.returnUrl || '/')
      },
      async signUp(username: string, password: string) {
        const user = await axios.post(`${baseUrl}/signup`, { username, password })

        this.user = user;

        localStorage.setItem('user', JSON.stringify(user));

        await this.router.push(this.returnUrl || '/')
      },
      async logout() {
        this.user = null;
        localStorage.removeItem('user');
        await this.router.push('/login')
      }
    },
});
