<template>
    <!--搜索框-->
    <el-form :inline="true" :model="formData" class="demo-form-inline">
        <el-form-item label="编号" prop="id">
          <el-input v-model="formData.id" placeholder="请输入编号" clearable />
        </el-form-item>
       <el-form-item label="名称" prop="name">
              <el-input v-model="formData.name" placeholder="请输入类目名称" clearable />
            </el-form-item>
            <el-form-item label="批量查询" prop="ids">
              <el-input v-model="formData.ids" placeholder="请输入多个编号已逗号分割" clearable />
            </el-form-item>
     
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>


    <!--数据列表-->
     <el-table :data="productData" style="width: 100%">
        <el-table-column fixed prop="id" label="编号" width="80" />
        <el-table-column prop="name" label="名称" width="100" />
         <el-table-column prop="subName" label="标题" width="120" />
        <el-table-column prop="img" label="图片" width="120" >
            <template #default="scope">
                <!--scope.row.img.split(',')[0] 展示多个图片-->
                <img :src="scope.row.img.split(',')[0]" alt="" width="50" height="50">    
            </template>
        </el-table-column>
        <el-table-column prop="price"  label="价格" width="80"/>
        <el-table-column prop="seq" label="排序" width="90" />
        <el-table-column prop="categoryId" label="父目录ID" width="90" />
         <!-- <el-table-column prop="status" label="状态" width="30" /> -->
         <el-table-column prop="statusX" label="状态" width="90" />
        <el-table-column prop="lastUpdateTime" label="最新时间" width="180" />
        <el-table-column fixed="right" label="操作" min-width="120">
          <template #default="scope">
    <el-button type="danger"  round size="small" v-if="scope.row.status != 1" @click="offlineClick(scope.row,'上线',1)">上线</el-button>
   <el-button type="danger"  round size="small" v-if="scope.row.status != 5" @click="offlineClick(scope.row, '下线', 5)">下线</el-button>
            <el-button type="danger"  round size="small" v-if="scope.row.status != 10" @click="offlineClick(scope.row, '禁售', 10)">禁售</el-button>
   <el-button type="warning" round size="small" @click="handleClick(scope.row)">修改
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
         layout="prev, pager, next"
        :page-size="pageData.pageSize"
        :total="pageData.total"
        @current-change="currentChange"
      />

</template>
<script setup lang="ts">
// import axios from 'axios';

import { onMounted, reactive, ref } from 'vue';
// 1、 引入api接口
import { ProductApi } from '../../api';
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router';
let router = useRouter();
//any 全类型  和var 是一样的
const productData = ref([] as any)
const formData = reactive({
    id: '',
    name: '',
    ids: [],
    categoryId:''
})

//分页的属性
const pageData = reactive({
  total: 20,
  pageSize: 5,// 每页的显示的数据
  pageNum:1,// 当前页码
})

// 3、 页面渲染立即展示数据
// 钩子函数  onMounted 页面渲染完成执行
onMounted(() => { 
  CallProductApi()
})

//2、构建函数 加载api数据
const CallProductApi = () => { 
  let id = formData.id == "" ? undefined : formData.id
  let name = formData.name == "" ? undefined : formData.name
  let ids = formData.ids[0] == "" ? undefined : formData.ids
  let pageNum = pageData.pageNum
  let pageSize = pageData.pageSize

  ProductApi.select.call({
    id: id, name: name, ids: ids,
    pageNum: pageNum,
    pageSize:pageSize
  }).then((res: any) => {
    console.log(res.items)
    console.log(res.total);
    
    productData.value = res.items
    pageData.total =res.total
  })


  
}
//下线
const offlineClick = (row:any,optionName:String,status:Number) => {
   ElMessageBox.confirm(
    '您确认要对 ^V^ '+row.name + optionName + "吗?",
    '警告！',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
      center: true,
    }
  )
     .then(() => {
       ProductApi.patch.call({ id: row.id,status:status }).then((res: any) => { 
        console.log(res)
        ElMessage({
          type: 'success',
          message: '恭喜您'+optionName+'成功，期待再次和您合作',
        })
        //刷新页面后重新加载数据
        CallProductApi()

       })
      
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消操作，非常感谢还能和您继续奋战',
      })
    })
}
// const disableClick = (row:any) => { 
//   ElMessageBox.confirm(
//     '您确认要对 ^V^ ' + row.name + ' ^V^进行禁售吗?',
//     '警告！',
//     {
//       confirmButtonText: '确认',
//       cancelButtonText: '取消',
//       type: 'warning',
//       center: true,
//     }
//   )
//     .then(() => {
//       ProductApi.delete.call({ id: row.id, status: 10 }).then((res: any) => {
//         console.log(res)
//         ElMessage({
//           type: 'success',
//           message: '恭喜您禁售成功，期待再次和您合作',
//         })
//         //刷新页面后重新加载数据
//         CallProductApi()

//       })

//     })
//     .catch(() => {
//       ElMessage({
//         type: 'info',
//         message: '取消操作，非常感谢还能和您继续奋战',
//       })
//     })
// }

//修改
const handleClick = (row: any) => {
  console.log('click')
  //路由跳转 在跳转的过程中把id 传递过去
  router.push({ name: 'product-edit', query: { id: row.id } })
}

const onSubmit = () => {
    CallProductApi()
}
//查找页码的
const currentChange = (num: number) => {
  pageData.pageNum = num
  console.log(`current page: ${num}`)
  CallProductApi()
}
</script>