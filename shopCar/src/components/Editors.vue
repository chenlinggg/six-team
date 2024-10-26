<template>
    <div style="border: 1px solid #ccc">
        <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" />
        <Editor style="height: 100px; overflow-y: hidden;" v-model="props.modelValue" @onCreated="handleCreated"  @onChange="onChnange"/>
    </div>
    <!-- <button @click="getHtml">点击获取</button> -->
</template>
<script setup lang="ts">
import '@wangeditor/editor/dist/css/style.css' // 引入 css

import { shallowRef, onMounted, watch } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import { uploadApi } from '../api';


// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
//const valueHtml = ref('<p>hello</p>')

onMounted(() => {
    // setTimeout(() => {
    //     valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
    // }, 1500)
})

let props = defineProps({
    modelValue: {
        type: String,
        default: ''
    }
})

let emit = defineEmits(['update:modelValue'])

const handleCreated = (editor: any) => {
    
    editorRef.value = editor // 记录 editor 实例，重要！
    // console.log("赋值前。。。。");
    // editor.setHtml(props.modelValue)
    // console.log("赋值后。。。。");
   editor.getConfig().MENU_CONF['uploadImage'] = {
        // 自定义上传
        async customUpload(file: File, insertFn: any) {  // TS 语法
          let reader=new FileReader()
            // file 即选中的文件
           reader.readAsDataURL(file)
            //加载api接口
           reader.onload = () => { 
                // 自己实现上传，并得到图片 url alt href
               uploadApi.upload.call({name:file.name,base64:reader.result}).then((res: any) => { 
                console.log(res);

                  // 最后插入图片
                    insertFn(res, "", "")
               })
           }
          
          
        }
    }
    //视频上传
    editor.getConfig().MENU_CONF['uploadVideo'] = {
        // 自定义上传
        async customUpload(file: File, insertFn: any) {  // TS 语法
          let reader=new FileReader()
            // file 即选中的文件
           reader.readAsDataURL(file)
            //加载api接口
           reader.onload = () => { 
                // 自己实现上传，并得到图片 url alt href
               uploadApi.upload.call({name:file.name,base64:reader.result}).then((res: any) => { 
                console.log(res);

                  // 最后插入图片
                    insertFn(res, "", "")
               })
           }
          
          
        }
    }
    
}
//解决编辑回显问题（简介）
watch(() => props.modelValue, (newValue: any, oldValue: any) => { 
    console.log("我感应到了。。。");
     editorRef.value.setHtml(newValue)
})


//Ai给的
// watch(props.modelValue, (val) => {
//     if (editorRef.value) {
//         // console.log("赋值前。。。。");
//         editorRef.value.setHtml(val)
//         // console.log("赋值后。。。。");
//     }
//  })


//定义函数 当发生改变的时候
const onChnange = (editor: any) => {
   //console.log(editor.getHtml());
    emit('update:modelValue', editor.getHtml())
}

// const getHtml = () => {
//     console.log(editorRef.value.getHtml())
//      console.log(editorRef.value.getText())
// }
</script>