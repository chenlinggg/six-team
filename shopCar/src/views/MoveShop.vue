<template>
  <div class="container">
    <div class="showview">
      <van-row gutter="10">
        <van-col span="3" class="lefticon">
          <van-icon name="bars" color="#ffefef" size="30px" />
        </van-col>
        <van-col span="21">
          <van-search v-model="keyword" show-action background="#face40" placeholder="请输入搜索关键词" @search="onSearch">
            <template #action>
              <div @click="onClickButton" style="color: aliceblue; font-weight: 500; font-size: 18px;">搜索</div>
            </template>
          </van-search>
        </van-col>
      </van-row>
      <!---轮播图-->
      <van-swipe :autoplay="3000" lazy-render>
        <van-swipe-item v-for="image in images" :key="image">
          <img :src="image.split(',')[0]" height="200px" width="100%" />
        </van-swipe-item>
      </van-swipe>
      <!--通知栏-->
      <van-notice-bar left-icon="volume-o" text="无论我们能活多久，我们能够享受的只有无法分割的此刻，此外别无其他。" />
      <!--图标样式-->
      <div class="Iconmenu">
        <van-swipe class="my-swipe" :touchable="true" indicator-color="white">
          <van-swipe-item>
            <van-grid :column-num="5" icon-size="50px" :border="false">
              <van-grid-item v-for="item in hotProduct" :key="item.title" :icon="item.img" :text="item.title" />
            </van-grid>
          </van-swipe-item>
          <van-swipe-item>
            <van-grid :column-num="5" icon-size="30px">
              <van-grid-item v-for="item in hotProduct" :key="item.title" :icon="item.img" :text="item.title" />
            </van-grid>
          </van-swipe-item>
        </van-swipe>
      </div>
    </div>
    <!--秒杀模块-->

    <div class="ms">
      <div class="lefttext">
        <span>华杉秒杀</span><br>
        <span>优惠多多</span><br>
        <span>限时抢购</span>
      </div>
      <div class="mscontent">
        <van-grid :column-num="4" icon-size="49px" >
          <van-grid-item v-for="value in MSproduct" :key="value" :icon="value.img.split(',')[0]" :text="value.title" style="color: red;"/>
        </van-grid>
      </div>
      <div class="productContent">


        <!--商品的展示-->
        <van-grid :border="false" :column-num="2" :gutter="5">
          <!--猜你喜欢-->
          <van-grid-item>
            <span style="color: #c2bfbf; font-weight:700; margin-bottom: 3px;"><van-icon name="like" style="color: red;"/>猜你喜欢<van-icon name="like" color="red"/></span>
            <div class="cai" v-for="item in Guesslike">
              
              <van-image :src="item.img.split(',')[0]" />
              <span style="color: ; font-weight: 700; margin-top: 4px;">{{ item.name }}</span><br>
             <span style="color: ; font-weight: 700; margin-top: 4px;">{{ item.subName }}</span><br>
              <span style="color: red; font-weight: 700; margin-top: 4px;">￥ {{ item.price }}w</span>
            </div>
          </van-grid-item>
          <van-grid-item v-for="item in productData" :key="item.id">
            <van-image :src="item.img.split(',')[0]" />
            <!--名称和标题-->
            <div class="nameTitle">

              <van-row justify="end">
                <van-col span="6"><van-icon name="vip-card-o" color="#ee0a24" size="30px" margin-top="5px" /></van-col>
                <van-col span="18">{{item.name}}</van-col>
              </van-row>
              <span class="titleCont">{{ item.subName }}</span>
              <div class="price-icon" style="padding-top: 4px; color: red; font-weight: 700; font-size: 20px;">
                <span class="yicon" style="font-size: 14px; color: red;">￥</span>{{item.price}}w
              </div>
              <!-- 两端对齐 -->
              <van-row justify="space-between" style="margin-top: 10px;">
                <van-col span="6">
                  <img src="https://img12.360buyimg.com/img/s48x28_jfs/t1/127567/8/7519/1313/5f165ca9Ea295fca1/b90967cc602f446a.png" alt="" width="30px" height="17px">
                </van-col>
                <van-col span="6"></van-col>
                <van-col span="9" style="background-color: #f2f2f2; border-radius: 10px; text-align: center; font-size: 15px;">看相似</van-col>
              </van-row>

            </div>
          </van-grid-item>


        </van-grid>
      </div>
    </div>



    <van-tabbar v-model="active">
      <van-tabbar-item icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item icon="cluster-o" :to="({name:'moveCategory'})">选车</van-tabbar-item>
      <van-tabbar-item icon="shop-collect-o">二手车</van-tabbar-item>
      <van-tabbar-item icon="manager-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue'

import { ProductApi } from '../api'
import { CategoryApi } from '../api';
import {useRouter} from 'vue-router'
const router=useRouter()

const active = ref(0);

const keyword = ref('');
const onSearch = () => { 
  console.log(keyword.value);
  router.push({name:'movelist', query: { keyword: keyword.value } })
  
}
const onClickButton = () => {
  console.log("搜索按钮被点击了");

}

const images= ref<String[]>([]);

const hotProduct = ref([
  { img: '//img12.360buyimg.com/babel/jfs/t20270715/38278/23/22574/7960/6694edb4F07db03e3/d663cd498321eadc.png', title: '华杉超市' },
  { img: '//img12.360buyimg.com/babel/jfs/t20270715/38278/23/22574/7960/6694edb4F07db03e3/d663cd498321eadc.png', title: '华杉超市' },
  { img: '//img12.360buyimg.com/babel/jfs/t20270715/38278/23/22574/7960/6694edb4F07db03e3/d663cd498321eadc.png', title: '华杉超市' },
  { img: '//img12.360buyimg.com/babel/jfs/t20270715/38278/23/22574/7960/6694edb4F07db03e3/d663cd498321eadc.png', title: '华杉超市' },
  { img: '//img12.360buyimg.com/babel/jfs/t20270715/38278/23/22574/7960/6694edb4F07db03e3/d663cd498321eadc.png', title: '华杉超市' },
])

const MSproduct = ref([])
//商品定义
const productData=ref([] as any)
//猜你喜欢
const Guesslike=ref<any>([])

onMounted(() => {
  CallProductApi();
  CallCategoryApi();
})

const CallProductApi = () => {
  ProductApi.select.call().then((res: any) => {
   // console.log(res[0].img);
    // images.value=res[0].img
    //AI提供的联想语法  能正常使用
    // Array.from(res).forEach((item: any) => {
    //   images.value.push(item.img);
    // })
    //自己构建
   images.value= Array.from(res, ({img,name})=>img).slice(0,6)
    res.forEach((item: any) => {
      item.title = item.price;
    })
    MSproduct.value = res.slice(0, 4)
    productData.value = res
    //猜你喜欢
   //console.log(Math.random() > 0.5 ? Guesslike.value = res : Guesslike.value = res.slice(1, 2));
    const id =Math.floor(Math.random() * 10)+1
    console.log(id);
    
    ProductApi.select.call({ id: id }).then((res: any) => { 
      console.log(res);
      Guesslike.value = res
    })
  })
}
//图标数据交互
const CallCategoryApi = () => { 
  CategoryApi.select.call().then((res: any) => { 
    // res.forEach((item: any) => {
    //   hotProduct.value.push({
    //     img: item.icon,
    //     title: item.name,
    //   });
    // })
    res.forEach((item: any) => { 
      item.title = item.name;
    })
    //只读取父目录的名字
    hotProduct.value = res.filter((item:any)=>item.parentId==0).slice(0, 5)
  })
}


</script>
<style scoped>
.container {
  background-color: rgb(252, 251, 250);
}

.showview {
  height: 410px;
  background: linear-gradient(to bottom, #fcc624 10%, #f4edb6 80%)
}

.lefticon .van-icon {
  margin: 13px;
  margin-left: 15px;
}

/* //菜单标签的构建 */
.Iconmenu {
  width: 97%;
  height: 100px;
  background: #fff;
  border-radius: 15px;
  margin: 6px;

}

.ms {
  width: 97%;
  height: 120px;
  background: #fcc624;
  border-radius: 15px;
  margin: 6px;
}

.lefttext {
  width: 20%;
  display: inline-block;
  font-size: 18px;
  color: #fff;
  padding-top: 20px;
  padding-left: 10px;
}

.mscontent {
  float: right;
  width: 76%;
  height: 110px;
  background-color: #fff;
  margin-top: 5px;
  border-radius: 15px;

}


.productContent {
  margin-top: 30px;
  margin-bottom: 35px;
}

.nameTitle {
  width: 100%;
  margin-left: -30px;
  padding-top: 5px;

}
/** 解决文本标题长的问题 */
 .nameTitle .titleCont{
  margin-left: 3px;
  overflow: hidden; /*文本超出部分隐藏  内容将被裁减以适应边距*/
  text-overflow: ellipsis; /*文本溢出时显示省略号*/
  display: -webkit-box; /*将对象作为弹性伸缩盒子模型显示*/
  -webkit-line-clamp: 2; /*显示的行数*/
  -webkit-box-orient: vertical; /*设置或检索伸缩盒对象的子元素的排列方式*/
 }
</style>
<style>
.van-search__content {
  border-radius: 15px;

}
.mscontent .van-grid-item__text{
  color: red !important;
  font-size: 15px !important;

}
/* .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  } */
</style>

