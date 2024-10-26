<template>

登录页面
</template>
<script setup lang="ts">
//使用jwt 
import {jwtDecode} from "jwt-decode"

const token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0ZWwiOiIxMjM0KioqKjciLCJpZCI6MSwiZXhwIjoxNzI4ODc0MDAwOTYzfQ.RNXhE4A7Ny7055SEcVEvVjIJJ6HcGb8io3auPXIIpQo"
const code=jwtDecode(token)
console.log(code);

</script>