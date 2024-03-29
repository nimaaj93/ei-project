import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    linkExactActiveClass: 'active',
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            name: 'Login',
            component: () => import('./views/Login.vue'),
            meta: {
                layout: 'simple',
                authenticated: false
            }
        },{
            path: '/home',
            name: 'Home',
            component: () => import('./views/Home.vue'),
            meta: {
                layout: 'default',
                authenticated: true
            }
        },{
            path: '/profile',
            name: 'Profile',
            component: () => import('./views/Profile.vue'),
            meta: {
                layout: 'default',
                authenticated: true
            }
        },{
            path: '/create-ticket',
            name: 'CreateTicket',
            component: () => import('./views/CreateTicket.vue'),
            meta: {
                layout: 'default',
                authenticated: true
            }
        },{
            path: '/user-tickets',
            name: 'UserTickets',
            component: () => import('./views/UserTickets.vue'),
            meta: {
                layout: 'default',
                authenticated: true
            }
        },{
            path: '/tickets-management',
            name: 'TicketsManagement',
            component: () => import('./views/TicketsManagement.vue'),
            meta: {
                layout: 'default',
                authenticated: true,
                authority: 'ADMIN',
            }
        },{
            path: '/unconfirmed-users',
            name: 'UnconfirmedUsers',
            component: () => import('./views/UnconfirmedUsers.vue'),
            meta: {
                layout: 'default',
                authenticated: true,
                authority: 'ADMIN',
            }
        },{
            path: '/user-list',
            name: 'UserList',
            component: () => import('./views/UserList.vue'),
            meta: {
                layout: 'default',
                authenticated: true,
                authority: 'ADMIN',
            }
        },{
            path: '/signup',
            name: 'SignUp',
            component: () => import('./views/SignUp.vue'),
            meta: {
                layout: 'simple',
                authenticated: false
            }
        },
        {
            path: '*',
            redirect: '/login'
        },
    ]
})