<script setup>

</script>

<template>
  <el-tabs v-model="category" @tab-click="getCourseList" type="border-card" tab-position="left">
    <el-tab-pane v-for="(tab, index) in tabs" :label="tab.label" :name="tab.name" :key="index">
      <el-empty :image-size="200" v-if="CourseList.length === 0"></el-empty>
      <el-table v-else :data="CourseList" style="width: 100%">
        <el-table-column prop="lessonName" label="Class Name" width="260"></el-table-column>
        <el-table-column prop="lessonDescription" label="Description" width="550"></el-table-column> 
        <el-table-column prop="lessonInstructor" label="Instructor" width="150"></el-table-column>
        <el-table-column prop="lessonCategory" label="Category" width="120"></el-table-column>
        <el-table-column prop="lessonTime" label="Time" width="220"></el-table-column>
        <el-table-column label="Action" width="180">
          <template slot-scope="scope">
            <el-button type="primary" @click="addToMycourse(scope.row.lessonName)">Book Class</el-button>
          </template>
        </el-table-column>
      </el-table>
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
  import { CourseList} from "../../api/course";
  import {addMycourse} from "../../api/mycourse";
  import homepage from './homepage.vue';
  import { getCourseDetail } from "../../api/course";
  export const tabs = [
      {
        label: "All Lessons",
        name: "all",
      },
      {
        label: "Aerobic",
        name: "Aerobic",
      },
      {
        label: "Anaerobic",
        name: "Anaerobic",
      },
      {
        label: "Mixed Exercise",
        name: "Mixed Exercise",
      },
    ];
  export default {
    data() {
      return {
        lessonParamName: "",
        lessonDetail: {
        lessonId: "",
        lessonName: "",
        lessonDescription: "",
        lessonCategory: "",
        lessonInstructor: "",
        lessonTime: "",
      },
        mycourseForm: {
        userId: "",
        lessonId: "",
        lessonName: "",
        lessonInstructor: "",
        lessonTime:"",
      },
        CourseList: [],
        category: "all",
        tabs: tabs,
      };
    },
    created() {
      this.getCourseList();
    },
    methods: {
      getCourseList() {
        CourseList("", this.category)
          .then((res) => {
            if (res.data.code === 200) {
              this.CourseList = res.data.data.records;
            } else {
              
              this.$message.error(res.data.message);
            }
          })
          .catch((err) => {
            console.log(err);
          });
      },
      selectCourse(course) {
      this.$message({
        message: `You have selected ${course.lessonName}!`,
        type: 'success'
      });
    },

    async getLesson() {
      try {
        const res = await getCourseDetail("", this.lessonParamName);
        if (res.data.code === 200) {
          this.lessonDetail = res.data.data;
        } else {
          this.$message.error(res.data.message);
        }
      } catch (err) {
        console.log(err);
      }
    },
      async addToMycourse(lessonName){
      if (this.isLoggedIn()) {
        this.lessonParamName = lessonName;
        
        await this.getLesson();
        this.mycourseForm.userId = this.$store.state.user.id;
        this.mycourseForm.lessonId = this.lessonDetail.lessonId;
        this.mycourseForm.lessonName = this.lessonDetail.lessonName;
        this.mycourseForm.lessonInstructor = this.lessonDetail.lessonInstructor;
        this.mycourseForm.lessonTime = this.lessonDetail.lessonTime;
        
        
        
       
        try {
          const res = await addMycourse(this.mycourseForm);
          if (res.data.code === 200) {
            this.callGetMycourseNumber();
            this.$message({
              showClose: true,
              message: 'Add successfully.',
              center: true,
              type: 'success'
            });
          } else {
            this.$message.error(res.data.message);
          }
        } catch (err) {
          this.$message.error("Failed to add course.");
          console.log(err);
        }
        
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
    isLoggedIn() {
      // Check if the user is logged in
      if(!this.$store.state.token || this.$store.state.user === undefined || this.$store.state.user === ''
        || this.$store.state.isStaff === undefined || this.$store.state.isStaff === true ) {
        return false;
      } else {
        return true;
      }
    },
    callGetMycourseNumber() {
      homepage.methods.getMycourseNumber.call(this);
    },
    
    },
  }
</script>
