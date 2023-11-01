import Vue from 'vue';
import VueRouter from 'vue-router'
import Home from '@/views/Home'
import About from "@/views/About";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        component: About
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
})
export default router