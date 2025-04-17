<script setup>

</script>

<template>
  <el-tabs v-model="category" @tab-click="getDietPlanList" type="border-card" tab-position="left" >
    <el-tab-pane v-for="(tab, index) in tabs" :label="tab.label" :name="tab.name" :key="index">
      <el-empty :image-size="200" v-if="dietPlanList.length === 0"></el-empty>
      <el-row :gutter="20" v-else class="scrollable-row">
        <el-backtop target=".scrollable-row" :visibility-height="100" />
        <el-col style="padding-top: 10px; padding-bottom: 10px" :span="5" v-for="(diet, index) in dietPlanList" :key="index" class="hover-up">
          <el-card :body-style="{ padding: '10px'}">
            <el-image
              style="width: 100%; height: 100%; cursor: pointer"
              fit="fill"
              :src="requireDietImage(diet.dietPicUrl)"
              @click.native="goToDietDetail(diet.dietName)"
            >
            </el-image>
            <div style="padding: 3px; min-height: 40px; align-items: center; display: flex; justify-content: center;" >
              <span class="bottom">{{ diet.dietName }}</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-tab-pane>
  </el-tabs>
</template>

<style scoped>
.scrollable-row {
  height: calc(100vh - 162px); /* Adjust the height */
  overflow-y: auto; /* Set vertical scroll */
}
.hover-up {
  transition: transform 0.2s ease-in;
}
.hover-up:hover {
  transform: translateY(-5px);
}
</style>

<script>
  import { dietPlanList} from "../../api/dietPlan";
  export const tabs = [
      {
        label: "All dietary combinations",
        name: "all",
      },
      {
        label: "Breakfast",
        name: "Breakfast",
      },
      {
        label: "Brunch",
        name: "Brunch",
      },
      {
        label: "Lunch",
        name: "Lunch",
      },
      {
        label: "Dinner",
        name: "Dinner",
      },
    ];
  export default {
    data() {
      return {
        dietPlanList: [],
        category: "all",
        tabs: tabs,
      };
    },
    created() {
      this.getDietPlanList();
    },
    methods: {
      getDietPlanList() {
        dietPlanList("", this.category)
          .then((res) => {
            if (res.data.code === 200) {
              this.dietPlanList = res.data.data.records;
            } else {
              this.$message.error(res.data.message);
            }
          })
          .catch((err) => {
            console.log(err);
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
      goToDietDetail(dietId) {
        // Use the router to navigate to the diet detail page
        this.$router.push({ name: 'dietDetail', params: { dietId } });
      },
    },
  }
</script>
