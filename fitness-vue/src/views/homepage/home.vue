<template>
  <el-col>
    <el-row>
      <el-carousel :interval="4000" type="card" height="360px">
        <el-carousel-item v-for="item in 6" :key="item" class="carousel-item">
          <img :src="require(`@/assets/img/banners/${item}.jpg`)" />
        </el-carousel-item>
      </el-carousel>
    </el-row>

    <el-row class="homeRow">
      <!-- Google Map -->
      <el-col :span="12" class="homeColMap">
        <GmapMap
          :center="{ lat: 22.3193, lng: 114.1694 }"
          :zoom="13"
          map-type-id="roadmap"
          style="width: 100%; height: 100%"
        >
          <GmapMarker :position="{ lat: 22.3193, lng: 114.1694 }" />
        </GmapMap>
      </el-col>

      <el-col :span="12" class="homeColText">
        <!--  Google Search Engine -->
        <div class="gcse-search" style="margin-bottom: 20px;"></div>

        <!-- Introduction -->
        <h2 class="Title">Fitness Center</h2>
        <p>
          Welcome to our Fitness Center!<br />
          With state-of-the-art equipment, expert trainers, and a variety of
          dynamic classes, we cater to all fitness levels. Whether you're
          looking to build strength, improve endurance, or simply de-stress, we
          have everything you need to reach your goals.
          <br /><br />
          Join our supportive community and embark on a journey to a healthier,
          happier you. Your fitness journey starts here!
        </p>
      </el-col>
    </el-row>
  </el-col>
</template>

<script>
export default {
  name: "HomePage",
  mounted() {
    if (!document.getElementById("google-cse-script")) {
      const script = document.createElement("script");
      script.id = "google-cse-script";
      script.type = "text/javascript";
      script.async = true;
      script.src = "https://cse.google.com/cse.js?cx=37a57aa6ef6d84e02";
      document.body.appendChild(script);
    }
  },
  methods: {
    requireProductImage(picUrl) {
      try {
        return require(`../../assets/img/banners/${picUrl}.png`);
      } catch (e) {
        return require("../../assets/img/default.jpg");
      }
    }
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #fbfbfb;
}
.el-carousel__item:nth-child(2n + 1) {
  background-color: #ffffff;
}
.carousel-item img {
  object-fit: cover;
  width: 100%;
  height: 100%;
}

.homeRow {
  padding: 20px;
  height: 450px;
  background-color: #ffffff;
}
.homeColMap {
  height: 100%;
  display: flex;
}
.homeColText {
  padding: 10px 20px;
  overflow-y: auto;
}

.Title {
  font-family: "cooper black";
  color: #333333;
  font-size: 250%;
}
</style>
