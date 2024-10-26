<template>
    <van-nav-bar :title="productData.name" left-text="返回" left-arrow @click-left="onClickLeft" />
    <!--头部图片信息-->
    <van-image width="100%" height="240" :src="imgArray[0]" @click="showImg"/>
    <!--商品标题-->
    <!-- 设置垂直间距 -->
    <van-row>
        <van-col span="5" class="title">
            <img src="" alt="">
        {{ productData.name }}    
        </van-col>
        <van-col span="8" style="color:#979aa8 ; font-size: 14px; text-align: center; line-height: 24px;">{{productData.subName}}</van-col>
        <van-col span="11" class="title">
            <div class="rank-info_root__26NtN">
                <a href="#" target="_blank" class="rank-info_rank-item__1JtqD">
                    <span class="rank-info_rank-name__CTpYp">紧凑轿车热门榜</span>
                    <span class="rank-info_rank-number__18pPe">No.2</span>
                    <van-icon name="arrow" />
                </a>

            </div>
        </van-col>
    </van-row>
    <!--价格-->
    <!-- <van-card price="2.00" style="width: 100%; height: 20px;"/> -->
    <van-tag color="#979aa8" plain size="large">经销商报价 </van-tag>
    <van-tag color="#ffff">
        <span class="prices">15.63-18.63 / 万</span>
    </van-tag><br>
    <van-tag color="#979aa8" plain size="large">厂商指导价 </van-tag>
    <van-tag color="#ffff">
        <span style="color: black; font-size: 14px;">
            <!-- <span style="color:red; font-size: 15px;">￥</span> -->
            {{ productData.price}}-{{productData.price}} / 万</span>
    </van-tag>

    <!--分享 收藏 降价通知-->
    <div>
        <van-row justify="center" gutter="20" style="margin-top: 10px; text-align: center;">
            <van-col span="6">
                <div class="imgicon">分享</div>
                <div></div>
                <!-- <van-cell title="分享" @click="showShare = true" style="float:right;"/>
                <van-share-sheet v-model:show="showShare" title="立即分享给好友" :options="options" /> -->
            <!-- <img src="https://img11.360buyimg.com/img/jfs/t1/185923/19/38125/325/64fad71dFeef526f9/f11f072077880807.png"
                        alt="" width="20px" height="20px">
            <span style="padding-left: 7px;">分享</span> -->
            </van-col>
            <van-col span="6">
                <div class="imgicon2">收藏</div>
                 <!-- <img src="https://img10.360buyimg.com/img/jfs/t1/102190/6/45012/397/64fad71dF756823e2/d39f976bcb0a7e1f.png"
                            alt="" width="20px" height="20px"/>
                <span>收藏</span> -->
            </van-col>
            <van-col span="7">
                 <!-- <img src="https://img11.360buyimg.com/img/jfs/t1/143406/40/38936/332/64fad71dF85fe799a/be862fbe6f0efb0d.png"
                            alt="" width="20px" height="20px"/>
                <span>降价通知</span> -->
                <div class="imgicon3">降价通知</div>
            </van-col>
        </van-row>
    </div>
    <hr style="color: rgb(208, 206, 206); margin-top: 20px;">
    <div class="brief" style="margin-bottom: 10px;">
        <!-- 后台传递的数据包含html标签 -->
        <div v-html="productData.brief" style="margin-bottom: 10px;"></div>
    </div>

    <!-- 
     <van-share-sheet
          v-model:show="showShare"
          title="分享"
          :options="options"
        /> -->

    <van-action-bar>
        <template #default="scope">
            <van-action-bar-icon icon="chat-o" text="客服" />
            <van-action-bar-icon icon="shop-o" text="店铺" />
            <van-action-bar-icon icon="cart-o" text="购物车" :to="{ name: 'movecrat' }"  @click="onShopping()" />
            <van-action-bar-icon icon="star" text="收藏" color="#ff5000" />
            <van-action-bar-button color="#be99ff" type="warning" text="加入购物车" :to="{ name: 'movecrat' }"  />
            <van-action-bar-button color="#7232dd" type="danger" text="立即购买" />
        </template>
    </van-action-bar>
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import { ProductApi } from '../../api';
import { showImagePreview } from 'vant';
import { useRoute } from 'vue-router';
const route = useRoute();

const onClickLeft = () => history.back();
const productData = reactive({
    name: '',
    subName: '',
    img: '',
    price: 0,
    brief:''
})

const showShare = ref(false);
const options = [
    [
        { name: '微信', icon: 'wechat' },
        { name: '朋友圈', icon: 'wechat-moments' },
        { name: '微博', icon: 'weibo' },
        { name: 'QQ', icon: 'qq' },
    ],
    [
        { name: '复制链接', icon: 'link' },
        { name: '分享海报', icon: 'poster' },
        { name: '二维码', icon: 'qrcode' },
        { name: '小程序码', icon: 'weapp-qrcode' },
    ],
];
const id=ref(0)
onMounted(() => {
    //route.query.id && console.log(route.query.id) 直接打印数据的
    id.value=Number(route.query.id === undefined ? 0 : route.query.id );
    console.log(id.value);
    
    CallProductApi();
})

//头部图片预览

const showImg=()=>{
    showImagePreview({
        images: imgArray.value,
        startPosition: 0,
         closeable: true,
      })
}


const imgArray=ref([])
const CallProductApi = () => {
    ProductApi.select.call({id:id.value}).then((res:any) => {
        console.log(res[0]);
        productData.name = res[0].name;
        productData.subName = res[0].subName;
        productData.img = res[0].img;
        productData.price = res[0].price;
        productData.brief = res[0].brief;
        //处理多长图片回显
        imgArray.value=res[0].img.split(',')
    })
}

const onShopping = () => {
    console.log('购物车');
    
}

</script>
<style scoped>
.title{
    color: black;
    font-size: 18px;
    font-weight: 700;
}
.van-card {
    width: 100%;
    height: 20px;
}

.prices {
    display: flex;
    align-items: center;
    font-size: 20px;
    color: red;
    font-weight: 700;
    margin-top: 6px;
}

.van-tag {
    margin-top: 10px;
}

/*
借助懂车的css样式 仅供参考
*/
/** a标签的样式 */
.rank-info_root__26NtN .rank-info_rank-item__1JtqD {
    position: relative;
    display: inline-flex;
    align-items: center;
    flex-wrap: nowrap;
    border-radius: 4px;
    background-color: #e6e8f2;
    border: 1px solid #e6e8f2;
    padding: 3px 8px 3px 12px;
    --tw-text-opacity: 1;
    color: rgba(31, 33, 41, var(--tw-text-opacity));
}

/** 文本的样式 */
.rank-info_root__26NtN .rank-info_rank-item__1JtqD .rank-info_rank-name__CTpYp {
    font-size: 12px;
    line-height: 18px;
}

/** NO.2的样式 */
.rank-info_root__26NtN .rank-info_rank-item__1JtqD .rank-info_rank-number__18pPe {
    font-family: D-DIN Exp;
    font-weight: 700;
    font-size: 16px;
    line-height: 19px;
    margin: 0 4px 0 8px;
    background-image: linear-gradient(0deg, transparent, transparent 2.5px, #897025 0, #ffcc32 6.5px, transparent 0, transparent);
}

/**分享 收藏 */
.imgicon{
    background: url("https://img11.360buyimg.com/img/jfs/t1/185923/19/38125/325/64fad71dFeef526f9/f11f072077880807.png") no-repeat left;
    background-size: 20px  20px;
    color: #c7c8cb;
}   
.imgicon2{
    background: url("https://img10.360buyimg.com/img/jfs/t1/102190/6/45012/397/64fad71dF756823e2/d39f976bcb0a7e1f.png") no-repeat left;
    background-size: 20px  20px;
color: #c7c8cb;
}   
.imgicon3{
    background: url("https://img11.360buyimg.com/img/jfs/t1/143406/40/38936/332/64fad71dF85fe799a/be862fbe6f0efb0d.png") no-repeat left;
    background-size: 20px  20px;
    padding-left: 10px;
    color: #c7c8cb;
}   
</style>
