import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import EmployeeTable from '@/components/EmployeeTable/EmployeeTable'
import User from '@/components/User'
import Login from '@/components/Login'
import Protected from '@/components/Protected'

import store from '../store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/', name: 'Home', component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  { path: '/callservice', component: Service },
  { path: '/bootstrap', name: 'Bootstrap', component: Bootstrap },
  { path: '/employee', name: 'Employee', component: EmployeeTable },
  { path: '/user', name: 'User', component: User },
  { path: '/login', name: 'Login', component: Login },
  {
    path: '/protected',
    component: Protected,
    meta: {
      requiresAuth: true
    }
  },

  // otherwise redirect to home
  { path: '*', redirect: '/' }
]

const router = new VueRouter({
  mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!store.getters.isLoggedIn) {
      next({
        path: '/login'
      })
    } else {
      next()
    }
  } else {
    next() // make sure to always call next()!
  }
})

export default router
