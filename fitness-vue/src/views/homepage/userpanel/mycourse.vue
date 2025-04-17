<script setup>

</script>

<template>
  <div>
    <el-row>
      <el-col :span="24">
        <!--Table-->
        <el-table
          :data="mycourseList"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}"
          stripe
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" min-width="3%"> </el-table-column>
          <el-table-column type="index" label="No." min-width="3%"></el-table-column>
          <el-table-column prop="lessonId" label="Lesson Id" min-width="10%"></el-table-column>
          <el-table-column prop="lessonName" label="Lesson Name" min-width="20%">
          </el-table-column>
          <el-table-column prop="lessonInstructor" label="Lesson Instructor" min-width="15%">
          </el-table-column>
          <el-table-column prop="lessonTime" label="Lesson Time" min-width="10%">
          </el-table-column>
          <el-table-column label="Delete" min-width="10%">
            <template slot-scope="scope">
              <!--Delete Btn-->
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="removeMycourseById(scope.row.mycourseId)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="24" style="margin-top: 20px; margin-bottom: 20px">
        <el-row :gutter="9" type="flex" justify="end">
          <el-col :span="2.5" style="margin-left: 10px">
            <el-button type="danger" @click="batchDeleteMycourse" :disabled="multipleSelection.length === 0"
            >Batch Delete</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNo"
        :page-sizes="[1, 5, 10, 20]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import { mycourseNumber, mycourseList, mycourseNewList, addMycourse, updateMycourse, deleteMycourse, batchDeleteMycourse } from "@/api/mycourse";
import homepage from "../homepage.vue";
// import { addOrder } from "@/api/order";
export default {
  data() {
    return {
      mycourseList: [],
      total: 0,
      totalPrice: 0,
      queryInfo: {
        keyword: "",
        pageNo: 1,
        pageSize: 10,
      },
      submitDialogVisible: false,
      editForm: {
        mycourseId: "",
        lessonId: "",
        lessonName: "",
        lessonInstructor: "",
        lessonTime: "",
        userId: "",
      },
      multipleSelection: [],
      mycourseIds: [],
      editRules: {
      },
      labelStyle: {
        width: '30%',
        color: 'black',
        'font-weight': 'bold'
      },
      contentStyle: {
        width: '20%',
        color: 'red'
      },
    }
  },
  created() {
    this.getMycourseList();
  },
  methods: {
    getMycourseList() {
      this.queryInfo.keyword = this.$store.state.user.id;
      mycourseNewList(this.queryInfo)
        .then((res) => {
          if (res.data.code === 200) {
            // Get the mycourse list
            this.mycourseList = res.data.data;
            // this.mycourseList.lessonPrice = 1;
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
      this.getMycourseList();
    },
    // Listen the event of changing pageNo
    handleCurrentChange(newPage) {
      this.queryInfo.pageNo = newPage;
      this.getMycourseList();
    },

    showSubmitDialog(mycourseInfo) {
      this.submitDialogVisible = true;
    },

    postUpdateRequest(successMessage){
      updateMycourse(this.editForm)
        .then((res) => {
          if (res.data.code === 200) {
            this.getMycourseList();
            this.$message({
              showClose: true,
              message: successMessage,
              type: "success",
            });
          } else {
            this.$message.error("Failed to edit mycourse.");
          }
        })
        .catch((err) => {
          this.$message.error("Failed to edit mycourse.");
          console.log(err);
        });
    },
    callGetMycourseNumber() {
      homepage.methods.getMycourseNumber.call(this);
    },
    changeLessonNum(mycourseInfo){
      this.editForm = mycourseInfo;
      this.postUpdateRequest('Edited lesson quantity successfully.');
    },
    formattedPrice(price) {
      return '$'+parseFloat(price).toFixed(2);
    },
    goToLessonDetail(lessonId) {
      this.$router.push({ name: 'lessonDetail', params: { lessonId } });
    },
    // Delete mycourse by id
    async removeMycourseById(mycourseId) {
      // Confirm dialog
      const confirmResult = await this.$confirm(
        "This operation will permanently delete the mycourse. Do you want to continue?",
        "Warning",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult === "confirm") {
        deleteMycourse(mycourseId)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                showClose: true,
                message: "Deleted mycourse successfully.",
                type: "success",
              });
              this.getMycourseList();
              this.callGetMycourseNumber();
            } else {
              this.$message.error("Failed to delete mycourse.");
            }
          })
          .catch((err) => {
            this.$message.error("Failed to delete mycourse.");
            console.log(err);
          });
      }
    },
    // Listen the event of changing selection
    handleSelectionChange(val) {
      this.multipleSelection = val;
      const newMycourseIds = [];
      this.multipleSelection.forEach((item) => {
        newMycourseIds.push(item.mycourseId);
      });
      this.mycourseIds = newMycourseIds;
      // Calculate the total price
      this.totalPrice = 0;
      this.multipleSelection.forEach((item) => {
        this.totalPrice += this.getAdjustedPrice(item.lessonPrice) * item.lessonNum;
      });
    },
    // Batch delete mycourse
    async batchDeleteMycourse(){
      // Confirm dialog
      const confirmResult = await this.$confirm(
        "This operation will permanently delete the mycourse. Do you want to continue?",
        "Warning",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult === "confirm") {
        batchDeleteMycourse(this.mycourseIds)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                showClose: true,
                message: "Deleted mycourse successfully.",
                type: "success",
              });
              this.getMycourseList();
              this.callGetMycourseNumber();
            } else {
              this.$message.error("Failed to delete mycourse.");
            }
          })
          .catch((err) => {
            this.$message.error("Failed to delete mycourse.");
            console.log(err);
          });
      }
    },
  },
}
</script>

<style scoped>
.el-icon-coffee-small{
  font-size: 10px;
}

.el-icon-coffee-medium{
  font-size: 12px;
}

.el-icon-coffee-large{
  font-size: 14px;
}
</style>
