import http from "../http/index";
//默认导出的api地址
export default {
    //查询所有类目总数
    cateCount: {
        name: "类目的总数量",
        url: '/api/cateCount',
       
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    proCount: {
        name: "商品的总数量",
        url: '/api/productCount',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    cateTodayCount: {
        name: "当天新增类目数",
        url: '/api/catetodayCount',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    productToday: {
        name: "当天新增类目数",
        url: '/api/productToday',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    select: {
        name: "汽车类目下的品牌",
        url: '/api/cateprod',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
};
