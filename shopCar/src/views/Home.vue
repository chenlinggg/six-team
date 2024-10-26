<template>
    <el-row :gutter="20" justify="space-between">
      <el-col :span="5">
        <div id="cartNumDiv" class="el-colDiv">
          <div  id="ssv1-main-text" class="nowDiv">实时</div>
          <span class="title">汽车总类目数</span><br>
          <span class="digital">{{ cateproCount.cateCount }}</span><br>
          <span class="last-span">当前分类总记录数</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div id="haveRoomDiv" class="el-colDiv">
            <div  id="ssv2-main-text" class="nowDiv">实时</div>
            <span class="title">今日新增类目数</span><br>
            <span class="digital">{{ cateproCount.catetodayCount }}</span><br>
            <span class="last-span">当前分类总记录数</span>
          </div>
      </el-col>
      <el-col :span="6">
       <div id="repairNum" class="el-colDiv">
              <div  id="ssv3-main-text" class="nowDiv">实时</div>
              <span class="title">汽车总数量</span><br>
              <span class="digital">{{ cateproCount.productCount }}</span><br>
              <span class="last-span">当前汽车总记录数</span>
            </div>
      </el-col>
      <el-col :span="6">
      <div id="emptyRoom" class="el-colDiv">
                <div  id="ssv4-main-text" class="nowDiv">实时</div>
                <span class="title">今日汽车新增数量</span><br>
                <span class="digital">{{ cateproCount.producttodayCount }}</span><br>
                <span class="last-span">当前汽车总记录数</span>
              </div>
      </el-col>
    </el-row>
   <el-row>
      <el-col :span="12"> 
        <!--日历-->
        <el-calendar v-model="value" style="height: 350px;"/>
      </el-col>
      <el-col :span="12">
      <Tianqi></Tianqi>
      </el-col>
    </el-row>
   





</template>
<script setup lang="ts">
import { onMounted, reactive } from "vue"
import {CateProApi} from "../api"
import { ref } from 'vue'
import Tianqi from "../components/Tianqi.vue";
const value = ref(new Date())


const cateproCount = reactive({
  cateCount: 0,
  catetodayCount: 0,
  productCount: 0,
  producttodayCount: 0
 

})

onMounted(() => { 
  CallcateproCountApi()
})

//对接后台api接口
const CallcateproCountApi = () => { 
  //类目总数量
  CateProApi.cateCount.call().then((res:any) => { 
    console.log(res);
    cateproCount.cateCount = Number(res)
  })
//今日新增数量
  CateProApi.cateTodayCount.call().then((res: any) => { 
    cateproCount.catetodayCount = Number(res)
  })

  CateProApi.proCount.call().then((res: any) => { 
    cateproCount.productCount = Number(res)
  })

  //当天
  CateProApi.productToday.call().then((res: any) => { 
    cateproCount.producttodayCount = Number(res)
  })
}



</script>
<style scoped>
.el-colDiv {
 
  max-width: 340px;
  min-width: 200px;
  overflow: hidden;
  height: 85px;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  background-color: #19191a;
  color: #fff;
  padding-left: 10px;
  padding-top: 5px;
  position: relative;
 }
.nowDiv{
    width: 35px;
    height: 20px;
    position: absolute;
    right: 4%;
    font-size: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 4px;
}
.title{
  font-size: 20px;
}
.digital{
  font-size: 30px;
}

.last-span{
  font-size: 13px;
  color:#343435
}

#cartNumDiv{
  background-color: #c5ea4d;
}
#ssv1-main-text{
  background-color: #4b7404;
}
#haveRoomDiv{
  background-color: #f2f545;
}
#ssv2-main-text{
  background-color: #83cb07;
}
#repairNum{
  background-color: #10e0eb;
}
#ssv3-main-text{
  background-color: #07456e;
}
#emptyRoom{
  background-color: #eb102d;
}
#ssv4-main-text{
  background-color: #8b12e1;
}







.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>