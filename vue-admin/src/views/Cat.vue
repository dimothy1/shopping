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
          prop="id" label="ID" sortable>
      </el-table-column>
      <el-table-column label="级别" prop="class">
        <template #default="scope">
        {{scope.row.parentId | levelFilter}}</template>
      </el-table-column>
      <el-table-column
          prop="parentId"
          label="父级"></el-table-column>
      <el-table-column
          prop="name"
          label="类目名称"></el-table-column>
      <el-table-column
          prop="sortOrder"
          label="同级次序"></el-table-column>
      <el-table-column label="查看下级">
        <template slot-scope="scope">
          <el-button
              size="mini"
              :disabled="scope.row.parentId | disableNextLevel"
              @click="handleShowNextLevel(scope.row.id)">查看
          </el-button>
          <el-button
              size="mini"
              :disabled="scope.row.parentId | disableLastLevel"
              @click="handleShowLastLevel">返回
          </el-button>
        </template>
      </el-table-column>

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
          <el-form-item label="父级">
            <el-input v-model="form.parentId" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="类目名称">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="同级次序">
            <el-input v-model="form.sortOrder" style="width: 80%"></el-input>
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
  name: 'Cat',
  data() {
    return {
      form:{},
      dialogVisible:false,
      total:10,
      search:'',
      currentPage: 1,
      tableData: [],
      pageSize:10,
      parentId:0,
    }
  },
  created() {
    this.load()
  },
  filters: {
    levelFilter(value) {
      if (value === 0) {
        return '一级';
      } else  {
        return '二级';
      }
    },
    disableNextLevel(value) {
      if (value === 0) {
        return false;
      } else {
        return true;
      }
    },
    disableLastLevel(value) {
      if (value === 0) {
        return true;
      } else {
        return false;
      }
    },
  },
    methods: {
      load() {
        request.get("/cat", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search,
            parentId:this.parentId,
          }
        }).then(res => {
          console.log(res)
          this.tableData = res.data.records
          this.total = res.data.total
        })
      },
      handleShowNextLevel(val) {
        this.parentId=val
        this.load()
      },
      handleShowLastLevel() {
        this.parentId=0
        this.load()
      },
      add() {
        this.dialogVisible = true;
        this.form = {}
      },
      save() {
        if (this.form.id) {
          request.put("/cat", this.form).then(res => {
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
          request.post("/cat", this.form).then(res => {
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
      },
      handleDelete(id) {
        request.delete("/cat/" + id).then(res => {
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
      }
    }
  }
</script>
