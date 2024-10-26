<template>
     <van-nav-bar title="汽车列表" left-text="返回" left-arrow @click-left="onClickLeft" />
    <van-list
      v-model:loading="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
    >
      <!-- <van-cell v-for="item in list" :key="item" :title="item" /> -->
      <!--商品列表-->
      <div  v-for="item in productlist" :key="item">
         <!-- :origin-price="item.price + '万'" 营销价格-->
        <van-card
      num="2"
      :price="item.price+'万'"
      :origin-price="item.price + '万'"
      :desc="item.subName"
      :title="item.name"
      :thumb="item.img.split(',')[0]"
      @click="onClick(item.id)"
    >
    <template #tags>
         <van-rate v-model="value" :count="6" />
          &nbsp; <van-tag plain type="primary" style="font-size: 16px;">3.5分</van-tag>
      </template>
      <template #footer>
        <van-button size="small">分享</van-button>
        <van-button size="small"><van-icon name="thumb-circle-o" style="color: red;" size="20px"/></van-button>
      </template>
       
        </van-card>
    <!-- 无论是价格还是图片都可以截取---item.img.split('/')[0] -->
        <hr style="color: aliceblue;">
      </div>
    </van-list>
    <van-back-top />
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from "vue"
import { ProductApi } from "../../api";
import {useRoute,useRouter} from 'vue-router'
const router = useRouter()
const route = useRoute()
const pageData = reactive({
    id: '',
    name: '',
    pageSize: 5,
    pageNum: 1,
    categoryId:0
})

const productlist=ref([] as any)
const value = ref(3.5);
const loading = ref(false);
const finished = ref(false);

onMounted(() => {
    pageData.categoryId = Number(route.query.categoryId)
    console.log(pageData.categoryId);
    
  pageData.name=String(route.query.keyword == undefined ? '' : route.query.keyword)
})

const onLoad = () => {
    CallProductApi()
    //加载状态结束
         loading.value = false;
    // setTimeout(() => {
    //     for (let i = 0; i < 10; i++) {
    //         productlist.value.push(productlist.value.length + 1 + '商品的名称');
    //     }
    //     //加载状态结束
    //     loading.value = false;
    //     //数据如果大于等于40 数据结束
    //     if (productlist.value.length >= 40) {
    //         finished.value = true;
    //     }
    // }, 1000);
};
const CallProductApi = () => { 
    //调用的时候加载要打开的加载状态
    loading.value = true;
    ProductApi.select.call({
        categoryId: pageData.categoryId,
        pageNum: pageData.pageNum,
        pageSize: pageData.pageSize
    }).then((res: any) => { 
        console.log(res);
        //加载状态结束
        loading.value = false;
        if (res.items.length == 0) { 
            finished.value = true;
        } else {
            productlist.value = productlist.value.concat(res.items)
            pageData.pageNum = pageData.pageNum + 1
        }
        
    })
}

const onClick = (id: number) => {
    router.push({
        name: "cardetails", query: {id:id}
    })
    
}

const onClickLeft = () => history.back();
</script>
<style scoped>

.van-card{
    --van-card-background: rgb(225, 224, 224);
    --van-card-price-color:red;
    /* --van-card-price-font-size:22px; */
    --van-card-price-integer-font-size:18px;
    --van-card-origin-price-font-size:15px;
    /* 文字大小 */
    --van-card-font-size:14px;
    --van-card-title-line-height:26px;
}
.van-button{
    --van-button-default-background	: #e1e0e0;
    font-size:15px;
}
</style>
<style>
.van-card__content{
    background-color: rgb(237, 238, 238);
}

</style>