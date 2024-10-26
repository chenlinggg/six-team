<template>
  <!-- 如果useframe== false 全屏展示 -->
    <div v-if="$route.meta.useFrame != undefined && $route.meta.useFrame == false">
     <RouterView />
    </div>
    <div v-if="$route.meta.useFrame == undefined || $route.meta.useFrame == true">
   <el-container>
              <el-header>
                <el-row class="row-bg" justify="space-between">
            <el-col :span="6" class="logo">
            <img src="./assets/logo.jpg" alt="">
            </el-col>
            <el-col :span="6">
            <!-- 汽车智能管控平台 -->
            </el-col>
            <el-col :span="6">
           <el-dropdown @command="outclick">
            <span class="el-dropdown-link">
             <el-icon size="19px" color="#fff" class="icon"><UserFilled /></el-icon> | 萧炎
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>账号设置</el-dropdown-item>
                <el-dropdown-item>切换账号</el-dropdown-item>
                <el-dropdown-item>关于我们</el-dropdown-item>
                <el-dropdown-item :icon="CircleCheck" command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
            </el-col>
          </el-row>
              </el-header>
              <el-container>
                <el-aside width="auto">
                    <Menu></Menu>
                </el-aside>
                <el-main> <RouterView /> </el-main>
              </el-container>
            </el-container>
           
  </div>

</template>
<script setup lang="ts">
import Menu from "@/components/Menu.vue";
import { ArrowDown, CircleCheck, } from '@element-plus/icons-vue'
import { useRouter} from "vue-router"
const router = useRouter()

const outclick = (command:any) => { 
  if (command === "logout") { 
    console.log("退出登录");
    router.push({name:"user-login"})
  }
}



</script>
<style scoped>
*{
    margin: 0;
    padding: 0;
}
.el-header{
    background-color:#c6e2ff;
    color:#fff;
    line-height: 60px;
    padding-left: 0px;
    padding-right: 0px;
}
.row-bg img{
    height: 60px;
    width: 200px;

}
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
.el-dropdown{
    float: right;
     line-height: 60px;
     color: rgb(91, 90, 90);
     font-weight: 700;
}
.el-dropdown-link .icon{
    padding-top: 10px;
}
</style>
