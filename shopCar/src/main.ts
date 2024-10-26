import { createApp } from 'vue'
//import './style.css'
import App from './App.vue'
// 1. 引入你需要的组件
import vant from 'vant';
// 2. 引入组件样式
import 'vant/lib/index.css';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//注册路由组件
import router from './router/index'
//国际化标准
import zhCn from 'element-plus/es/locale/lang/zh-cn'
// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
    app.use(vant)
    app.use(ElementPlus, {
    locale: zhCn,
    })
    app.use(router)
    app.mount('#app')
