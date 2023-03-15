<template>
  <div style="padding: 10px">
        <div style="margin:10px 0">
          <el-button type="primary" @click="add">新增</el-button>
        </div>
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left:5px" @click="load" >查询</el-button>
    </div>
    <!--    <img alt="Vue logo" src="../assets/logo.png">-->
    <el-table
        :data="tableData" border style="width: 100%">
      <el-table-column
          prop="itemId" label="item_id" sortable>
      </el-table-column>
      <el-table-column

          label="描述"><template #default="scope"><el-button type="primary" style="margin-left:5px" @click="HandleLook(scope.row)" >查看</el-button></template></el-table-column>
      <!--      <el-table-column-->
      <!--          prop="sellPoint"-->
      <!--          label="卖点"></el-table-column>-->
<!--      <el-table-column-->
<!--          prop="price"-->
<!--          label="价格"></el-table-column>-->
<!--      <el-table-column-->
<!--          prop="num"-->
<!--          label="数量"></el-table-column>-->
      <!--      <el-table-column-->
      <!--          prop="barcode"-->
      <!--          label="条形码"></el-table-column>-->
<!--      <el-table-column-->
<!--          prop="image"-->
<!--          label="图片">-->
<!--        <template #default="scope">-->
<!--          <el-image-->
<!--              style="width: 100px; height: 100px"-->
<!--              :src="scope.row.image"-->
<!--              :preview-src-list="[scope.row.image]">-->
<!--          </el-image>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <!--      <el-table-column-->
      <!--          prop="cid"-->
      <!--          label="类目"></el-table-column>-->
      <!--      <el-table-column-->
      <!--          prop="status"-->
      <!--          label="商品状态">-->
      <!--        <template #default="scope">-->

      <!--     <div v-if="scope.row.status===1">正常</div>-->
      <!--       <div v-if="scope.row.status===2">下架</div>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
            <el-table-column
                prop="created"
                label="创建时间"></el-table-column>
            <el-table-column
                prop="updated"
                label="更新时间"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button
                  size="mini"
                  type="danger"
              >删除</el-button>
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
          <div id="div1"></div>
          <el-form-item label="创建时间">
            <el-date-picker
                v-model="form.created"
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false,save()">确 定</el-button>
        </span>
      </el-dialog>


      <el-dialog title="详情" :visible.sync="vis" width="50%">
        <el-card>
          <div v-html="detail.itemDesc" style="min-height: 100px"></div>
        </el-card>
      </el-dialog>
    </div>
  </div>
</template>

import request from "../utils/request";
<script>
import request from "@/utils/request";
import E from  'wangeditor';
let editor;
export default {
  name: "Description",
  data() {
    return {
      form:{},
      dialogVisible:false,
      dialogVisible1:false,
      total:10,
      search:'',
      currentPage: 1,
      tableData: [],
      pageSize:10,
      size:'',
      vis:false,
      detail:{},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/description", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true;
      this.form = {};
      if(!editor){
        this.$nextTick(()=>
        { editor = new E('#div1')
          editor.txt.clear()
          editor.config.uploadImgServer='http://localhost:9090/files/editor/upload'
          editor.config.uploadFileName="file"
          editor.create()})}
      if(editor){
        editor.txt.clear()
      }
    },
    save() {
      this.form.itemDesc=editor.txt.html();
      if (this.form.itemId) {
        request.put("/description", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "修改成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "修改失败"
            })
          }
        })
        this.load();
        this.dialogVisible = false;
      } else {
        request.post("/description", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "新增失败"
            })
          }

        })
        this.load();
        this.dialogVisible = false;
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      if(!editor){
        this.$nextTick(()=>{
          editor=new E('#div1')
          editor.config.uploadImgServer='http://localhost:9090/files/editor/upload'
          editor.config.uploadFileName="file"
          editor.create()
          editor.txt.html(row.itemDesc)
            }
        )
      }
      if(editor) editor.txt.html(row.content)
    },
    handleDelete(id) {
      request.delete("/description/" + id).then(res => {
        console.log(id)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "删除失败"
          })
        }
      })
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.load()
      console.log(`当前页: ${val}`);
    },
    filesUploadSuccess(res) {
      console.log(res)
      this.form.image = res.data
    },
    HandleLook(value){
      // this.form = JSON.parse(JSON.stringify(value))
      // console.log(this.form)
      // this.dialogVisible1 = true
      this.detail=value
      this.vis=true
    }


  }
}
</script>