<script setup>

</script>

<template>
  <div>
    <el-page-header @back="goBack" :content="dietParamName"></el-page-header>
    <el-row class="dietRow">
      <el-col :span="10" class="dietColImg">
        <el-image
          style="width: 70%; height: 70%; margin-top: 130px;"
          fit="fill"
          :src="requireDietImage(dietDetail.dietPicUrl)"
          :preview-src-list="[requireDietImage(dietDetail.dietPicUrl)]"
        ></el-image>
        <h3>Category: <el-tag :style="getCategoryClass(dietDetail.dietCategory)">{{ dietDetail.dietCategory}}</el-tag></h3>
      </el-col>
      <el-col :span="14" class="dietColInfo">
        <el-card>
          <h1>{{ dietDetail.dietName }}</h1>
          <p>{{ dietDetail.dietDescription }}</p>
        </el-card>
        <el-row>
        <br><br>
          <el-button
            type="primary"
            @click="addToFavorite"
            :disabled="isFavorite"
          >{{ isFavorite ? 'Already Favorite' : 'Add To Favorite' }}</el-button>
        </el-row>
      </el-col>
    </el-row>

  </div>

</template>

<style scoped>
.dietRow {
  padding: 20px;
}
.dietColImg {
  justify-content: center;
  align-items: center;
}

.dietColInfo > :first-child {
  background: #f3f3f3;
}
</style>

<script>
import { getDietDetail } from "../../api/dietPlan";
import { addFavorite, deleteFavorite } from "../../api/favorite";
import homepage from './homepage.vue';
export default {
  data() {
    return {
      dietParamName: "",
      dietDetail: {
        dietId: "",
        dietName: "",
        dietDescription: "",
        dietCategory: "",
        dietPicURL: "",
        dietRate: 0,
      },
      favoriteForm: {
        dietId: "",
        dietNum: "",
        userId: "",
        dietName: "",
      },
      isFavorite: false,
      categoryColors: {
        "Breakfast": "color: #fff; background-color: #ff3333;",
        "Brunch": "color: #fff; background-color: #ffaa33;",
        "Lunch": "color: #fff; background-color: #55aa00;",
        "Dinner": "color: #fff; background-color: #d28eff",
      },
    };
  },
  computed: {

  },
  created() {
    this.dietParamName = this.$route.params.dietId;
    this.getDiet();
    this.checkIfFavorite();
  },
  methods: {
    goBack() {
      this.$router.back();
    },
    getDiet() {
      getDietDetail("", this.dietParamName)
        .then((res) => {
          if (res.data.code === 200) {
            this.dietDetail = res.data.data;
            //TODO: following value should get from backend
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    checkIfFavorite() {
      // Implement a method to check if the current diet is already in the favorites
      // and set isFavorite accordingly
      // Assuming we have a method to fetch the favorite list and it returns a promise
      this.$store.dispatch('fetchFavoriteList').then(() => {
        const favoriteList = this.$store.state.favoriteList;
        this.isFavorite = favoriteList.some(favorite => favorite.dietId === this.dietDetail.dietId);
      });
    },
    requireDietImage(dietPicUrl) {
      try {
        // Try to require the image
        return require(`../../assets/img/diets/${dietPicUrl}.jpg`);
      } catch (e) {
        // If it fails, return the default image
        return require("../../assets/img/default.jpg");
      }
    },
    handleChange(val) {
      console.log(val);
    },
    getCategoryClass(category) {
      return this.categoryColors[category];
    },
    addToFavorite(){
      if (this.isLoggedIn()) {
        this.favoriteForm.userId = this.$store.state.user.id;
        this.favoriteForm.dietId = this.dietDetail.dietId;
        this.favoriteForm.dietName = this.dietDetail.dietName;
        addFavorite(this.favoriteForm)
          .then((res) => {
            if (res.data.code === 200) {
              this.isFavorite = true;
              this.$message({
                showClose: true,
                message: 'Add to your favorite successfully.',
                center: true,
                type: 'success'
              });
            } else {
              this.$message.error(res.data.message);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.$router.push({
          path: '/login',
          query: {
            redirect: this.$route.fullPath
          }
        });
        this.$message({
          type: 'info',
          message: 'Please login first.'
        });
      }
    },
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
                    this.isFavorite = false;
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
    isLoggedIn() {
      // Check if the user is logged in
      if(!this.$store.state.token || this.$store.state.user === undefined || this.$store.state.user === ''
        || this.$store.state.isStaff === undefined || this.$store.state.isStaff === true ) {
        return false;
      } else {
        return true;
      }
    },
  }
}
</script>
