import http from "../http";

//默认导出
export default {

    register: {
        name: '用户注册',
        url: '/api/user/register',
        call: async function (params: any = {}) { 
            return await http.post(this.url,params)
        }
    },
    login: {
        name: '用户登录',
        url: '/api/user/login',
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    }

}
