import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/layout/Layout.vue";

Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    // redirect:'/login',
    children:[
      {
        path: 'user',
        name: 'User',
        component: ()=>import("@/views/User"),
      },
      {
        path:'commodity',
        name:'Commodity',
        component:()=>import("@/views/Commodity"),
      },
      {
        path:'addcommodity',
        name:'AddCommodity',
        component:()=>import("@/views/AddCommodity"),
      },
      {
        path:'cat',
        name:'Cat',
        component:()=>import("@/views/Cat"),
      },
      {
        path:'description',
        name:'Description',
        component:()=>import("@/views/Description"),
      },
      {
        path:'order',
        name:'Order',
        component:()=>import("@/views/Order"),
      },

    ]
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


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
