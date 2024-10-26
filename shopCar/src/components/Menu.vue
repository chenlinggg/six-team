<template>
  <!--官网提供 需要配置css样式-->
    <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
      <el-radio-button :value="false"><el-icon><Expand /></el-icon></el-radio-button>
      <el-radio-button :value="true"><el-icon><Fold /></el-icon></el-radio-button>
    </el-radio-group> -->
  <!-- {{ $router.options.routes }} -->
  <el-menu active-text-color="#ffd04b" background-color="#8ab7e4" class="el-menu-vertical-demo"
    :default-active="$route.path" text-color="#fff" :router="true" :collapse="isCollapse">
    <!--开关按钮-->
    <el-row class="row-bg" justify="center">
      <el-col :span="5">
      <component :is="topIcon" class="icon2" @click="openOrClose">
      </component>
      </el-col>
    </el-row>

    <template v-for="item in $router.options.routes">
      <el-menu-item :index="item.path" v-if="item.children == undefined && item.meta ? item.meta.show == true : ''">
        <!-- <el-icon><House /></el-icon> -->
        <component :is="item.meta ? item.meta.icon:''" class="icon"></component>
        <span>{{ item.meta ? item.meta.title : '' }}</span>
      </el-menu-item>

      <el-sub-menu :index="item.path" v-if="item.children != undefined && item.meta ? item.meta.show == true : ''">
        <template #title>
          <component :is="item.meta ?item.meta.icon:''" class="icon"></component>
          <span>{{ item.meta ? item.meta.title : '' }}</span>
        </template>
        <el-menu-item-group>
          <template v-for="child in item.children">
            <el-menu-item :index="child.path" v-if="child.meta ? child.meta.show == true : ''">
              <component :is="child.meta?child.meta.icon:''" class="icon"></component>
              <span>{{ child.meta ? child.meta.title : '' }}</span>
            </el-menu-item>

          </template>
        </el-menu-item-group>
      </el-sub-menu>

    </template>
  </el-menu>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const isCollapse = ref(false)
//动态切换图标
const topIcon = ref('Fold')
const openOrClose = () => { 
 topIcon.value = isCollapse.value == true ? 'Fold' : 'Expand'
  isCollapse.value = !isCollapse.value
}

//官网提供
// const handleOpen = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath)
// }
// const handleClose = (key: string, keyPath: string[]) => {
//   console.log(key, keyPath)
// }
</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: calc(100vh - 47px);

}
.el-menu--collapse{
  min-height: calc(100vh - 47px);
}

.icon {
  width: 25px;
  height: 25px;
  margin-right: 5px;
}
.icon2{
  width: 19px;
  height: 19px;
  color: aliceblue;
  font-weight: 700;
  padding-top: 5px;
}
</style>