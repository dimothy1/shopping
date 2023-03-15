<template>
  <div style="padding: 10px">
<!--    <div style="margin:10px 0">-->
<!--      <el-button type="primary" @click="add">新增</el-button>-->
<!--    </div>-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left:5px" @click="load" >查询</el-button>
    </div>
    <!--    <img alt="Vue logo" src="../assets/logo.png">-->
    <el-table
        :data="tableData" border style="width: 100%">
      <el-table-column
          prop="id" label="ID" sortable>
      </el-table-column>
      <el-table-column
          prop="title"
          label="标题"></el-table-column>
<!--      <el-table-column-->
<!--          prop="sellPoint"-->
<!--          label="卖点"></el-table-column>-->
      <el-table-column
          prop="price"
          label="价格"></el-table-column>
      <el-table-column
          prop="num"
          label="数量"></el-table-column>
<!--      <el-table-column-->
<!--          prop="barcode"-->
<!--          label="条形码"></el-table-column>-->
      <el-table-column
          prop="image"
          label="图片">
        <template #default="scope">
          <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.image"
              :preview-src-list="[scope.row.image]">
          </el-image>
        </template>
      </el-table-column>
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
<!--      <el-table-column-->
<!--          prop="created"-->
<!--          label="创建时间"></el-table-column>-->
<!--      <el-table-column-->
<!--          prop="updated"-->
<!--          label="更新时间"></el-table-column>-->
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
              size="mini"
              @click="handleLook(scope.row)">详情</el-button>
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
          <el-form-item label="标题">
            <el-input v-model="form.title" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="卖点">
            <el-input v-model="form.sell_point" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="form.price" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="数量">
            <el-input v-model="form.num" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="条形码">
            <el-input v-model="form.barcode" style="width: 80%"></el-input>
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
            <el-input v-model="form.cid" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="商品状态">
            <el-select v-model="form.status" placeholder="请选择商品状态" style="width: 80%">
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




        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false,save()">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog title="详情" :visible.sync="dialogVisible1" width="50%">
        <el-descriptions title="商品信息" class="margin-top" direction="vertical" :column="3" :size="size" border>
          <el-descriptions-item ><template slot="label">
            商品名称
          </template>{{ form.title }}</el-descriptions-item>
          <el-descriptions-item label="商品卖点">{{ form.sell_point }}</el-descriptions-item>
          <el-descriptions-item label="商品价格">{{ form.price }}</el-descriptions-item>
          <el-descriptions-item label="库存数量">
            {{ form.num }}
          </el-descriptions-item>
          <el-descriptions-item label="条形码">
            {{ form.barcode }}
          </el-descriptions-item>
          <el-descriptions-item label="商品照片">
            <el-image
                style="width: 100px; height: 100px"
                :src="form.image"
                :preview-src-list="[form.image]">
            </el-image>
          </el-descriptions-item>
          <el-descriptions-item label="所属类目">
            {{ form.cid }}
          </el-descriptions-item>
          <el-descriptions-item label="出售状态">{{ form.status }}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ form.created }}</el-descriptions-item>
          <el-descriptions-item label="更新时间">{{ form.updated }}</el-descriptions-item>
        </el-descriptions>
      </el-dialog>
    </div>
  </div>
</template>

import request from "../utils/request";
<script>
import request from "@/utils/request";

export default {
  name: "Commodity",
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
      size:''
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/commodity", {
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
      this.form = {}
    },
    save() {
      if (this.form.id) {
        if (this.form.status == "正常") this.form.status = 1
        else this.form.status = 2
        request.put("/commodity", this.form).then(res => {
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
        request.post("/commodity", this.form).then(res => {
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
      if (this.form.status == 1) this.form.status = "正常"
      else this.form.status = "下架"
      this.dialogVisible = true
    },
    handleDelete(id) {
      request.delete("/commodity/" + id).then(res => {
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
    handleLook(value){
      this.form = JSON.parse(JSON.stringify(value))
      if (this.form.status == 1) this.form.status = "正常"
      else this.form.status = "下架"
      this.dialogVisible1 = true
    }


  }
}
</script>