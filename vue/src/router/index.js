import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "@/views/Home.vue";


Vue.use(VueRouter)

const routes = [
  //   商城路由
  {
    path: '/shop',
    name: 'shop',
    component: Home,
    children: [
      {
        path: 'myshop',
        name: 'Myshop',
        component: ()=>import("@/views/Myshop"),
      },
      {
        path:'detail/:id',
        name:'Detail',
        component:()=>import("@/views/Detail")
      }
    ]
  },
  {
    path: '/',
    name: 'home',
    component: Home,
    redirect: '/shop/myshop',
  },
  {
    path:'/Login',
    name:'Login',
    component: ()=>import("@/views/Login")
  },
  {
    path:'/Register',
    name:'Register',
    component: ()=>import("@/views/Register")
  },
  {
    path:'/Cart',
    name:'Cart',
    component: ()=>import("@/views/Cart")
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
