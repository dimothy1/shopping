<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left:5px" @click="load" >查询</el-button>
    </div>
    <!--    <img alt="Vue logo" src="../assets/logo.png">-->
    <el-table
        :data="tableData" border style="width: 100%">
      <el-table-column
          prop="orderId" label="订单编号" sortable>
      </el-table-column>
      <el-table-column
          label="实付金额">
        <template #default="scope">
          {{scope.row.payment.toFixed(2)}}
        </template>
      </el-table-column>
      <el-table-column
          label="邮费">
        <template #default="scope">
          {{scope.row.postFee.toFixed(2)}}
        </template>
      </el-table-column>
      <el-table-column label="支付状态" >
        <template #default="scope">
          {{scope.row.status|statusfilter}}
        </template>

      </el-table-column>
      <el-table-column
          prop="createTime"
          label="订单创建时间"></el-table-column>
      <el-table-column
          prop="paymentTime"
          label="付款时间"></el-table-column>
      <el-table-column
          prop="userId"
          label="用户Id"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
<!--          <el-button-->
<!--              size="mini"-->
<!--              @click="handleEdit(scope.row)">编辑</el-button>-->
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button
                  size="mini"
                  type="danger"
              >删除订单</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10,  20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

      <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.phone" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" style="width: 80%"></el-input>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false,save()">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src



import request from "../utils/request";

export default {
  name: 'Order',
  data() {
    return {
      form:{},
      dialogVisible:false,
      total:10,
      search:'',
      currentPage: 1,
      tableData: [],
      pageSize:10
    }
  },
  created() {
    this.load()
  },
  filters:{
    statusfilter(val){
      if(val===1) return '未付款';
      else return '已支付';
    }
  },
  methods: {
    load(){
      request.get("/order",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
    add(){
      this.dialogVisible=true;
      this.form={}
    },
    save(){
      if(this.form.id){
        request.put("/order",this.form).then(res=>{
          console.log(res)
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
        this.load();
        this.dialogVisible=false;
      } else{
        request.post("/order",this.form).then(res=>{
          console.log(res)
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })}else{
            this.$message({
              type:"error",
              message:"新增失败"
            })
          }

        })
        this.load();
        this.dialogVisible=false;
      }
    },
    handleEdit(row) {
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    handleDelete(id) {
      request.delete("/order/"+id).then(res=>{
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })}else{
          this.$message({
            type:"error",
            message:"删除失败"
          })
        }
      })

      this.load()

    },
    handleSizeChange(pageSize) {
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(val) {
      this.currentPage=val
      this.load()
      console.log(`当前页: ${val}`);
    }
  }
}
</script>