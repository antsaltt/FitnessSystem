<script setup>

</script>

<template>
  <div class="index">
    <el-container>
      <el-header style="background-color: #333333">
        <el-menu
          :default-active="$route.path"
          router
          text-color="#dbd7d7" active-text-color="white" mode="horizontal" background-color="#333333" style="display: flex;"
        >
          <el-menu-item
            v-for="(item, i) in navList" :key="i" :index="item.name"
          >
            <i :class="item.icon"></i>
            {{ item.title }}
          </el-menu-item>
          <div class="button-container">
            <div v-if="!isLoggedIn">
              <el-button type="primary" class="login-btn" @click="login">Login</el-button>
              <el-button class="login-btn" @click="signup">Sign up</el-button>
            </div>
            <div v-if="isLoggedIn">
              <span class="user-title" style="margin-right: 5px; color: aliceblue;">Welcome,</span>
              <span class="user-title" style="margin-right: 10px; color: aliceblue" slot="reference">
                  {{ this.$store.state.user.loginName }}
                </span>

              <el-tooltip placement="bottom" trigger="hover" content="My Cart">
                <el-badge :value="dynamicCartNum" :max="9" class="item" style="margin-right: 10px">
                  <el-button type="warning" icon="el-icon-shopping-cart-full" @click="myProfile('cart')"></el-button>
                </el-badge>
              </el-tooltip>
              <el-tooltip placement="bottom" trigger="hover" content="My Order">
                <el-button type="success" icon="el-icon-tickets" @click="myProfile('order')"></el-button>
              </el-tooltip>
              <el-tooltip placement="bottom" trigger="hover" content="My Favorite">
                 <el-button class="pink-button" style="margin-right: 10px;" icon="el-icon-star-off" @click="myProfile('favorite')"></el-button>
              </el-tooltip>
              <el-tooltip placement="bottom" trigger="hover" content="My Course">
                <el-badge :value="dynamicMycourseNum" :max="9" class="item" style="margin-right: 10px">
                  <el-button type="info" icon="el-icon-finished" @click="myProfile('course')"></el-button>
                </el-badge>
              </el-tooltip>
              <el-tooltip placement="bottom" trigger="hover" content="My Profile">
                <el-button type="primary" icon="el-icon-user" @click="myProfile()"></el-button>
              </el-tooltip>
              <el-button type="danger" class="logout-btn" @click="logout">Logout</el-button>
            </div>
          </div>
        </el-menu>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>
        © 2025 Gym Zone Fitness Center. All rights reserved.
      </el-footer>
    </el-container>
  </div>
</template>

<script>


import { cartNumber } from "../../api/cart";
import { mycourseNumber } from "../../api/mycourse";

export default {
  name: "HomePage",
  computed: {
    isLoggedIn() {
      // Check if the user is logged in
      if(!this.$store.state.token || this.$store.state.user === undefined || this.$store.state.user === ''
        || this.$store.state.isStaff === undefined || this.$store.state.isStaff === true ) {
        return false;
      } else {
        return true;
      }
    },
    formattedMoney() {
      // Reserve two decimal places for the balance
      let money = this.$store.state.user.money;
      return '$'+parseFloat(money).toFixed(2);
    },
    dynamicCartNum() {
      // Get the cart number from the store
      return Number(this.$store.state.cartNum);
    },
    dynamicMycourseNum() {
      // Get the cart number from the store
      return Number(this.$store.state.mycourseNum);
    },
  },
  data() {
    return {
      cartNum: 0,
      mycourseNum: 0,
      navList: [
        { name: "/home", title: "Home", icon: "el-icon-s-home" },
        { name: "/products", title: "Products", icon: "el-icon-goods" },
        { name: "/CourseManagement", title: "CourseManagement", icon: "el-icon-menu" },
        { name: "/TrainingSchedule", title: "Training Schedule", icon: "el-icon-help" },
        { name: "/dietPlans", title: "Diet Plans", icon: "el-icon-star-on" },
      ],
    };
  },
  methods: {
    login() {
      this.$router.push({path: '/login'});
    },
    signup() {
      this.$router.push({path: '/register'});
    },
    logout() {
      this.$router.replace({path: '/'});
      this.$store.commit('REMOVE_INFO');
      this.$message({
        showClose: true,
        message: 'Logout successfully.',
        center: true,
        type: 'success'
      });
    },
    myProfile(opinion) {
      switch (opinion) {
        case 'cart':
          this.$router.push({path: '/userpanel/cart'});
          break;
        case 'order':
          this.$router.push({path: '/userpanel/order'});
          break;
        case 'course':
          this.$router.push({path: '/userpanel/course'});
          break;
        case 'favorite':
          this.$router.push({path: '/userpanel/favorite'});
          break;
        default:
          this.$router.push({path: '/userpanel/profile'});
          break;
      }
    },
    getCartNumber() {
      const userId = this.$store.state.user.id;
      const data = {};
      cartNumber(data, userId)
        .then(response => {
          this.cartNum = response.data.data;
          // update the cart number in the store
          this.$store.commit('updateCartNum', this.cartNum);
        })
        .catch(error => {
          console.log(error);
        });
    },
    getMycourseNumber() {
      const userId = this.$store.state.user.id;
      const data = {};
      mycourseNumber(data, userId)
        .then(response => {
          this.mycourseNum = response.data.data;
          // update the cart number in the store
          this.$store.commit('updateMycourseNum', this.mycourseNum);
        })
        .catch(error => {
          console.log(error);
        });
    },
  },
  mounted() {
    if(this.isLoggedIn){
      this.getCartNumber();
      this.getMycourseNumber();
    }
  },

};
</script>

<style scoped>
.button-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex: 1;
}

.index {
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
}

.el-container {
  width: 100%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content:space-between;
}

.el-main {
  height: 100%;
  overflow: hidden;
}

.el-footer {
  display: flex;
  height: 30px !important;
  width: 100%;
  align-items: center;
  justify-content: center;
  background-color: #333333;
  color: #ffffff;
}

.pink-button {
  background-color: #f0768b;
  border-color: #f0768b;
  color: white;
}

</style>
