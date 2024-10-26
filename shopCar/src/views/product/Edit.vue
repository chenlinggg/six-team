<template>
    <el-form ref="formRef" style="max-width: 600px" :model="formDate" :rules="rules" label-width="auto"
        class="demo-ruleForm" status-icon>
        <el-form-item label="商品名称" prop="name">
            <el-input v-model="formDate.name" />
        </el-form-item>
        <el-form-item label="商品标题" prop="subName">
            <el-input v-model="formDate.subName" />
        </el-form-item>
        <el-form-item label="商品图片" prop="img">
            <el-input v-model="formDate.img" />
            <Tuku v-model="formDate.img"></Tuku>
            &nbsp;
            <Tuku v-model="formDate.img1"></Tuku>
            &nbsp;
            <Tuku v-model="formDate.img2"></Tuku>
        </el-form-item>
        <el-form-item label="商品排序" prop="seq">
            <!-- <el-input v-model="formDate.seq" /> -->
            <el-input-number v-model="formDate.seq" :min="1" :max="10" />
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
            <el-input-number v-model="formDate.price" :precision="2" :step="0.1" />
        </el-form-item>
        <el-form-item label="父类目ID" prop="categoryId">
            <el-tree-select v-model="formDate.categoryId" :data="categorydata" check-strictly :render-after-expand="false"
                style="width: 150px" />
        </el-form-item>
        <el-form-item label="商品简介" prop="brief">
            <!-- <textarea v-model="formDate.brief" :rows="5"></textarea> -->
            <!-- 富文本编辑框 -->
            <editors v-model="formDate.brief" :height="100" />
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
import { CategoryApi, ProductApi } from "../../api";
import { ElMessage } from 'element-plus'
import Editors from "../../components/Editors.vue";
import { useRouter, useRoute } from 'vue-router'
const route = useRoute()
const router = useRouter()

const formDate = reactive({
    id: 0,
    name: '',
    subName: '',
    img: '',
    img1: '',
    img2: '',
    seq: 0,
    categoryId: 0,
    price: 0,
    brief: '',

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
    formDate.id = Number(route.query.id)
    CallCategoryApi()
    //回调
    CallProductList()
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
const CallProductList = () => {
    ProductApi.select.call({ id: formDate.id }).then((res: any) => {
        console.log(res[0].name);
        let result = res[0]
        //赋值操作
        formDate.name = result.name
        formDate.subName = result.subName
        let imgArray = res[0].img.split(',')
        
        formDate.img = imgArray[0]
        if(imgArray[1] !== undefined && imgArray[1] !== ''){
            formDate.img1 = imgArray[1]
        }
        if(imgArray[2] !== undefined && imgArray[2] !== ''){
            formDate.img2 = imgArray[2]
        }

       // formDate.img = result.img
        formDate.seq = res[0].seq
        formDate.price = result.price
        formDate.categoryId = result.categoryId
        formDate.brief = result.brief

    })

}

//定义提交方法
const formsubmit = () => {
    ProductApi.update.call({
        id: formDate.id, name: formDate.name, subName: formDate.subName, img: formDate.img,
        seq: formDate.seq, price: formDate.price, brief: formDate.brief, categoryId: formDate.categoryId
    }).then((res: any) => {
        console.log(res);
        ElMessage.success("修改成功")
        router.push({ name: 'product-list' })

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