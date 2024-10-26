import http from "../http/index";
//默认导出的api地址
export default {
  //后台访问类型
    //查询
    select: {
        name: "类目的查询",
        url: '/api/category',
        //async 异步函数 主要解决的问题是 如果后台api在更新或操作数据就会进入异步空间
        // await 等待的意思 等待后台api执行完操作后 返回数据
        // call 是提供的一个方法 用来调用api
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    },
    insert: {
        name: "类目的添加",
        url: '/api/category',
        call: async function (params: any = {}) {
            return await http.post(this.url, params)
        }
    },
    update: {
        name: "类目的修改",
        url: '/api/category',
        call: async function (params: any = {}) {
            return await http.put(this.url, params)
        }
    },
    delete: {
        name: "类目的删除",
        url: '/api/category',
        call: async function (params: any = {}) {
            return await http.delete(this.url, params)
        }
    },
};
