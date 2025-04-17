<template>
    <div>
      <el-row>
        <el-col :span="24">
          <el-row :gutter="20">
            <el-col :span="6">
              <!--search-->
              <el-input
                placeholder="Please enter lesson information"
                v-model="queryInfo.keyword"
                clearable
                @clear="getLessonList"
              >
                <el-button
                  slot="append"
                  icon="el-icon-search"
                  @click="getLessonList"
                ></el-button>
              </el-input>
            </el-col>
            <el-col :span="2.5">
              <el-button type="primary" @click="addDialogVisible = true" icon="el-icon-plus">Add Lesson</el-button>
            </el-col>
            <el-col :span="2.5">
              <el-button type="danger" @click="batchDeleteLesson" :disabled="multipleSelection.length === 0"
                         :icon="multipleSelection.length === 0 ? 'el-icon-delete' : 'el-icon-delete-solid'"
              >Batch Delete</el-button
              >
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="24">
          <!--Table-->
          <el-table
            :data="lessonList"
            :header-cell-style="{'text-align':'center'}"
            :cell-style="{'text-align':'center'}"
            border
            stripe
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55" min-width="3%"> </el-table-column>
            <el-table-column type="index" label="No." min-width="3%"></el-table-column>
            <el-table-column prop="lessonCategory" label="Lesson Category" min-width="15%"></el-table-column>
            <el-table-column prop="lessonName" label="Lesson Name" min-width="25%"></el-table-column>
            <el-table-column prop="lessonInstructor" label="Lesson Instructor" min-width="10%">
            </el-table-column>
            <el-table-column prop="lessonTime" label="Lesson Time" min-width="25%"></el-table-column>
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
                  @click="removeLessonById(scope.row.lessonId)"
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
      <!--Add Lesson Dialog-->
      <el-dialog
        title="Add Lesson"
        :visible.sync="addDialogVisible"
        width="35%"
        @close="addDialogClosed"
        :top="`5vh`"
      >
        <el-form :model="lessonForm" :rules="lessonRules" ref="lessonForm" label-width="25%">
          <el-form-item label="Lesson Name" prop="lessonName">
            <el-input v-model="lessonForm.lessonName"></el-input>
          </el-form-item>
          <el-form-item label="Description" prop="lessonDescription">
            <el-input type="textarea" :rows="3" v-model="lessonForm.lessonDescription"></el-input>
          </el-form-item>
          <el-form-item label="Category" prop="lessonCategory">
            <el-select v-model="lessonForm.lessonCategory" placeholder="Please select a lesson category.">
              <el-option v-for="item in categoryOpinions" :key="item.name" :label="item.label" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Lesson Instructor" prop="lessonInstructor">
            <el-input v-model="lessonForm.lessonInstructor"></el-input>
          </el-form-item>
          <el-form-item label="Lesson Time" prop="lessonTime">
            <el-input v-model="lessonForm.lessonTime"></el-input>
          </el-form-item>

        </el-form>
        <!--Button-->
        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="addLesson('lessonForm')">Confirm</el-button>
        </span>
      </el-dialog>

      <!--Edit Lesson Dialog-->
      <el-dialog title="Edit Lesson" :visible.sync="editDialogVisible" width="35%" :top="`5vh`" @close="editDialogClosed">
        <el-form :model="editForm" :rules="editRules" ref="editForm" label-width="25%">
          <el-form-item label="Lesson Name" prop="lessonName">
            <el-input v-model="editForm.lessonName"></el-input>
          </el-form-item>
          <el-form-item label="Category" prop="lessonCategory">
            <el-select v-model="editForm.lessonCategory" placeholder="Please select a lesson category.">
              <el-option v-for="item in categoryOpinions" :key="item.name" :label="item.label" :value="item.name"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Lesson Price" prop="lessonPrice">
            <el-input v-model="editForm.lessonInstructor"></el-input>
          </el-form-item>
          <el-form-item label="Lesson Time" prop="lessonTime">
            <el-input v-model="editForm.lessonTime"></el-input>
          </el-form-item>
          <el-form-item label="Description" prop="lessonDescription">
            <el-input type="textarea" :rows="3" v-model="editForm.lessonDescription"></el-input>
          </el-form-item>
        </el-form>
        <!--Button-->
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="editLesson('editForm')">Confirm</el-button>
        </span>
      </el-dialog>
    </div>
  </template>

  <script>
  import { lessonList, getLessonDetail, addLesson, updateLesson, deleteLesson, batchDeleteLesson } from "@/api/courseEdit";
  import { tabs } from "../homepage/Coursemanagement.vue";
  export default {
    data() {
      return {
        lessonList: [],
        total: 0,
        categoryOpinions: tabs.filter(tab => tab.name !== 'all'),
        queryInfo: {
          keyword: "",
          pageNo: 1,
          pageSize: 5,
        },
        addDialogVisible: false,
        lessonForm: {
          lessonName: "",
          lessonDescription: "",
          lessonCategory: "",
          lessonInstructor: "",
          lessonTime: "",
        },
        editDialogVisible: false,
        editForm: {
          lessonId: "",
          lessonName: "",
          lessonCategory: "",
          lessonDescription: "",
          lessonInstructor: "",
          lessonTime: "",
        },
        multipleSelection: [],
        lessonIds: [],
        lessonRules: {
          lessonName : [
            { required: true, message: 'Login name cannot be empty!', trigger: 'change' }
          ],
          lessonCategory : [
            { required: true, message: 'User name cannot be empty!', trigger: 'change' }
          ],
          lessonDescription : [
            { required: true, message: 'Description cannot be empty!', trigger: 'change' }
          ],
          lessonInstructor : [
            { required: true, message: 'Balance cannot be empty!', trigger: 'change' }
          ],
          lessonTime : [
            { required: true, message: 'picUrl cannot be empty!', trigger: 'change' }
          ],
        },
        editRules: {
          lessonName : [
            { required: true, message: 'Login name cannot be empty!', trigger: 'change' }
          ],
          lessonCategory : [
            { required: true, message: 'User name cannot be empty!', trigger: 'change' }
          ],
          lessonDescription : [
            { required: true, message: 'Description cannot be empty!', trigger: 'change' }
          ],
          lessonInstructor : [
            { required: true, message: 'Instructor cannot be empty!', trigger: 'change' }
          ],
          lessonTime : [
            { required: true, message: 'Time cannot be empty!', trigger: 'change' }
          ],
        },
      };
    },
    created() {
      this.getLessonList();
    },
    methods: {
      getLessonList() {
        lessonList(this.queryInfo)
          .then((res) => {
            if (res.data.code === 200) {
              // Get the lesson list
              this.lessonList = res.data.data;
              this.total = res.data.totalRecords;
            } else {
              this.$message.error(res.data.message);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      },
      handleSizeChange(newSize) {
        this.queryInfo.pageSize = newSize;
        this.getLessonList();
      },
      // Listen the event of changing pageNo
      handleCurrentChange(newPage) {
        this.queryInfo.pageNo = newPage;
        this.getLessonList();
      },
      // Add lesson
      addLesson(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            addLesson(this.lessonForm)
              .then((res) => {
                if (res.data.code === 200) {
                  this.addDialogVisible = false;
                  this.getLessonList();
                  this.$message({
                    message: "Added lesson successfully.",
                    type: "success",
                  });
                } else {
                  this.$message.error("Failed to add lesson.");
                }
              })
              .catch((err) => {
                this.$message.error("Failed to add lesson.");
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
        this.$refs.lessonForm.resetFields();
      },
      editDialogClosed() {
        this.$refs.editForm.resetFields();
      },

      showEditDialog(lessonInfo) {
        this.editDialogVisible = true;
        this.editForm = lessonInfo;
      },
      // Edit lesson
      editLesson(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            updateLesson(this.editForm)
              .then((res) => {
                if (res.data.code === 200) {
                  this.editDialogVisible = false;
                  this.getLessonList();
                  this.$message({
                    message: "Edited lesson successfully.",
                    type: "success",
                  });
                } else {
                  this.$message.error("Failed to edit lesson.");
                }
              })
              .catch((err) => {
                this.$message.error("Failed to edit lesson.");
                console.loge(err);
              });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      // Delete lesson by id
      async removeLessonById(lessonId) {
        // Confirm dialog
        const confirmResult = await this.$confirm(
          "This operation will permanently delete the lesson. Do you want to continue?",
          "Warning",
          {
            confirmButtonText: "Confirm",
            cancelButtonText: "Cancel",
            type: "warning",
          }
        ).catch((err) => err);
        if (confirmResult === "confirm") {
          deleteLesson(lessonId)
            .then((res) => {
              if (res.data.code === 200) {
                this.getLessonList();
                this.$message({
                  message: "Deleted lesson successfully.",
                  type: "success",
                });
              } else {
                this.$message.error("Failed to delete lesson.");
              }
            })
            .catch((err) => {
              this.$message.error("Failed to delete lesson.");
              console.log(err);
            });
        }
      },
      // Listen the event of changing selection
      handleSelectionChange(val) {
        this.multipleSelection = val;
        const newLessonIds = [];
        this.multipleSelection.forEach((item) => {
          newLessonIds.push(item.lessonId);
        });
        this.lessonIds = newLessonIds;
      },
      // Batch delete lesson
      async batchDeleteLesson(){
        // Confirm dialog
        const confirmResult = await this.$confirm(
          "This operation will permanently delete the lesson. Do you want to continue?",
          "Warning",
          {
            confirmButtonText: "Confirm",
            cancelButtonText: "Cancel",
            type: "warning",
          }
        ).catch((err) => err);
        if (confirmResult === "confirm") {
          batchDeleteLesson(this.lessonIds)
            .then((res) => {
              if (res.data.code === 200) {
                this.$message({
                  message: "Deleted lesson successfully.",
                  type: "success",
                });
                this.getLessonList();
              } else {
                this.$message.error("Failed to delete lesson.");
              }
            })
            .catch((err) => {
              this.$message.error("Failed to delete lesson.");
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
