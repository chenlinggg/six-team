<template>
    <van-nav-bar title="购物车" left-text="返回" left-arrow />
    <van-checkbox-group v-model="selectProduct">
        <!--左右滑动的标签-->
          <van-swipe-cell v-for="(item,index) in selectProductList">
        <!---加入布局-->
        <van-row class="goods">
            <van-col span="2" class="goods-left">
                <van-checkbox :name="item"></van-checkbox></van-col>
            <van-col span="22">
                <van-card  
                :price="item.price" 
                :desc="item.name" 
                :title="item.subName" class="goods-card"
                :thumb="item.img">
                    <!--加入插槽-->
                    <template #num>
                        <van-stepper v-model="item.qty" min="1" max="99"  @change="changeQty"/>
                    </template>
                </van-card>
            </van-col>
        </van-row>
    
        <template #right>
            <van-button square text="删除" type="danger" class="delete-button" @click="deleteItem(item, index)"/>
        </template>
     <!--分割符-->
         <hr>
    </van-swipe-cell>
    </van-checkbox-group>
    <!--在最下面填写-->
    <van-empty description="还没有商品！空空如也"  v-if="selectProductList.length === 0" />

    <van-submit-bar :price="totalAmount" button-text="提交订单" @submit="onSubmit" >
     <van-checkbox v-model="selectAll" @click="checkAll">全选</van-checkbox>
    </van-submit-bar>
    <!-- <van-button type="default" @click="getchecked">获取复选框</van-button> -->
</template>
<script setup lang="ts">

import { ref, watch , onMounted } from 'vue'
const value = ref(1);
const selectProduct = ref<{ name: string; subName: string }[]>([])

const totalAmount = ref(0)
const selectProductList = ref([
    {
        name: '商品1号',
        subName: '商品1号',
        price: 1.89,
        qty: 1,
        img: 'https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg',
         checked: true
    },
    {
        name: '商品2号',
        subName: '商品2号',
        price: 2.89,
        qty: 2,
        img: 'https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg'
    }
])
//赋值应用
onMounted(() => {

    selectProduct.value = selectProductList.value.filter((item: any) => item.checked == true)
})
const selectAll = ref(false)

const checkAll = () => {
    if (selectAll.value === true) {
        selectProduct.value = selectProductList.value
    } else {
        selectProduct.value = []
    }
}
//使用计算函数计算
const computeTotalAmount = () => {
    totalAmount.value = 0
    selectProduct.value.forEach((item: any) => {
        totalAmount.value += item.qty * item.price * 100

    })
}

watch(() => selectProduct.value, (newValue, oldValue) => { 
    console.log(newValue);
    // newValue.forEach((item: any) => {
    //     console.log(item);
    //     totalAmount.value += item.qty * item.price * 100
    // })
    computeTotalAmount()
      if (newValue.length === selectProductList.value.length) {
        selectAll.value = true;
    } else {
        selectAll.value = false;
    }
})
const changeQty = () => {
   computeTotalAmount()
}
//删除
const deleteItem = (item: any, index: number) => {
    console.log(item);
    console.log(index);
    //点击删除要把数组里面的数据减少，使用splice 获取idex
    selectProductList.value.splice(index, 1)
    //过滤一下已经删除的id
    selectProduct.value = selectProduct.value.filter((it: any) => it.id != item.id)
    //加入测试 查看是否删除
    computeTotalAmount()
}
const getchecked = () => {
    console.log(selectProduct.value);

}
</script>
<style scoped>
.goods-card {
    margin: 0;
    background-color: #d7d3d3;
}

.delete-button {
    height: 100%;
}

.goods {
    background-color: rgb(211, 207, 207);
}

.goods-left {
    height: 100px;
    display: flex;
    margin-left: 2px
}
</style>
<style>
.van-col--2 {
    flex: 0;
}
</style>