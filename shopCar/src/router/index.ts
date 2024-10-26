//引入对应的包
import { createRouter,createWebHistory } from 'vue-router'

//定义路由规则
const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import("@/views/Home.vue"),
        // useFrame 代表是否全屏显示  如果为true 就不是全屏显示  如果为false 就是全屏显示
        // show 是控制是否在菜单上显示
        meta: { title: '首页', show: true, icon:'HomeFilled',useFrame:true}
    },
    {
        path: '/moveShop',
        name: 'moveShop',
        component: () => import("@/views/MoveShop.vue"),
        meta: { title: '移动端首页', show: false, icon: 'HomeFilled', useFrame: false }
    },
    {
        path: '/movelist',
        name: 'movelist',
        component: () => import("@/views/move/MoveList.vue"),
        meta: { title: '汽车列表', show: false, icon: 'HomeFilled', useFrame: false }
    },
    {
        path: '/cardetails',
        name: 'cardetails',
        component: () => import("@/views/move/Cardetails.vue"),
        meta: { title: '汽车详情页', show: false, icon: 'HomeFilled', useFrame: false }
    },
    {
        path: '/moveCategory',
        name: 'moveCategory',
        component: () => import("@/views/move/MoveCategory.vue"),
        meta: { title: '汽车分类', show: false, icon: 'HomeFilled', useFrame: false }
    },
    {
        path: '/screen',
        name: 'screen',
        component: () => import("@/views/echart/Index.vue"),
        meta: { title: '数据统计', show: true, icon: 'DataBoard' }
    },
    {
        path: '/movecrat',
        name: 'movecrat',
        component: () => import("@/views/move/MoveCrat.vue"),
        meta: { title: "购物车", show: false, icon: 'HomeFilled', useFrame: false }
    },
    {
        path: '/product',
        component:()=>import("@/views/product/Index.vue"),
        meta: { title: '商品管理', show: true, icon: 'Goods' },
        children: [
            {
                path: '/product/add',
                name: 'product-add',
                component: () => import("@/views/product/Add.vue"),
                meta: { title: '添加商品', show: true, icon: 'Plus' }
            },
            {
                path: '/product/edit',
                name: 'product-edit',
                component: () => import("@/views/product/Edit.vue"),
                meta: { title: '商品修改', show: true, icon: 'Edit' }
            }, {
                path: '/product/list',
                name: 'product-list',
                component: () => import("@/views/product/List.vue"),
                meta: { title: '商品列表', show: true, icon: 'Tickets' }
            }
        ]
    },
    {
        path: '/category',
        component: () => import("@/views/category/Index.vue"),
        meta: { title: '类目管理', show: true, icon: 'Grid' },
        children: [
            {
                path: '/category/add',
                name: 'category-add',
                component: () => import("@/views/category/Add.vue"),
                meta: { title: '类目添加', show: true, icon: 'Plus' }
            },
            {
                path: '/category/edit',
                name: 'category-edit',
                component: () => import("@/views/category/Edit.vue"),
                meta: { title: '类目修改', show: false, icon: 'Edit' }
            }, {
                path: '/category/list',
                name: 'category-list',
                component: () => import("@/views/category/List.vue"),
                meta: { title: '类目列表', show: true, icon: 'Tickets' }
            }
        ]
    },
    {
        path: '/user',
        component: () => import("@/views/user/Index.vue"),
        meta: { title: '', show: false, icon: 'Aim' },
        children: [{
            path: '/user/login',
            name: 'user-login',
            component: () => import("@/views/user/Login.vue"),
            meta: { title: "用户登录", show: false, icon: 'HomeFilled', useFrame: false }
        }, {
            path: '/user/register',
            name: 'user-register',
            component: () => import("@/views/user/Register.vue"),
            meta: { title: "用户登录", show: false, icon: 'HomeFilled', useFrame: false }
        }]
    },{
        path: '/uviewDemo',
        name: 'uviewDemo',
        component: () => import("@/views/move/dcshu.vue"),
        meta: { title: "购物车", show: false, icon: 'HomeFilled', useFrame: false }
    },
]
//创建路由的实例
const router = createRouter({
    history: createWebHistory(),
    routes
})
//导出路由
export default router
