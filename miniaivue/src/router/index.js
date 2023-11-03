import Vue from 'vue';
import VueRouter from 'vue-router'
import About from "@/views/About";
import Price from "@/views/Price";
import Login from "@/views/Login"
import Register from "@/views/Register";
import Me from "@/views/Me";
import SendMessage from "@/views/SendMessage";
import Echart from "@/views/admin/Echart";
import Orders from "@/views/admin/Orders";
import Users from "@/views/admin/Users";
import UserLayout from "@/views/UserLayout";
import AdminLayout from "@/views/admin/AdminLayout";
import MainPage from "@/views/MainPage";


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: UserLayout,
        children: [
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
            },
            {
                path: '/chat',
                name: 'Chat',
                component: MainPage,
                children:[
                    {
                        path: 'me',
                        name: 'Me',
                        component: Me
                    },
                    {
                        path: 'sendMessage',
                        name: 'SendMessage',
                        component: SendMessage
                    },
                ]
            }

        ]
    },



    {
        path: '/admin',
        name: 'Admin',
        component: AdminLayout,
        children: [
            {
                path: 'users',
                name: 'Users',
                component: Users
            },
            {
                path: 'orders',
                name: 'Orders',
                component: Orders
            },
            {
                path: 'echarts',
                name: 'Echarts',
                component: Echart
            }
        ]
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
})
export default router