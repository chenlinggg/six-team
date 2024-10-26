import http from "../http/index";
//默认导出的api地址
export default {
    //后台访问类型
    //查询
    upload: {
        name: "类目的查询",
        url: '/api/upload',
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    },
    imglist: {
        name: '图片列表',
        url: '/api/upload',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    }
};