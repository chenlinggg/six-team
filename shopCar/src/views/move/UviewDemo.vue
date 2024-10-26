<template>
    <div>
        <div v-if="data.length > 0" class="table-box">
          <!-- 固定列 表头 -->
          <div class="fixed-columns fixed-backgound1">
            <div v-for="(item) in columns" :key="item.title">
              <div>{{ item.title }}</div>
            </div>
          </div>
          <!-- 原本的表格+数据 -->
          <div class="value-box">
            <div v-for="(item, index) in columns" :key="index">
              <!-- 表头 -->
              <div class="content-box">
                <div class="header-title header-title-backgound1">
                  <div class="name">{{ item.title }}</div>
                </div>
                <!-- 数据 -->
                <div class="value-text" v-for="(item2, index2) in item.value" :key="index2">
                  {{ item2 }}
                </div>
              </div>
            </div>
          </div>
        </div>
        <van-empty v-else description="暂无数据" />
      </div>


    <!-- <div class="home-nav-container">
    <ul
      class="home-nav nav-container"
      :style="floorStyle"
      @scroll="getLeft"
    >
      <li
        v-for="(item, index) in floors"
        :key="index"
      >
        <img class="nav-icon" :src="item.headImg" alt="" />
      </li>
    </ul>
    <div v-if="slideShow" class="slide">
      <div class="slide-bar">
        <div class="slide-show" :style="`width:${slideWidth}px;margin-left:${slideLeft <= 1 ? 0 : slideLeft}px`"></div>
      </div>
    </div>
    </div> -->
</template>
 
<script>
export default {
    data() {
        return {
            columns: [],
            data: []
        }
    },
    methods: {
        init() {
            this.columns = [{
                title: '新区域',
                dataIndex: 'qy'
            }, {
                title: '大区',
                dataIndex: 'dq'
            }, {
                title: '季度净利润区间开始',
                dataIndex: 'profit_bonus_start'
            }, {
                title: '开始是否含',
                dataIndex: 'contain_start'
            }, {
                title: '季度净利润区间结束',
                dataIndex: 'profit_bonus_end'
            }, {
                title: '结束是否含',
                dataIndex: 'contain_end'
            }, {
                title: '该区间季度利润绩效',
                dataIndex: 'profit_bonus_commission'
            }]
            this.data = [
                {
                    'id': 1,
                    'dq': '一大区',
                    'qy': '区域一',
                    'profit_bonus_start': '10',
                    'contain_start': '是',
                    'profit_bonus_end': '200',
                    'contain_end': '否',
                    'profit_bonus_commission': '600'
                },
                {
                    'id': 2,
                    'dq': '二大区',
                    'qy': '区域二',
                    'profit_bonus_start': '200',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 3,
                    'dq': '三大区',
                    'qy': '区域三',
                    'profit_bonus_start': '300',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                },
                {
                    'id': 4,
                    'dq': '四大区',
                    'qy': '区域四',
                    'profit_bonus_start': '400',
                    'contain_start': '是',
                    'profit_bonus_end': '500',
                    'contain_end': '否',
                    'profit_bonus_commission': '100'
                }
            ]
            //处理数据，把处理好的数据放进表头数组里
            this.columns.forEach(item => {
                item.value = []
                for (const i in this.data) {
                    for (const j in this.data[i]) {
                        if (item.dataIndex === j) {
                            item.value.push(this.data[i][j])
                        }
                    }
                }
            })
        }
    },
    mounted() {
        this.init()
    }
}
// export default {
//     data() {
//         return {
//             slideWidth: 0, // 滑块宽
//             slideLeft: 0, // 滑块位置
//             slideShow: false, // 是否显示滑块
//             slideRatio: 0, // 滑块比例
//             lengthRatio: 0, // 列表长度与屏幕长度比例（每个Item占20%屏幕长度）
//         };
//     },
//     // created() {
//     //     this.getRatio();
//     // },
//     mounted() {
//         window.addEventListener('scroll', this.getLeft);
//     },
//     methods: {
//         getRatio() {
//             if (this.floor.rooms.length <= 10) {
//                 this.slideShow = false;
//             } else {
//                 this.lengthRatio = Math.floor(this.floor.rooms.length / 2) / 5; // 列表长度与屏幕长度比例（每个Item占20%屏幕长度）
//                 this.slideRatio = 40 / (375 * this.lengthRatio); // 滚动列表长度与滑条长度比例
//                 this.slideWidth = 40 / this.lengthRatio; // 当前显示蓝色滑条的长度(保留两位小数)
//                 this.slideShow = true;
//             }
//         },
//         // slideLeft动态变化
//         getLeft(e) {
//             this.slideLeft = e.target.scrollLeft * this.slideRatio;
//         },
//     },
// };
</script>
 
<style>
.fixed-columns {
  width: 105px;
  position: sticky;
  z-index: 9999;
  top: 0;
  left: 0;
  >div {
    height: 50px;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #333333;
    font-size: 12px;
    font-weight: 500;
    border-bottom: 1px solid #E1E1E1;
    border-right: 1px solid #E1E1E1;
    border-left: 1px solid #E1E1E1;
  }
}
/* 固定的表头背景颜色*/
.fixed-backgound1 {
  background-color: #FDF9EE;
}
.fixed-backgound2 {
  background-color: #FFF3F3;
}
.value-box {
  /*-350px是所有表头加起来的高度，用于固定表头 */
  margin-top: -350px;
}
 
.table-box {
  width: 100%;
  border-radius: 4px;
  border-top: 1px solid #E1E1E1;
  overflow-x: auto;
  position: relative;
  font-family: PingFangSC, PingFang SC;
  .content-box {
    width: 100%;
    height: 50px;
    display: flex;
    .header-title {
      width: 105px;
      height: 50px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-bottom: 1px solid #E1E1E1;
      border-right: 1px solid #E1E1E1;
      border-left: 1px solid #E1E1E1;
      color: #333333;
      font-size: 12px;
      font-weight: 500;
      position: absolute;/*表头固定 */
      left: 0;
      z-index: 999;
    }
    .name {
        text-align: center;
      }
    .value-text {
      min-width: 80px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-bottom: 1px solid #E1E1E1;
      border-right: 1px solid #E1E1E1;
      text-align: center;
      font-size: 12px;
     }
    .value-text:nth-child(2) {
      /*表头固定后，数据内容的第一列需要设置左边距空出表头的宽度*/
      margin-left: 105px;
    }
    /*根据需要改变表头的背景颜色*/ 
    .header-title-backgound1 {
      background-color: #FDF9EE;
    }
    .header-title-backgound2 {
      background-color: #FFF3F3;
    }
  }
}

.home-nav-container {
background-color: #fff;
position: relative;
background-size: 100% 100%;
margin: 0.05rem 0.24rem;
border-radius: 0.2rem;
.home-nav {
  display: flex;
  flex-wrap: wrap;
  &.nav-container {
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    max-height: 3.48rem;
    overflow-x: scroll;
    overflow-y: hidden;
    position: relative;
    &::-webkit-scrollbar {
      display: none;
    }
  }
  li {
    width: 20%;
    text-align: center;
    box-sizing: border-box;
  }
}
.slide{
  height: .08rem;
  background:#fff;
  width:100%;
  padding: 0.04rem 0 0.08rem 0;
}
.slide .slide-bar{
  width: 40px;
  bottom: 2px;
  margin: 0 auto;
  height: .08rem;
  background:#f0f0f0;
  border-radius: .08rem;
}
.slide .slide-bar .slide-show{
  height:100%;
  border-radius: .08rem;
  background-color: #d2d2d2;
}
}
</style>