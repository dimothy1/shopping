<template>
  <el-card class="form-container" shadow="never">
  <el-form :model="form" label-width="100px" style="margin-left: 0px;padding: 50px">
    <el-form-item label="标题">
      <el-input v-model="form.title" style="width: 30%"></el-input>
    </el-form-item>
    <el-form-item label="卖点" style="width:36%">
      <el-input type="textarea" v-model="form.sell_point"></el-input>
    </el-form-item>
    <el-form-item label="价格">
      <el-input v-model="form.price" style="width: 30%"></el-input>
    </el-form-item>
    <el-form-item label="数量">
      <el-input v-model="form.num" style="width: 30%"></el-input>
    </el-form-item>
    <el-form-item label="图片">
      <el-upload
          class="upload-demo"
          ref="upload"
          action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess">
        <el-button  type="primary" >点击上传</el-button>
      </el-upload>
    </el-form-item>
    <el-form-item label="类目">
      <el-input v-model="form.cid" style="width: 30%"></el-input>
    </el-form-item>
<!--    <el-form-item label="类目">-->
<!--      <el-select v-model="value"  placeholder="请选择" style="width: 15%" @change="getcat2()">-->
<!--        <el-option-->
<!--            v-for="item in datacat"-->
<!--            :value="item.name"-->
<!--        >-->
<!--          <span style="float: left" >{{ item.name }}</span>-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-select style="width: 15%" v-model="value1" >-->
<!--        <el-option-->
<!--            v-for="item in datacat2"-->
<!--            :value="item.name"-->

<!--        >-->
<!--          <span style="float: left">{{ item.name }}</span>-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-cascader-->
<!--          v-model="datacat"-->
<!--          :options="datacat2">-->
<!--      </el-cascader>-->
<!--    </el-form-item>-->
    <el-form-item label="商品状态">
      <el-select v-model="form.status" placeholder="请选择商品状态" style="width: 30%">
        <el-option label="正常" value="1"></el-option>
        <el-option label="下架" value="2"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="创建时间">
      <el-date-picker
          v-model="form.created"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd">
      </el-date-picker>
    </el-form-item>
    <el-form-item>
    <el-button type="primary" @click="submitForm">立即增加</el-button>
    <el-button @click="resetForm">重置</el-button>
  </el-form-item>

  </el-form></el-card>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "AddCommodity",
  data(){
    return{
      form:{},
      datacat:[],
      datacat2:[],
      value:'请选择',
      value1:'请选择',
      judge:1,
    }
  },
  created() {
    this.form={};
    request.get("/cat/category",{
      params:{
        parentId:0
      }
    }).then(res=>{
      this.datacat=res.data;
    })
  },
  methods:{
    submitForm() {
      request.post("/commodity",this.form).then(res=>{
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"修改成功"
          })
        }else{
          this.$message({
            type:"error",
            message:"修改失败"
          })
        }
      })
    },
    resetForm() {
      this.form={};
    },
    filesUploadSuccess(res) {
      console.log(res)
      this.form.image = res.data
    },
    getcat2(){
      request.get("/cat/category",{
        params:{
          parentId:this.value
        }
      }).then((res)=>{
        this.datacat2=res.data
      })
    }
  }

}
</script>

<style scoped>

</style>