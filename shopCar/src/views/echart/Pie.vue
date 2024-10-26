<template>
    <el-row>
        <el-col :span="12"><div id="Piemain" style="width: 100%; height: 300px;"></div></el-col>
        <el-col :span="12">
            <div id="Rosemain" style="width: 100%; height: 300px;"></div>
            </el-col>
      </el-row>

<div id="barmain" style="width: 100%; height: 240px;"></div>
</template>
<script setup lang="ts">
import { onMounted } from 'vue'
import {CateProApi,ScreenCatePro} from '@/api/index'
import * as echarts from 'echarts';

onMounted(() => { 
   //测试数据是否能拿到
    // ScreenCatePro.cateProCount.call().then((Barres:any) => { 
    //     console.log(Barres);
    //     initBar()
    // })
    // initRose()
    barcateProd()
    callCateProdApi()
})

//基础饼图  前后交互 不写前端的时候
const initCart = (data:any) => { 
    //拿到dom的id选择器
    let cartDom = document.getElementById('Piemain');
    //初始化echarts实例
    let myChart = echarts.init(cartDom);
    //绘制图表
    let option = {
        title: {
            text: '汽车类目数量统计图',
            subtext: '统计维度：汽车品牌',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: '汽车品牌',
                type: 'pie',
                radius: '50%',
                data: data,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }
    option && myChart.setOption(option);
}
//基础柱状图  第二种方式
const initBar = (data:any) => { 
    let barcart = echarts.init(document.getElementById('barmain'), 'dark');
    console.log(data);
    //使用js函数 对数组做处理
    console.log(Array.from(data, ({name, Ptotal })=> Ptotal));
    

    
    let option = {
        title: {
            text: '品牌类目下汽车类型的统计',
            subtext: '实时数据统计',
            left: 'center'
        },
          toolbox: {
            show: true,
            feature: {
                //数据视图
                dataView: { readOnly: false },
                //视图切换
                magicType: { type: ['line', 'bar'] },
                //刷新
                restore: { show: true },
                //保存为图片
                saveAsImage: { show: true }
            }
        },
        xAxis: {
            type: 'category',
            data: Array.from(data, ({ name, Ptotal }) => name),
            axisLabel: {
                interval: 0, //坐标轴上文字旋转 标签的显示间隔 默认是不重叠
                rotate: 35, //文字旋转的角度 默认是0 （-90 到 90） 正数向左 负数 向右
                
                textStyle: {
                    color: 'red',
                    fontSize: 13

                }
            }
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: Array.from(data, ({ name, Ptotal }) => Ptotal),
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.1)'
                }
            }
        ]
    }
    barcart.setOption(option);
}

//玫瑰图
const initRose = (data: any) => { 
    //第二种方式 采用函数定义的模式
    const formRoseData = data.map(function (obj: any) { 
        return {
            name: obj.name,
            value: obj.Ptotal
        }
    })

    echarts.init(document.getElementById('Rosemain')).setOption({
        legend: {
            top: 'left',

        },
         tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        series: [
            {
                name: '汽车品牌统计',
                type: 'pie',
                radius: [30, 100],
                center: ['50%', '50%'],
                roseType: 'area',
                itemStyle: {
                    borderRadius: 8
                },
                data: formRoseData
                // data: data.map(({ name, Ptotal }) => ({name,value:Ptotal}))
            }
        ]
    });

}
const barcateProd = () => { 
    ScreenCatePro.cateProCount.call().then((res:any) => { 
       // console.log(res)
        initBar(res)
        initRose(res)
    })

}


const callCateProdApi = () => { 
    CateProApi.select.call().then((res:any) => { 
        console.log(res)
        initCart(res)
    })
}
</script>
<style scoped>

</style>