<template>
    
    <!-- 我是首页
    <Tuku></Tuku>
    <Editors></Editors> -->
    <!-- <el-table :data="transposedData" border stripe style="width: 100%">
        <el-table-column
          v-for="item in transposedData"
          :key="item.label"
          :label="item.label"
          align="center">
          <template #default="scope">
            <template v-if="scope.row.label === '参数'">
              <span>{{ scope.row.values.join(' / ') }}</span>
            </template>
            <template v-else>
              <span>{{ scope.row.values.join(' / ') }}</span>
            </template>
          </template>
        </el-table-column>
      </el-table> -->

</template>
<script setup lang="ts">
// import Tuku from '../components/Tuku.vue';
// import Editors from '../components/Editors.vue';

// import { useCarComparison } from '../utils/useCarComparison';
// import { carData } from '../utils/carData';

// const { transposedData } = useCarComparison(carData);




// import { onMounted } from "vue";
// //按需引用
// import {CategoryApi} from "../api/index"


// onMounted(() => { 
//     //引入并使用api接口
//     CategoryApi.select.call().then((res: any) => { 
//         console.log(res);
        
//     })
// })
</script>
<style scoped>
.el-table {
  width: 100%;
  margin: 20px;
}

.el-table__header-wrapper,
.el-table__body-wrapper {
  overflow: hidden;
}
</style>