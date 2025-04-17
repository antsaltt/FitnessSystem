<script setup>

</script>

<template>
  <div>
    <el-page-header @back="goBack" :content="lessonParamName"></el-page-header>
    <el-row class="lessonRow">
      <el-col :span="10" class="lessonColImg">
      </el-col>
      <el-col :span="14" class="lessonColInfo">
        <el-card>
          <h1>{{ lessonDetail.lessonName }}</h1>
          <p>{{ lessonDetail.lessonDescription }}</p>
          <h2>{{ formattedPrice(getAdjustedPrice(lessonDetail.lessonPrice, this.orderForm.size)) }} </h2>
        </el-card>
        <el-row>
          <el-form ref="orderForm" :model="orderForm">
          </el-form>
          <el-input-number v-model="orderForm.num" @change="handleChange" :min="1" :max="10" label="LessonName"></el-input-number>
          <el-button type="primary" @click="addToMycourse">Book Now</el-button>
        </el-row>

      </el-col>
    </el-row>

  </div>

</template>

<style scoped>
.lessonRow {
  padding: 20px;
}
.lessonColImg {
  justify-content: center;
  align-items: center;
}

.lessonColInfo > :first-child {
  background: #f3f3f3;
}

/* .el-icon-coffee-small{
  font-size: 10px;
}

.el-icon-coffee-medium{
  font-size: 12px;
}

.el-icon-coffee-large{
  font-size: 14px;
} */
</style>

<script>
import { getCourseDetail } from "../../api/course";
import {addMycourse} from "../../api/mycourse";
import homepage from './homepage.vue';
export default {
  data() {
    return {
      lessonParamName: "",
      lessonDetail: {
        lessonId: "",
        lessonName: "",
        lessonDescription: "",
        lessonInstructor: "",
        lessonTime: "",
        lessonPrice: "",
        lessonCapacity: "",

      },
      mycourseForm: {
        lessonId: "",
        lessonName: "",
        lessonInstructor: "",
        lessonTime: "",
        lessonPrice: "",
        userId: "",
      },
      orderForm: {
        num: 0,
      },

    };
  },
  computed: {

  },
  created() {
    this.lessonParamName = this.$route.params.lessonId;
    this.getlesson();
  },
  methods: {
    goBack() {
      this.$router.back();
    },
    getlesson() {
      getLessonDetail("", this.lessonParamName)
        .then((res) => {
          if (res.data.code === 200) {
            this.lessonDetail = res.data.data;
            //TODO: following value should get from backend
            // this.lessonDetail.lessonRate = 4.5;
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    handleChange(val) {
      console.log(val);
    },

    addToMycourse(){
      if (this.isLoggedIn()) {
        this.mycourseForm.userId = this.$store.state.user.id;
        this.mycourseForm.lessonId = this.lessonDetail.lessonId;
        this.mycourseForm.lessonName = this.lessonDetail.lessonName;
        this.mycourseForm.lessonTime = this.lessonDetail.lessonTime;
        this.mycourseForm.lessonInstructor = this.lessonDetail.lessonInstructor;
        this.mycourseForm.lessonName = this.lessonDetail.lessonName;
        addMycourse(this.mycourseForm)
          .then((res) => {
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
    formattedPrice(price) {
      return '$'+parseFloat(price).toFixed(2);
    },
    getAdjustedPrice(price, size) {
      let adjustedPrice = parseFloat(price);
      return adjustedPrice;
    },
  }
}
</script>
