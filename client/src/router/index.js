import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/admin',
        name: 'Admin',
        component: () =>
            import ('../views/Admin.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () =>
            import ('../views/Login.vue')
    },
    {
        path: '/bookings',
        name: 'Bookings',
        component: () =>
            import ('../views/Reservations.vue')
    },
    {
        path: '/companies/:id',
        name: 'Company',
        props: true,
        component: () =>
            import ('../views/Company.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router