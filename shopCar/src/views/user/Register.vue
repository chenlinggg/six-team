<template>
    <div class="conents">
        <el-row class="row-bg" justify="center">
            <el-col :span="18">
                <el-card class="box-card" style="max-width: 480px" >
                    <template #header>
                        <div class="card-header">
                            <span>会员注册</span>
                            <div style="font-size: 13px;">如果已账户请<el-button class="btn" text style="font-size: 17px;" @click="onlogin">去登录</el-button></div>
                        </div>
                    </template>
                    <!--提交form表单-->
                    <el-form ref="formRef" style="max-width: 600px" :model="formData" :rules="rules" label-width="auto"
                        class="demo-ruleForm" status-icon>
                        <el-form-item label="用户名" prop="username">
                            <el-input v-model="formData.username" />
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input v-model="formData.password" type="password" />
                        </el-form-item>
                        <el-form-item label="确认密码" prop="password2">
                            <el-input v-model="formData.password2" type="password" />
                        </el-form-item>
                        <el-form-item label="手机号" prop="tel">
                        <el-input v-model="formData.tel" />
                        </el-form-item>
                        <el-form-item label="邮箱" prop="email">
                            <el-input v-model="formData.email" />
                            </el-form-item>
                        <el-form-item class="btnclick">
                            <el-button type="primary" @click="submitForm(formRef)">
                                注册
                            </el-button>
                            <el-button @click="resetForm(formRef)">重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
        </el-row>

    </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue';
import { Md5 } from 'ts-md5'
import { ElMessage } from 'element-plus'
import { UserApi } from '@/api'
import { useRouter } from 'vue-router'
const router = useRouter()

const formRef = ref()
const formData = reactive({
    username: '',
    password: '',
    password2: '',
    tel: '',
    email: ''
})
// const md5pwd = Md5.hashStr("123456").toUpperCase();
// console.log("加密后的数字=",md5pwd);



//正则验证
const rules = reactive({
    username: [
        { required: true, message: '用户名必填', trigger: 'blur' },
        { min: 2, max: 15, message: '用户名必需是 2 to 15字母', trigger: 'blur' },
    ],
     tel: [
        { required: true, message: '手机号必填', trigger: 'blur' },
        { pattern:/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-9])\d{8}$/, message: '必须输入正确手机号', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '密码必填', trigger: 'blur' },
        { pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/, message: '必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间', trigger: 'blur' },
    ],
    password2: [
        { required: true, message: '确认密码必填', trigger: 'blur' },
        { pattern: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/, message: '必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间', trigger: 'blur' },
    ],
     email: [
        { required: true, message: '邮箱必填', trigger: 'blur' },
        { pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, message: '必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间', trigger: 'blur' },
    ],
})

const formSubmit = () => {
    if (formData.password != formData.password2) { 
        ElMessage.error('两次密码输入不一致')
    }
    let tel =formData.tel
    let username = formData.username
    let email = formData.email
    //md5加密
    const md5pwd = Md5.hashStr(formData.password).toUpperCase();
    UserApi.register.call({ tel, username, email, password: md5pwd }).then((res: any) => {
        if (res == 1) { 
            ElMessage.success(res.msg)
            router.push({name:'user-login'})
        }
        
    })
}

const submitForm = async (formEl: any) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
        if (valid) {
            formSubmit()
            console.log('submit!')
        } else {
            console.log('error submit!', fields)
        }
    })
}

const resetForm = (formEl: any) => {
    if (!formEl) return
    formEl.resetFields()
}

const onlogin = () => {
    router.push({name:'user-login'})
}
</script>
<style scoped>
.conents{
    width: 100vw;
    height: 100vh;
    background-image: url("@/assets/register.jpg");
    /* 让背景图平铺屏幕并且不重复*/
    background-repeat: no-repeat;

    /* 让背景图覆盖整个屏幕*/
    background-size: cover;
    background-position: center;
}
.card-header{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.card-header span{
    font-size: 25px;
    font-weight: 700;
}
el-button{
    font-size: 20px;
}

.box-card{
    margin: 0 auto;
    margin-top: 15vh;
}
.btnclick{
   margin-left: 150px;
}
.btnclick button{
    margin-right: 40px;
}
</style>