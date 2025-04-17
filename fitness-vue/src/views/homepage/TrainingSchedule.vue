<script setup>

</script>

<template>
  <div>
    <el-card class="about-card">
      <div class="About_title">
        <h2 style="text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8);">Gym Zone in Auckland</h2>
        <p style="text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.8);">
          We hope our fitness center can provide every user with a great experience!
        </p>
      </div>

      <!-- Training Plan Table -->
      <el-row>
        <el-col :span="24">
          <h3 style="margin-bottom: 20px; text-align: center;">Fitness Training Schedule</h3>
          <el-table :data="trainingPlanData" border stripe style="width: 100%">
            <el-table-column prop="month" label="Month"></el-table-column>
            <el-table-column prop="activity" label="Activity"></el-table-column>
            <el-table-column prop="trainer" label="Trainer"></el-table-column>
            <el-table-column prop="time" label="Time"></el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped>
.about-card{
  width: 80%;
  margin: 0 auto;
  background-color: #EDEBE9;
}

.About_title{
  background-image: url("../../assets/img/background.jpg");
  background-size: cover;
  background-position: center;
  color: white;
  text-align: center;
  height: 150px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.image-container{
  width: 200px;
  height: 200px;
  border-radius: 50%;
  overflow: hidden;
  transition: transform 0.5s;
  margin-top: 10px;
  margin-left: 30%;
}

.rotating-image {
  width: 100%;
  height: 100%;
}

.image-container:hover {
  transform: rotate(90deg);
}

.content{
  text-align: left;
  margin-top: -1px;
}

.limitheight{
  height: 700px;
}

.right-image-coinater{
  margin-top: 3%;
  margin-left: 10%;
  height:45%;
  width: 80%;
  background: #ffffff;
  border-radius: 5px;
  overflow: hidden;
  box-shadow: 5px 5px 5px 2px hsl(0, 0%, 50%);
}

.des{
  padding: 1%;
}

.right-picture{
  width: 100%;
  height: 70%;
  object-fit: cover;
  transition: transform 1s;
  transform: scale(1);
}

.right-picture:hover {
  transform: scale(1.2);
  transform-origin: center center;
}

</style>

<script>
export default {
  data() {
    return {
      trainingPlanData: [
        { month: 'January', activity: 'Strength Training', trainer: 'John Doe', time: 'Mon/Wed/Fri, 8:00 AM - 9:00 AM' },
        { month: 'January', activity: 'Cardio Kickboxing', trainer: 'Emily Smith', time: 'Tue/Thu, 5:00 PM - 6:00 PM' },
        { month: 'February', activity: 'Yoga & Meditation', trainer: 'Sarah Johnson', time: 'Mon/Wed, 9:30 AM - 10:30 AM' },
        { month: 'February', activity: 'HIIT Workout', trainer: 'David Brown', time: 'Tue/Thu, 7:00 PM - 8:00 PM' },
        { month: 'March', activity: 'Pilates', trainer: 'Rachel Miller', time: 'Wed/Fri, 10:00 AM - 11:00 AM' },
        { month: 'April', activity: 'Zumba Fitness', trainer: 'Michael Clark', time: 'Mon/Wed, 6:30 PM - 7:30 PM' },
        { month: 'May', activity: 'CrossFit Training', trainer: 'Jessica Taylor', time: 'Tue/Thu, 6:00 AM - 7:00 AM' },
        { month: 'June', activity: 'Nutrition and Meal Planning Seminar', trainer: 'Olga Petrovna', time: 'Tue/Wed/Fri, 8:00 PM - 9:00 PM' },
        { month: 'July', activity: 'Bodybuilding Technique Clinic', trainer: 'Mary Lee', time: 'Mon/Tue/Thu, 3:00 PM - 5:00 PM' },
        { month: 'August', activity: 'CrossFit Style Workout', trainer: 'Amanda Rodriguez', time: 'Mon/Wed, 10:30 AM - 11:30 AM' },
        { month: 'September', activity: 'Aqua Aerobics Session', trainer: 'Laura Baker', time: 'Tue/Sat, 7:30 PM - 9:00 PM' },
        { month: 'October', activity: 'Barre Fitness Class', trainer: 'Daniel Scott', time: 'Wed/Sun, 7:30 PM - 8:30 PM' },
        { month: 'November', activity: 'TRX Suspension Training', trainer: 'Ashley Adams', time: 'Wed/Fri, 8:30 PM - 9:30 PM' },
        { month: 'December', activity: 'Indoor Cycling (Spin) Session', trainer: 'Robert Taylor', time: 'Mon/Tue/Thu, 9:00 AM - 11:00 AM' },
      ]
    }
  }
}
</script>
