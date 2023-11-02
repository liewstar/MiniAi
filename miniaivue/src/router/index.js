import Vue from 'vue';
import VueRouter from 'vue-router'
import About from "@/views/About";
import Price from "@/views/Price";
import Login from "@/views/Login"
import Register from "@/views/Register";
import MainPage from "@/views/MainPage";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: MainPage
    },
    {
        path: '/about',
        name: 'About',
        component: About
    },
    {
        path: '/price',
        name: 'Price',
        component: Price
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
})
export default router