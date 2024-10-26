<template>
    <el-form ref="formRef" style="max-width: 600px" :model="formDate" :rules="rules" label-width="auto"
        class="demo-ruleForm" status-icon>
        <el-form-item label="类目名称" prop="name">
            <el-input v-model="formDate.name" />
        </el-form-item>
        <el-form-item label="类目图片" prop="img">
            <!-- <el-input v-model="formDate.img" /> -->
            <Tuku v-model="formDate.img"></Tuku>
        </el-form-item>
        <el-form-item label="类目排序" prop="seq">
            <!-- <el-input v-model="formDate.seq" /> -->
            <el-input-number v-model="formDate.seq" :min="1" :max="10" />
        </el-form-item>
        <el-form-item label="父类目ID" prop="parentId">
            <el-tree-select v-model="formDate.parentId" :data="categorydata" check-strictly :render-after-expand="false"
                style="width: 150px" />
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm(formRef)">
                提交
            </el-button>
            <el-button @click="resetForm(formRef)">重置</el-button>
        </el-form-item>
    </el-form>
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from "vue"
import Tuku from "../../components/Tuku.vue";
import { CategoryApi } from "../../api";
import { ElMessage } from 'element-plus'
import { useRouter, useRoute } from 'vue-router'
const route = useRoute()
const router = useRouter()

const formDate = reactive({
    id:0,
    name: '',
    img: '',
    seq: 0,
    parentId: 0

})
const formRef = ref()

const rules = reactive({
    name: [
        { required: true, message: '类目名称必填项', trigger: 'blur' },
        { min: 2, max: 15, message: '类目名称不能小于 2 大于 15', trigger: 'blur' },
    ],
})
const categorydata = ref([
    {
        value: 0,
        label: '一级目录',

    },
])

onMounted(() => {
    console.log(route.query.id);
    formDate.id=Number(route.query.id) 
    CallCategoryApi()
    //回调
    CallCategoryList()
})

const CallCategoryApi = () => {
    CategoryApi.select.call().then((res: any) => {
       // console.log(res);
        res.forEach((item: any) => {
            item.value = item.id
            item.label = item.name
        });

        const categoryData2 = res.reduce((hezi: any, currentValue: any) => {
            currentValue.children = res.filter((item: any) => {
                return item.parentId === currentValue.id
            })
            if (currentValue.parentId === 0) {
                hezi.push(currentValue)
            }
            return hezi;
        }, [])
       // console.log(categoryData2);
        categorydata.value = categoryData2
    })

}

//构建回显数据的函数
const CallCategoryList = () => { 
    CategoryApi.select.call({ id: formDate.id }).then((res: any) => {
        console.log(res[0].name);
        let result = res[0]
        //赋值操作
        formDate.name = result.name
        formDate.img = res[0].img
        formDate.seq = res[0].seq
        formDate.parentId = res[0].parentId
    })

}

//定义提交方法
const formsubmit = () => {
    CategoryApi.update.call({
       id:formDate.id, name: formDate.name, img: formDate.img,
        seq: formDate.seq, parentId: formDate.parentId
    }).then((res: any) => {
        console.log(res);
        ElMessage.success("修改成功")
        router.push({ name: 'category-list' })
        //刷新列表
        CallCategoryApi();
    })
}

const submitForm = async (formEl: any) => {
    if (!formEl) return
    await formEl.validate((valid: any, fields: any) => {
        if (valid) {
            console.log('submit!')
            formsubmit()
        } else {
            console.log('error submit!', fields)
        }
    })
}

const resetForm = (formEl: any) => {
    if (!formEl) return
    formEl.resetFields()
}
</script>