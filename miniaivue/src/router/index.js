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
import Loading from "@/views/Loading";


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: UserLayout,
        children: [
            {
              path:'',
              name: Loading,
              component: Loading
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

//路由导航守卫
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('MiniAiToken');

    // 如果存在Token，允许访问页面
    if (token || to.path === '/login' || to.path === '/register' || to.path === '/price' || to.path === '/about' || to.path === '/') {
        next();
    } else {
        // 如果不存在Token，重定向到登录页面或其他需要登录的页面
        next('/login');
    }
});
export default router