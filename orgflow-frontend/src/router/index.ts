import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/',
      component: () => import('../views/Home.vue'),
      meta: {
        requiresAuth: true
      },
      redirect: '/dashboard', // 默认重定向到仪表盘
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          component: () => import('@/components/dashboard/Dashboard.vue'),
          meta: { requiresAuth: true }
        },
        {
          path: '/user',
          name: 'user',
          component: () => import('@/components/system/UserManagement.vue'),
          meta: { requiresAuth: true }
        },
        // 可以添加更多子路由
      ]
    },
    // 捕获所有未匹配的路由，重定向到首页
    {
      path: '/:pathMatch(.*)*',
      redirect: '/'
    }
  ],
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')

  if (to.path === '/' || to.path === '/dashboard') {
    if (!token) {
      // 未登录时，重定向到登录页，不带重定向参数
      next({ path: '/login' })
      return
    }
    // 已登录，正常访问
    next()
    return
  }

  if (to.meta.requiresAuth) {
    if (!token) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      // 已登录，允许访问
      next()
    }
  } else {
    // 不需要登录的页面
    if (token && to.path === '/login') {
      // 已登录情况下访问登录页，重定向到首页
      next({ path: '/' })
    } else {
      // 正常访问不需要登录的页面
      next()
    }
  }
})

export default router