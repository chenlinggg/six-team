<template>
    <!-- <el-button type="primary" style="margin-left: 16px" @click="drawer = true">
        open
      </el-button> -->
     
    <el-avatar shape="square" :size="100" :src="props.modelValue" @click="drawer = true">
        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="">
    </el-avatar>

      <el-drawer
        v-model="drawer"
        title="我的图库"
        direction="ttb"
        size="40%"
      >
    
     <el-upload 
     v-model:file-list="fileList"
      list-type="picture-card" 
      :auto-upload="false"
      :on-change="onChange">
        <el-icon><Plus /></el-icon>

        <template #file="{ file }">
          <div>
            <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
            <span class="el-upload-list__item-actions">
              <span
                class="el-upload-list__item-preview"
                @click="getImgurl(file.url)"
              >
                <el-icon><zoom-in /></el-icon>
              </span>
            </span>
          </div>
        </template>
      </el-upload>
    </el-drawer>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import {uploadApi} from "@/api"

const drawer = ref(false)
const imgurl=ref("")
const fileList = ref<any[]>([
    {
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    },
])

const onChange = (uploadFile: any, uploadFiles: any) => { 
console.log("uploadFile==", uploadFile,"uploadFiles==", uploadFiles);
    let name=uploadFile.name
    console.log(name);
    let file = uploadFile.raw
    let reader = new FileReader()
    reader.readAsDataURL(file)
    //加载 使用onload加载数据提取base64格式的字符串
    reader.onload = () => { 
        console.log(reader.result);
        CallUploadApi(name,reader.result)
    }

}

//钩子函数 
onMounted(() =>
{
        CallUploadList()
    })

const CallUploadList = () => { 
    uploadApi.imglist.call().then((res: any) => { 
        console.log(res);
        fileList.value = res
    })
}

const CallUploadApi = (name:any,base64:any) => { 
    console.log(name,base64);
    uploadApi.upload.call({ name: name, base64: base64 }).then((res:any) => { 
        console.log(res);
        
    })
}

//定义传递的默认类型，单向数据 父---> 子传递
const props = defineProps({
    modelValue: {
        type: String,
        default: ''
    }
})
//定义传递的默认类型，双向数据 父---> 子传递 ---> 父  提前通知父类
const emit=defineEmits(['update:modelValue'])


const getImgurl = (file:any) => {
//    console.log("file==",file);
//     imgurl.value = file  //赋值url地址
   emit('update:modelValue', file)
   drawer.value = false  //关闭抽屉
}
</script>