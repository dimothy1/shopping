<template>

        <el-menu
            :default-active="path"
            style="height: 97vh"
            active-text-color="#409EFF"
            background-color="#304156"
            class="el-menu-vertical-demo"
            router
            text-color="#bfcbd9"
            @close="handleClose"
            @open="handleOpen">
          <el-submenu index="1" >
            <template slot="title"><i class="el-icon-goods"></i>商品</template>
            <el-menu-item index="/commodity" ><i class="el-icon-goods"></i>商品管理</el-menu-item>
            <el-menu-item index="/addcommodity" ><i class="el-icon-circle-plus-outline"></i>添加商品</el-menu-item>
            <el-menu-item index="/cat" ><i class="el-icon-document"></i>商品类目</el-menu-item>
            <el-menu-item index="/description" ><i class="el-icon-more"></i>商品描述</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-document-copy"></i>订单</template>
            <el-menu-item index="/order" ><i class="el-icon-s-order"></i>订单管理</el-menu-item>
          </el-submenu>
<!--          <el-submenu index="4">-->
<!--            <template slot="title"><i class="el-icon-postcard"></i>信息</template>-->
<!--            <el-menu-item index="" ><i class="el-icon-notebook-2"></i>信息统计</el-menu-item>-->
<!--          </el-submenu>-->
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-s-custom"></i>用户</template>
            <el-menu-item index="/user" ><i class="el-icon-user-solid"></i>用户列表</el-menu-item>
          </el-submenu>
        </el-menu>

</template>

<script>
export default {
  name: "Aside",
  data(){
    return{
      user:{},
      path:this.$route.path
    }
  },
  created() {
    let userStr=sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>