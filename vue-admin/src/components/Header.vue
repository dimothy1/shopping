<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <Breadcrumb style="margin-top: 20px"/>
    <div style="flex:1"></div>
    <div style="width: 100px">
      <el-dropdown>
  <span class="el-dropdown-link">
    {{ nickname }}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
        <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click.native="$router.push('/')">退出系统</el-dropdown-item>
        </el-dropdown-menu></template>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import Breadcrumb from "@/components/Breadcrumb.vue";

export default {
  name: "Header",
  components: {Breadcrumb},
  data(){
    return{
      nickname: "请登录"
    }
  },
  created() {
    if(sessionStorage.getItem('user')) this.nickname=JSON.parse(sessionStorage.getItem('user')).username
  }
}
</script>

<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>