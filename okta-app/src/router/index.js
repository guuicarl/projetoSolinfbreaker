import { createRouter, createWebHistory } from 'vue-router'
import { LoginCallback, navigationGuard } from '@okta/okta-vue'
import LoginComponent from '@/components/Login'
import HomeComponent from '@/components/HelloWorld'
import Teste from '@/components/Teste'
import Carteira from '@/components/Carteira'
import About from '@/views/About'
import Stocks from '@/components/Stocks'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: HomeComponent,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/login',
      component: LoginComponent
    },
    {
      path: '/login/callback',
      component: LoginCallback
    },
    {
      path: '/teste',
      component: Teste,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/carteira',
      component: Carteira,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/about',
      component: About,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/stocks',
      component: Stocks,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach(navigationGuard)

export default router