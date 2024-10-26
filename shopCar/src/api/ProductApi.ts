import http from "../http/index";
//默认导出的api地址
export default {
    //后台访问类型
    //查询
    select: {
        name: "商品的查询",
        url: '/api/product',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    insert: {
        name: "商品的添加",
        url: '/api/product',
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    },
    update: {
        name: "商品的修改",
        url: '/api/product',
        call: async function (params: any = {}) {
            return await http.put(this.url, params)
        }
    },
    delete: {
        name: "商品的删除",
        url: '/api/product',
        call: async function (params: any = {}) {
            return await http.delete(this.url, params)
        }
    },
    patch: {
        name: "商品的状态更新",
        url: '/api/product',
        call: async function (params: any = {}) {
            return await http.patch(this.url, params)
        }
    },
};
