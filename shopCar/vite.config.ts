import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5000,
    //配置代理服务器
    proxy: {
      //以 /api 开头的请求，全部转发到target设置的地址
      "/api": {
        target: 'http://localhost:8080',
        //changeOrigin 的默认值为 false，用来将 host 请求头修改为 target 的 URL。
        changeOrigin: true
      }
    }
  },
  resolve: {
    // 配置路径别名
    alias: {
      '@': path.resolve(__dirname, './src'),
    }
  }
})
