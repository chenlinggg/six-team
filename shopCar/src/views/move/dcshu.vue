<template>
    <div class="comparison_box">
        <div class="comparison_left">
            <div class="search">
                <el-input placeholder="请选择" v-model="input3">
                    <i slot="suffix" class="el-input__icon el-icon-date"></i>
                </el-input>
            </div>
            <div class="system_list">
                <ul>
                    <li v-for="(item, index) in systemList" :key="index" @click="selectSystem(index)"
                        :class="{ li_active: index == selectVal }">
                        <span>左侧{{ item.name }}</span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="comparison_right" @scroll="handleScroll">
            <!-- 顶部固定列 -->
            <div class="comparison_top">
                <div class="comparison_top_title">
                    <el-button icon="el-icon-plus" circle></el-button>
                    <div class="text">添加对比</div>
                </div>
                <div class="comparison_top_list">
                    <draggable :list="navList" class="drag-area" @end="onDragEnd">
                        <div class="list_details" v-for="(item, index) in navList" :key="index"
                            :class="{ list_details_active: index == 0 && isDing }">
                            <div class="fixed_tag">
                                <span class="tag_close" v-if="index == 0 && isDing" @click="cancelDing(index)">
                                    已钉住
                                </span>
                                <span class="tag" v-else @click="dingHandle(index)">
                                    钉在左侧
                                </span>
                                <i @click="moveItemUp(index)" class="icon-ym icon-ym-nav-close"
                                    v-if="navList.length > 1"></i>
                            </div>
                            <div class="craft_name">{{ item.name }}{{ index }}</div>
                        </div>
                    </draggable>
                </div>
            </div>
            <div class="table_list" ref="scrollContainer">
                <div class="panel-item anchor-item" :ref="'anchor-' + index" v-for="(item, index) in systemList"
                    :key="index">
                    <div class="panel-item-title">
                        <div class="title">内容区域{{ item.name }}</div>
                        <div class="panel-item_list">
                            <div class="list_item" v-for="(list, i) in navList" :key="i"
                                :class="{ list_item_active: i == 0 && isDing }"></div>
                        </div>
                    </div>
                    <div class="nav_box" v-for="(main, j) in item.list" :key="j">
                        <div class="nav_left">
                            <div>{{ main.title }}标题</div>
                        </div>
                        <div class="nav_right">
                            <div class="nav_list" v-for="(nav, idx) in navList" :key="idx"
                                :class="{ nav_list_active: idx == 0 && isDing }">
                                {{ nav[main.id] }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 返回顶部 -->
            <div class="back_top" v-if="showBackTop">
                <el-button type="primary" icon="icon-ym icon-ym-report-icon-align-top" circle
                    @click="scrollToTop"></el-button>
            </div>
        </div>
    </div>
</template>
 
<script>
import draggable from 'vuedraggable'
export default {
    components: {
        draggable
    },
    data() {
        return {
            input3: '',
            systemList: [
                {
                    name: '系统1',
                    list: [
                        {
                            id: 'xitong1',
                            title: '转动轴1'
                        },

                        {
                            id: 'xitong2',
                            title: '转动轴2'
                        },
                        {
                            id: 'xitong3',
                            title: '转动轴3'
                        },
                        {
                            id: 'xitong4',
                            title: '转动轴4'
                        },
                        {
                            id: 'xitong5',
                            title: '转动轴5'
                        },
                        {
                            id: 'xitong6',
                            title: '转动轴6'
                        },
                    ]
                },
                { name: '系统2' },
                { name: '系统3' },
                { name: '系统4' },
                { name: '系统5' },
                { name: '系统6' },
                { name: '系统7' },
                { name: '系统8' },
                { name: '系统9' },
                { name: '系统10' },
            ],
            selectVal: 0,
            showBackTop: false, // 是否显示返回顶部
            navList: [
                {
                    name: '工艺参数1',
                    xitong1: '10',
                    xitong2: '65',
                    xitong3: '1',
                    xitong4: '30',
                    xitong6: '40',
                    xitong5: '50',
                },
                {
                    name: '工艺参数2',
                    xitong1: '50',
                    xitong2: '6555',
                    xitong3: '15',
                    xitong4: '545430',
                    xitong6: '440',
                    xitong5: '540',
                },
                {
                    name: '工艺参数3',
                    xitong1: '550',
                    xitong2: '655',
                    xitong3: '1545',
                    xitong4: '5430',
                    xitong6: '44',
                    xitong5: '50',
                },
                {
                    name: '工艺参数4',
                    xitong1: '550',
                    xitong2: '655',
                    xitong3: '1545',
                    xitong4: '5430',
                    xitong6: '44',
                    xitong5: '50',
                },
                {},
                {},
                {},
                {},
                {}
            ],
            isDing: false, // 是否钉住
        }
    },
    methods: {
        dingHandle(index) {
            this.isDing = true
            this.navList.splice(0, 0, this.navList.splice(index, 1)[0]);
        },
        cancelDing() {
            this.isDing = false
        },
        onDragEnd(event) {
            // 拖拽结束后的处理逻辑
        },
        /**
         * 向前移动
         */
        moveUp(index) {
            if (index > 0) {
                this.navList.splice(index - 1, 0, this.navList.splice(index, 1)[0]);
            }
        },
        /**
         * 向后移动
         */
        moveDown(index) {
            if (index < this.navList.length - 1) {
                this.navList.splice(index + 1, 0, this.navList.splice(index, 1)[0]);
            }
        },
        selectSystem(val) {
            this.$nextTick(() => {
                this.selectVal = val
                let selector = 'anchor-' + val
                this.$refs[selector][0].scrollIntoView({ behavior: 'instant', block: 'end', inline: "nearest" })
            })
        },
        // 滚动监听器
        handleScroll(e) {
            const { systemList } = this
            let contents = document.querySelectorAll('.anchor-item')
            let nowTop = e.target.scrollTop // 滚动条目前的滚动距离
            if (nowTop > 100) {
                this.showBackTop = true
            } else {
                this.showBackTop = false
            }
            let firstTop = contents[0].offsetTop // 第一个章节的顶部位置
            let idx = null
            try { // forEach循环只能通过抛出异常的方式终止
                systemList.forEach((section, index) => {
                    let contentTop = contents[index].offsetTop // 第index个章节的顶部位置
                    let scrollDistance = contentTop - firstTop // 从第一个章节到第index个章节的滚动距离
                    if (nowTop < scrollDistance) {
                        // 滚动条已滚动的距离少于第index个章节需要的滚动距离，说明当前正文处于第(index-1)的章节
                        idx = Math.max(0, index - 1)
                        throw new Error(`find section:${this.selectVal}`)
                    }
                })
            } catch (e) { }
            // 滚动到最后一章（该章节高度超过正文容器）时，滚动条的滚动距离超过所有章节需要的滚动距离
            this.selectVal = idx === null ? systemList.length - 1 : idx
            if (e.srcElement.scrollTop + e.srcElement.offsetHeight === e.srcElement.scrollHeight) {
                // 滚动条触底，处于最后一章（消除该章节高度低于正文容器时始终定位在倒数第二章的情况）
                this.selectVal = systemList.length - 1
            }
        },
        scrollToTop() {
            this.$nextTick(() => {
                this.selectSystem(0)
            });
        },
        moveItemUp(index) {
            console.log(55555)
            this.navList.splice(index, 1);
            console.log(this.navList)

        },
    },
    mounted() {

    }
}
</script>
 
<style scoped>
.comparison_box {
    display: flex;
    width: 100%;
    height: 100%;

    .comparison_left {
        width: 140px;
        height: 100%;
        background: #fff;
        border-radius: 5px;
        flex-shrink: 0;
        padding: 8px 0;
        overflow: hidden;

        .search {
            padding: 0 8px;
            margin-bottom: 12px;
        }

        .system_list {
            height: calc(100% - 40px);

            ul {
                height: 100%;
                overflow: auto;
                padding: 0 8px;

                li {
                    list-style: none;
                    padding: 8px 6px 8px 12px;
                    font-size: 14px;
                    border-radius: 6px;
                    cursor: pointer;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    white-space: nowrap;
                    background: #fff;

                    &:hover {
                        background: #b3d8ff;
                    }
                }

                .li_active {
                    color: #fff;
                    background: #409eff;
                }
            }
        }
    }

    .comparison_right {
        background: #fff;
        width: calc(100% - 140px);
        margin-left: 12px;
        overflow: auto;

        .comparison_top {
            height: 100px;
            position: sticky;
            top: 0;
            z-index: 10;
            background: #fff;
            display: flex;

            .comparison_top_title {
                width: 160px;
                height: 100%;
                border: 1px solid #e5e7eb;
                position: sticky;
                left: 0;
                flex-shrink: 0;
                background: #fff;
                z-index: 8;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;

                .text {
                    font-size: 12px;
                    margin-top: 4px;
                }
            }

            .comparison_top_list {
                display: flex;

                .drag-area {
                    display: flex;
                }

                .list_details {
                    width: 160px;
                    height: 100%;
                    border: 1px solid #e5e7eb;
                    border-left: none;
                    background: #fff;
                    position: relative;

                    .fixed_tag {
                        display: flex;
                        justify-content: space-between;

                        .tag {
                            background-color: rgba(255, 204, 50, 0.12);
                            border-bottom-right-radius: 24px;
                            padding: 4px 20px 4px 6px;
                            font-size: 12px;
                            color: rgba(179, 125, 18);

                            cursor: pointer;
                        }

                        .tag_close {
                            background-color: #f2f4fa;
                            border-bottom-right-radius: 24px;
                            padding: 4px 20px 4px 6px;
                            color: rgba(31, 33, 41);

                            font-size: 12px;
                            cursor: pointer;
                        }

                        >i {
                            font-size: 12px;
                            color: #c9cbd5;
                            margin-right: 4px;
                            margin-top: 4px;
                            cursor: pointer;
                        }
                    }

                    .craft_name {
                        margin-top: 16px;
                        text-align: center;
                        font-size: 14px;
                        font-weight: 600;
                    }
                }

                .list_details_active {
                    position: sticky;
                    left: 160px;
                    background: #fff;
                    z-index: 6;
                }
            }
        }

        .table_list {
            height: 100%;

            .panel-item {
                display: flex;
                flex-direction: column;

                .panel-item-title {
                    height: 36px;
                    display: flex;
                    align-items: center;

                    .title {
                        width: 160px;
                        height: 100%;
                        line-height: 36px;
                        position: sticky;
                        left: 0;
                        font-size: 14px;
                        font-weight: 600;
                        background: rgba(242, 244, 250);
                        padding-left: 16px;
                        border-bottom: 1px solid #e5e7eb;
                        flex-shrink: 0;
                    }

                    .panel-item_list {
                        height: 100%;
                        display: flex;

                        .list_item {
                            width: 160px;
                            height: 100%;
                            background: rgba(242, 244, 250);
                            border-bottom: 1px solid #e5e7eb;
                        }

                        .list_item_active {
                            position: sticky;
                            left: 160px;
                            background: rgba(242, 244, 250);
                        }
                    }
                }

                .nav_box {
                    display: flex;
                    height: 32px;

                    .nav_left {
                        height: 100%;
                        position: sticky;
                        left: 0;
                        border: 1px solid #e5e7eb;
                        border-top: none;
                        width: 160px;
                        line-height: 32px;
                        padding-left: 16px;
                        color: rgba(96, 99, 112);
                        flex-shrink: 0;
                        z-index: 8;
                        background: #ffff;
                        font-size: 14px;
                    }

                    .nav_right {
                        display: flex;
                        height: 100%;

                        .nav_list {
                            width: 160px;
                            height: 100%;
                            flex-shrink: 0;
                            border: 1px solid #e5e7eb;
                            border-top: none;
                            border-left: none;
                            line-height: 32px;
                            padding-left: 16px;
                            font-size: 14px;
                        }

                        .nav_list_active {
                            position: sticky;
                            left: 160px;
                            background: #fff;
                        }
                    }
                }
            }
        }

        .back_top {
            position: fixed;
            bottom: 50px;
            right: 60px;
        }
    }
}
</style>