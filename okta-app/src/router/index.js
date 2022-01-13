import { createRouter, createWebHistory } from 'vue-router'
import { LoginCallback, navigationGuard } from '@okta/okta-vue'
import LoginComponent from '@/components/Login'
import HomeComponent from '@/components/HelloWorld'

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
  ]
})

router.beforeEach(navigationGuard)

export default router