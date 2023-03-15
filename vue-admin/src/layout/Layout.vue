<template>

<!--    <el-container style="height: 800px">-->
<!--      <el-aside width="200px"><Aside/></el-aside>-->
<!--      <el-container>-->
<!--        <el-header><Header/></el-header>-->
<!--        <el-main> <router-view style="flex:1"/></el-main>-->
<!--      </el-container>-->
<!--    </el-container>-->
    <div class="home-page">
      <el-container ref="homePage">
        <el-aside width="200px" style="background-color: rgb(84, 92, 100);">
          <div class="top-logo">
<!--            <img src="../assets/logo.png" alt="">-->
          </div>
          <Aside/>
        </el-aside>
        <el-container>
          <el-header style="text-align: right; font-size: 12px">
            <Header/>
          </el-header>

          <el-main>
            <router-view style="flex:1"/>
          </el-main>
        </el-container>
      </el-container>
    </div>

</template>

<script>
import Header from "@/components/Header.vue";
import Aside from "@/components/Aside.vue";

export default {
  name: "Layout",
  components:{
    Header,
    Aside
  },
  data() {

    return {
      clientHeight:'',
    }
  },
  watch: {
    // 如果 `clientHeight` 发生改变，这个函数就会运行
    clientHeight: function () {
      this.changeFixed(this.clientHeight)
    }
  },
  computed: {},
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    changeFixed(clientHeight){ //动态修改样式
      // console.log(clientHeight);
      this.$refs.homePage.$el.style.height = clientHeight-20+'px';
    },
  },
  created() {},
  mounted() {
    // 获取浏览器可视区域高度
    this.clientHeight =   `${document.documentElement.clientHeight}`
    //console.log(self.clientHeight);
    window.onresize = function temp() {
      this.clientHeight = `${document.documentElement.clientHeight}`;
    };
  }
};
</script>
<style>
.el-aside::-webkit-scrollbar {
  display: none;
}

</style>
