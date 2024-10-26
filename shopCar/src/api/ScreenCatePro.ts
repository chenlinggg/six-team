import http from "../http/index";
//默认导出的api地址
export default {
    //查询所有类目总数
    cateProCount: {
        name: "柱状图数据类目下商品数量统计",
        url: '/api/barcatepro',
        call: async function (params: any = {}) {
            return await http.get(this.url, params)
        }
    }




}
