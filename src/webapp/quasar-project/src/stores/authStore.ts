import { defineStore } from 'pinia';
import { axios } from 'boot/axios';

const baseUrl = 'http://localhost:8080/api/authenticate'

export const useAuthStore = defineStore(
  'auth',
  {
    state: () => ({
      user: localStorage.getItem('user') === null ? null : JSON.parse(<string>localStorage.getItem('user')),
      returnUrl: '/dashboard'
    }),
    actions: {
      async signIn(username: string, password: string) {
        const user = await axios.post(`${baseUrl}/signin`, { username, password })

        this.user = user;

        localStorage.setItem('user', JSON.stringify(user));

        this.router.push(this.returnUrl || '/dashboard')
      },
      async signUp(username: string, password: string) {
        const user = await axios.post(`${baseUrl}/signup`, { username, password })

        this.user = user;

        localStorage.setItem('user', JSON.stringify(user));

        this.router.push(this.returnUrl || '/dashboard')
      },
      logout() {
        this.user = null;
        localStorage.removeItem('user');
        this.router.push('/login')
      }
    },
});
