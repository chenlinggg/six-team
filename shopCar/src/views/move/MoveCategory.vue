<template>
    <van-nav-bar title="汽车分类" left-text="返回" left-arrow @click-left="onClickLeft" />
    <!-- activeId:右侧选中项的 id    leftactiveIndex:左侧选中项的索引 -->
    <van-tree-select 
    v-model:active-id="rightactiveId" 
    v-model:main-active-index="leftactiveIndex" 
    :items="categoryData" 
    height="850px">

    <!--如果你想要显示多张图片 需要把插槽拿来-->
    <template #content>
    <!-- 下方路由跳转 做备用 -->
         <!-- :to="({ name: 'moveBrand', params:{id:item.id} })" -->
        <van-grid :column-num="3" icon-size="50px" >
          <van-grid-item v-for="item in categoryData[leftactiveIndex]?.children" 
          :to="{name:'movelist',query:{categoryId:item.id}}"
          :key="item.id" 
          :icon="item.img" 
          :text="item.text" />
        </van-grid>

    </template>

    

</van-tree-select>





    <van-tabbar v-model="active">
        <van-tabbar-item icon="home-o">首页</van-tabbar-item>
        <van-tabbar-item icon="cluster-o" :to="({ name: 'moveCategory' })">选车</van-tabbar-item>
        <van-tabbar-item icon="shop-collect-o">二手车</van-tabbar-item>
        <van-tabbar-item icon="manager-o">我的</van-tabbar-item>
    </van-tabbar>
</template>
<script setup lang="ts">
import { onMounted, ref } from "vue";
import {CategoryApi} from "../../api";
const onClickLeft = () => history.back();
const active = ref(0);

const rightactiveId = ref(1);
const leftactiveIndex = ref(0);
const categoryData = ref([
    {
        text: '浙江',
        children: [
            { text: '杭州', id: 1 ,img:'https://m.360buyimg.com/babel/jfs/t1/215072/20/30644/9019/646da7a1F63613a8c/a5b2428860e14917.jpg'},
            { text: '温州', id: 2 ,img:'https://m.360buyimg.com/babel/jfs/t1/100342/15/41391/10528/646dad13F717b6cd9/a301d60c251994d8.jpg' },
            { text: '宁波', id: 3, disabled: true },
        ],
    }
   
])

const categoryData2 = ref([
    { id: 1, name: '手机数码', img: '', parentId: 0 },
    { id: 2, name: '华为', img: '', parentId: 1 },
    { id: 3, name: '小米', img: '', parentId: 1 },
    { id: 4, name: '家用电器', img: '', parentId: 0 },
    { id: 5, name: '电饭煲', img: '', parentId: 4 },
    { id: 6, name: '微波炉', img: '', parentId: 4 },
])

onMounted(() => {
   
    //console.log(categoryData2);
    CallCategoryApi()
})

//分类的api接口
const CallCategoryApi = () => { 
    CategoryApi.select.call().then((res: any) => { 
       //替换数据库的name为text
        res.forEach((item: any) => {
            item.text = item.name
        })
        //使用高阶函数处理数组 并判断出parentId是否为0
        let categoryData3 = res.reduce((newArray: any, currValue: any) => {

            //res.filter((item: any) => {return item.parentId === currValue.id})
            currValue.children = res.filter((item: any) => item.parentId === currValue.id)
            if (currValue.parentId === 0) {
                newArray.push(currValue)
            }
            return newArray
        }, [])
        console.log(categoryData3);
        categoryData.value = categoryData3


    })


}



</script>
<style scoped></style>