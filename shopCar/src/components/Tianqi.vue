<template>
    <!--头部搜索框设计-->
    <div class="search-box">
        <div class="search">
            <div class="search-input">
                <input type="text" placeholder="请输入搜索内容" v-model="city">
                <button class="iconfont icon-sousuo" @click="search"><el-icon>
                        <Search />
                    </el-icon></button>
            </div>
        </div>
        <div class="weather">
            <div class="weather-left">
                <div>{{ city2 }} : 天气</div>
                <div class="weather-summary">
                    <img :src="`/src/assets/images/${weather.icon}.svg`" alt="" width="120px" height="100px">

                </div>
                <!--温度-->
                <span style="font-size: 35px; margin-top: 10px;">{{ weather.temp }}°</span>
                <span style="color: red;font-size: 25px;">{{ weather.text }}</span>

            </div>
            <div class="weather-bottom">
                <el-row class="row-bg" justify="space-around" style="padding-top: 20px; margin-left: 50px;">
                    <el-col :span="6">
                        <span>{{ weather.windScale }}级</span><br>
                        <span>{{ weather.windDir }}</span>
                    </el-col>
                    <el-col :span="6">
                        <span>{{ weather.humidity }}%</span><br>
                        <span>相对湿度</span>
                    </el-col>
                    <el-col :span="8">
                        <span>{{ weather.windSpeed }}级</span><br>
                        <span>风速(公里/小时)</span>
                    </el-col>
                </el-row>
                <el-row class="row-bg" style="padding-top: 20px; margin-left: 50px; padding-bottom: 20px;">
                    <el-col :span="5">
                        <span>{{ weather.feelsLike }}</span><br>
                        <span>体感温度</span>
                    </el-col>
                    <el-col :span="5">
                        <span>{{ weather.wind360 }}</span><br>
                        <span>风向360角度</span>
                    </el-col>
                    <el-col :span="5">
                        <span>{{ weather.pressure }}</span><br>
                        <span>大气压强</span>
                    </el-col>
                    <el-col :span="5">
                        <span>{{ weather.vis }}</span><br>
                        <span>能见度</span>
                    </el-col>
                    <el-col :span="4">
                        <span>{{ weather.cloud }}%</span><br>
                        <span>云量</span>
                    </el-col>
                </el-row>
            </div>
        </div>


    </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios';

const city = ref('')
const city2 = ref('郑州')
const weather = ref({} as any)
const location=ref('101180101')

onMounted(() => {
    
   weathers()
})


const weathers = async () => { 
    let res = await axios.get("https://devapi.qweather.com/v7/weather/now", {
        params: {
            location: location.value,
            key: '94f66cc8c6c04c1f8d9b66db1079d662'
        }
    })
    console.log(res.data.now);
    weather.value = res.data.now
}


//搜索函数
const search = async () => {
    console.log(city)
    city2.value = city.value
//     获取城市名称
    let locationres = await axios.get("https://geoapi.qweather.com/v2/city/lookup", {
        params: {
            key: '94f66cc8c6c04c1f8d9b66db1079d662',
            location: city.value  //input框输入的城市名称
        }
    })
    console.log(locationres)
  
    //获取城市id的编号后，再获取天气
    location.value = locationres.data.location[0].id
    
    let res = await axios.get("https://devapi.qweather.com/v7/weather/now", {
        params: {
            location: location.value == undefined ? '101180101' : location.value,
            key: '94f66cc8c6c04c1f8d9b66db1079d662'
        }
    })
    console.log(res);
    weather.value = res.data.now
    // console.log(weather.value);

}

</script>
<style scoped>
.search-box {
    width: 100%;
    margin: 0 auto;
}

.search {
    background-color: rgb(136, 139, 139);
    padding: 10px 0;
}

.search-input {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 150px;
    width: 100%;
    height: 30px;

}

.search-input input {
    flex: 1 1 0;
    margin: 0 20px;
    line-height: 30px;
    border: 0;
    border-radius: 20px;
}

.iconfont {
    font-weight: 700;
    color: rgb(240, 29, 29);
    width: 40px;
    height: 30px;
    margin-right: 15px;
    border: 0;
    cursor: pointer;
}

.weather {
    width: 100%;
    height: 380px;
    background-color: rgb(179, 178, 177);

    .weather-left {
        font-size: 18px;
        font-weight: 700;

        .weather-summary {
            display: flex;
            justify-content: center;

            width: 120px;
            height: 70px;
            margin: 20px auto;

        }

        span {
            display: flex;
            justify-content: center;
            align-items: center;
            /* margin-left: 50%; */
            font-size: 18px;
        }
    }
}

.weather-bottom {
    width: 96%;
    
    margin-left: 10px;
    border-radius: 10px;
    background-color: rgb(100, 98, 98);
}



</style>
<style>

.el-calendar-table .el-calendar-day{
    height: 64px !important;
}
.el-calendar__body{
    height: 360px !important;
    }
</style>