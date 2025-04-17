<script setup>

</script>

<template>
  <div>
    <el-row>
      <el-col :span="24">
        <!--Table-->
        <el-table
          :data="favoriteList"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}"
          stripe
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" min-width="3%"> </el-table-column>
          <el-table-column type="index" label="No." min-width="3%"></el-table-column>
          <el-table-column prop="dietId" label="Diet Id" min-width="10%"></el-table-column>
          <el-table-column prop="dietName" label="Diet Name" min-width="20%">
            <template slot-scope="scope">
              <el-link type="primary" @click.native="goToDietDetail(scope.row.dietName)">{{ scope.row.dietName }}</el-link>
            </template>
          </el-table-column>

          <el-table-column label="Delete" min-width="10%">
            <template slot-scope="scope">
              <!--Delete Btn-->
              <el-button
                type="danger"
                icon="el-icon-delete"
                @click="removeFavoriteById(scope.row.favoriteId)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="24" style="margin-top: 20px; margin-bottom: 20px">
        <el-row :gutter="9" type="flex" justify="end">
          <el-col :span="2.5">
          </el-col>
          <el-col :span="2.5" style="margin-left: 10px">
            <el-button type="danger" @click="batchDeleteFavorite" :disabled="multipleSelection.length === 0"
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
import { favoriteList, favoriteNewList, addFavorite, updateFavorite, deleteFavorite, batchDeleteFavorite } from "@/api/favorite";
import homepage from "../homepage.vue";
export default {
  data() {
    return {
      favoriteList: [],
      queryInfo: {
        keyword: "",
        pageNo: 1,
        pageSize: 10,
      },
      submitDialogVisible: false,
      editForm: {
        favoriteId: "",
        dietId: "",
        userId: "",
        dietName: "",
      },
      multipleSelection: [],
      favoriteIds: [],
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
    this.getFavoriteList();
  },
  methods: {
    getFavoriteList() {
      this.queryInfo.keyword = this.$store.state.user.id;
      favoriteNewList(this.queryInfo)
        .then((res) => {
          if (res.data.code === 200) {
            // Get the favorite list
            this.favoriteList = res.data.data;
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
      this.getFavoriteList();
    },
    // Listen the event of changing pageNo
    handleCurrentChange(newPage) {
      this.queryInfo.pageNo = newPage;
      this.getFavoriteList();
    },

    showSubmitDialog(favoriteInfo) {
      this.submitDialogVisible = true;
    },
    postUpdateRequest(successMessage){
      updateFavorite(this.editForm)
        .then((res) => {
          if (res.data.code === 200) {
            this.getFavoriteList();
            this.$message({
              showClose: true,
              message: successMessage,
              type: "success",
            });
          } else {
            this.$message.error("Failed to edit favorite.");
          }
        })
        .catch((err) => {
          this.$message.error("Failed to edit favorite.");
          console.log(err);
        });
    },



    goToDietDetail(dietId) {
      this.$router.push({ name: 'dietDetail', params: { dietId } });
    },
    // Delete favorite by id
    async removeFavoriteById(favoriteId) {
      // Confirm dialog
      const confirmResult = await this.$confirm(
        "This operation will permanently delete the favorite. Do you want to continue?",
        "Warning",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult === "confirm") {
        deleteFavorite(favoriteId)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                showClose: true,
                message: "Deleted favorite successfully.",
                type: "success",
              });
              this.getFavoriteList();
              this.$store.dispatch('fetchFavoriteList'); // Update favorite list in store
            } else {
              this.$message.error("Failed to delete favorite.");
            }
          })
          .catch((err) => {
            this.$message.error("Failed to delete favorite.");
            console.log(err);
          });
      }
    },
    // Listen the event of changing selection
    handleSelectionChange(val) {
      this.multipleSelection = val;
      const newFavoriteIds = [];
      this.multipleSelection.forEach((item) => {
        newFavoriteIds.push(item.favoriteId);
      });
      this.favoriteIds = newFavoriteIds;
    },
    // Batch delete favorite
    async batchDeleteFavorite(){
      // Confirm dialog
      const confirmResult = await this.$confirm(
        "This operation will permanently delete the favorite. Do you want to continue?",
        "Warning",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirmResult === "confirm") {
        batchDeleteFavorite(this.favoriteIds)
          .then((res) => {
            if (res.data.code === 200) {
              this.$message({
                showClose: true,
                message: "Deleted favorite successfully.",
                type: "success",
              });
              this.getFavoriteList();
              this.$store.dispatch('fetchFavoriteList'); // Update favorite list in store
            } else {
              this.$message.error("Failed to delete favorite.");
            }
          })
          .catch((err) => {
            this.$message.error("Failed to delete favorite.");
            console.log(err);
          });
      }
    },
  }
}
</script>
