<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-row :gutter="20">
          <el-col :span="6">
            <!--search-->
            <el-input
              placeholder="Please enter diet information"
              v-model="queryInfo.keyword"
              clearable
              @clear="getDietList"
            >
              <el-button
                slot="append"
                icon="el-icon-search"
                @click="getDietList"
              ></el-button>
            </el-input>
          </el-col>
          <el-col :span="2.5">
            <el-button type="primary" @click="addDialogVisible = true" icon="el-icon-plus">Add Diet</el-button>
          </el-col>
          <el-col :span="2.5">
            <el-button type="danger" @click="batchDeleteDiet" :disabled="multipleSelection.length === 0"
                       :icon="multipleSelection.length === 0 ? 'el-icon-delete' : 'el-icon-delete-solid'"
            >Batch Delete</el-button
            >
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="24">
        <!--Table-->
        <el-table
          :data="dietList"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}"
          border
          stripe
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" min-width="3%"> </el-table-column>
          <el-table-column type="index" label="No." min-width="3%"></el-table-column>
          <el-table-column prop="dietCategory" label="Diet Category" min-width="15%"></el-table-column>
          <el-table-column prop="dietName" label="Diet Name" min-width="25%"></el-table-column>
          <el-table-column prop="dietPicUrl" label="Picture Url" min-width="25%"></el-table-column>
          <el-table-column label="Operation" min-width="10%">
            <template slot-scope="scope">
              <!--Edit Btn-->
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row)"
              ></el-button>
              <!--Delete Btn-->
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="removeDietById(scope.row.dietId)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNo"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-row>
    <!--Add Diet Dialog-->
    <el-dialog
      title="Add Diet"
      :visible.sync="addDialogVisible"
      width="35%"
      @close="addDialogClosed"
      :top="`5vh`"
    >
      <el-form :model="dietForm" :rules="dietRules" ref="dietForm" label-width="25%">
        <el-form-item label="Diet Name" prop="dietName">
          <el-input v-model="dietForm.dietName"></el-input>
        </el-form-item>
        <el-form-item label="Category" prop="dietCategory">
          <el-select v-model="dietForm.dietCategory" placeholder="Please select a diet category.">
            <el-option v-for="item in categoryOpinions" :key="item.name" :label="item.label" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Picture Url" prop="dietPicUrl">
          <el-input v-model="dietForm.dietPicUrl"></el-input>
        </el-form-item>
        <el-form-item label="Description" prop="dietDescription">
          <el-input type="textarea" :rows="3" v-model="dietForm.dietDescription"></el-input>
        </el-form-item>
      </el-form>
      <!--Button-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addDiet('dietForm')">Confirm</el-button>
      </span>
    </el-dialog>

    <!--Edit Diet Dialog-->
    <el-dialog title="Edit Diet" :visible.sync="editDialogVisible" width="35%" :top="`5vh`" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editRules" ref="editForm" label-width="25%">
        <el-form-item label="Diet Name" prop="dietName">
          <el-input v-model="editForm.dietName"></el-input>
        </el-form-item>
        <el-form-item label="Category" prop="dietCategory">
          <el-select v-model="editForm.dietCategory" placeholder="Please select a diet category.">
            <el-option v-for="item in categoryOpinions" :key="item.name" :label="item.label" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Picture Url" prop="dietPicUrl">
          <el-input v-model="editForm.dietPicUrl"></el-input>
        </el-form-item>
        <el-form-item label="Description" prop="dietDescription">
          <el-input type="textarea" :rows="3" v-model="editForm.dietDescription"></el-input>
        </el-form-item>
      </el-form>
      <!--Button-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editDiet('editForm')">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { dietList, getDietDetail, addDiet, updateDiet, deleteDiet, batchDeleteDiet } from "@/api/dietEdit";
import { tabs } from "../homepage/diets.vue";
export default {
  data() {
    return {
      dietList: [],
      total: 0,
      categoryOpinions: tabs.filter(tab => tab.name !== 'all'),
      queryInfo: {
        keyword: "",
        pageNo: 1,
        pageSize: 5,
      },
      addDialogVisible: false,
      dietForm: {
        dietName: "",
        dietCategory: "",
        dietDescription: "",
        dietPicUrl: "",
      },
      editDialogVisible: false,
      editForm: {
        dietId: "",
        dietName: "",
        dietCategory: "",
        dietDescription: "",
        dietPicUrl: "",
      },
      multipleSelection: [],
      dietIds: [],
      dietRules: {
        dietName : [
          { required: true, message: 'Login name cannot be empty!', trigger: 'change' }
        ],
        dietCategory : [
          { required: true, message: 'User name cannot be empty!', trigger: 'change' }
        ],
        dietDescription : [
          { required: true, message: 'Description cannot be empty!', trigger: 'change' }
        ],
        dietPicUrl : [
          { required: true, message: 'picUrl cannot be empty!', trigger: 'change' }
        ],
      },
      editRules: {
        dietName : [
          { required: true, message: 'Login name cannot be empty!', trigger: 'change' }
        ],
        dietCategory : [
          { required: true, message: 'User name cannot be empty!', trigger: 'change' }
        ],
        dietDescription : [
          { required: true, message: 'Description cannot be empty!', trigger: 'change' }
        ],
        dietPicUrl : [
          { required: true, message: 'picUrl cannot be empty!', trigger: 'change' }
        ],
      },
    };
  },
  created() {
    this.getDietList();
  },
  methods: {
    getDietList() {
      dietList(this.queryInfo)
        .then((res) => {
          if (res.data.code === 200) {
            // Get the diet list
            this.dietList = res.data.data;
            this.total = res.data.totalRecords;
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // Listen the event of changing pageSize
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getDietList();
    },
    // Listen the event of changing pageNo
    handleCurrentChange(newPage) {
      this.queryInfo.pageNo = newPage;
      this.getDietList();
    },
    // Add diet
    addDiet(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addDiet(this.dietForm)
            .then((res) => {
              if (res.data.code === 200) {
                this.addDialogVisible = false;
                this.getDietList();
                this.$message({
                  message: "Added diet successfully.",
                  type: "success",
                });
              } else {
                this.$message.error("Failed to add diet.");
              }
            })
            .catch((err) => {
              this.$message.error("Failed to add diet.");
              console.log(err);
            });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    // Listen the event of closing add dialog
    addDialogClosed() {
      // Reset the form
      this.$refs.dietForm.resetFields();
    },
    editDialogClosed() {
      this.$refs.editForm.resetFields();
    },

    showEditDialog(dietInfo) {
      this.editDialogVisible = true;
      this.editForm = dietInfo;
    },
    // Edit diet
    editDiet(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateDiet(this.editForm)
            .then((res) => {
              if (res.data.code === 200) {
                this.editDialogVisible = false;
                this.getDietList();
                this.$message({
                  message: "Edited diet successfully.",
                  type: "success",
                });
              } else {
                this.$message.error("Failed to edit diet.");
              }
            })
            .catch((err) => {
              this.$message.error("Failed to edit diet.");
              console.loge(err);
            });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // Delete diet by id
    async removeDietById(dietId) {
      // Confirm dialog
      const confirmResult = await this.$confirm(
        "This operation will permanently delete the diet. Do you want to continue?",
        "Warning",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult === "confirm") {
        deleteDiet(dietId)
          .then((res) => {
            if (res.data.code === 200) {
              this.getDietList();
              this.$message({
                message: "Deleted diet successfully.",
                type: "success",
              });
            } else {
              this.$message.error("Failed to delete diet.");
            }
          })
          .catch((err) => {
            this.$message.error("Failed to delete diet.");
            console.log(err);
          });
      }
    },
    // Listen the event of changing selection
    handleSelectionChange(val) {
      this.multipleSelection = val;
      const newDietIds = [];
      this.multipleSelection.forEach((item) => {
        newDietIds.push(item.dietId);
      });
      this.dietIds = newDietIds;
    },
    // Batch delete diet
    async batchDeleteDiet(){
      // Confirm dialog
      const confirmResult = await this.$confirm(
        "This operation will permanently delete the diet. Do you want to continue?",
        "Warning",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult === "confirm") {
        batchDeleteDiet(this.dietIds)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                message: "Deleted diet successfully.",
                type: "success",
              });
              this.getDietList();
            } else {
              this.$message.error("Failed to delete diet.");
            }
          })
          .catch((err) => {
            this.$message.error("Failed to delete diet.");
            console.log(err);
          });
      }
    },
  },
};
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.el-card {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
  height: 60pt;
}

.el-select {
  width: 100%;
}
</style>
