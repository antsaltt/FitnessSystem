/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : fitness

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 17/04/2025 15:20:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `product_num` int NOT NULL,
  `user_id` int NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE,
  UNIQUE INDEX `cart_cartID_uindex`(`cart_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 1, 1, 1, 'Black Ribbed Seamless Petite Tights');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `comments_id` int NOT NULL AUTO_INCREMENT,
  `comments_mesg` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `comments_time` datetime NOT NULL,
  PRIMARY KEY (`comments_id`) USING BTREE,
  UNIQUE INDEX `comments_commentsID_uindex`(`comments_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 'good', '2020-01-12 11:01:01');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `instructor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `capacity` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Yoga', 'Relax and improve flexibility.', 'Monday 9:00 - 10:00', 'Ms. Lee', 20, 20);
INSERT INTO `course` VALUES (2, 'Pilates', 'Strengthen your core and improve posture.', 'Tuesday 10:00 - 11:00', 'Mr. Wang', 25, 15);
INSERT INTO `course` VALUES (3, 'Spinning', 'High-intensity cardio workout to burn calories.', 'Wednesday 18:00 - 19:00', 'Mr. Zhang', 30, 10);
INSERT INTO `course` VALUES (4, 'Aerobics', 'Aerobic exercise to improve cardiovascular health.', 'Thursday 17:00 - 18:00', 'Ms. Zhao', 20, 25);
INSERT INTO `course` VALUES (5, 'Boxing', 'Increase strength and endurance, release stress.', 'Friday 19:00 - 20:00', 'Mr. Liu', 35, 12);
INSERT INTO `course` VALUES (6, 'Strength Training', 'Build muscle strength and tone your body.', 'Saturday 15:00 - 16:00', 'Mr. Chen', 40, 8);

-- ----------------------------
-- Table structure for diet
-- ----------------------------
DROP TABLE IF EXISTS `diet`;
CREATE TABLE `diet`  (
  `diet_id` int NOT NULL AUTO_INCREMENT,
  `diet_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `diet_description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `diet_category` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `diet_pic_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`diet_id`) USING BTREE,
  UNIQUE INDEX `diet_dietID_uindex`(`diet_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diet
-- ----------------------------
INSERT INTO `diet` VALUES (1, 'Pancakes + Soymilk', 'Mixed grain pancakes (minus the pancakes): 268kcal, one cup of soymilk:68kcal, Total calories: 336kcal. Pancakes and Soymilk make up a nutritionally balanced and tasty breakfast option for those of you who want to get plenty of energy in the morning.', 'Breakfast', 'Breakfast A');
INSERT INTO `diet` VALUES (2, 'Orange + Yogurt', 'One medium-sized orange (about 130g):62 calories, one cup (about 245g) of low-fat plain yogurt:54 calories, Total:216kcal. Orange and Yogurt make up a refreshingly healthy and nutritious breakfast option, especially for those of you looking for a light and tasty morning treat.', 'Breakfast', 'Breakfast B');
INSERT INTO `diet` VALUES (3, 'Whole Wheat Bread + Soymilk', 'Two slices of whole-wheat bread (about 56g): 70 calories, one cup (240ml) of unsweetened soy milk: 80 calories, Total: 220kcal. A breakfast of Whole Wheat Bread and Soymilk is a healthy, nutritionally balanced and suitable choice for all dietary preferences, especially for those of you who want to get plenty of energy in the morning and maintain a healthy lifestyle.', 'Breakfast', 'Breakfast C');
INSERT INTO `diet` VALUES (4, 'Cereal + Milk', 'One cup (about 30g) of regular cereal: 110 calories, One cup (240ml) of whole milk: 150 calories, Total calories: 260kcal. Cereal and Milk are a classic combination of convenience and nutritional balance, popular with people of all ages and especially suitable for those who want to gain energy quickly in the morning.', 'Breakfast', 'Breakfast D');
INSERT INTO `diet` VALUES (5, 'Millet Porridge + Egg', 'One cup of cooked millet porridge (about 174g): 207 calories, one large egg (about 50g): 72 calories, Total: 279kcal. The breakfast consisting of Millet Porridge (millet porridge) and Egg (egg) is a nutritious and traditional choice, especially suitable for those of you who wish to consume sufficient energy and quality protein in the morning.', 'Breakfast', 'Breakfast E');
INSERT INTO `diet` VALUES (6, 'Corn + Milk', 'One medium-sized piece of boiled corn (about 154 grams): 123 calories, one cup (240 ml) of whole milk: 150 calories, Total: 273kcal. A breakfast of Corn and Milk is a nutritious and easy-to-make option, especially for those of you who want to get enough energy and balanced nutrition in the morning.', 'Breakfast', 'Breakfast F');
INSERT INTO `diet` VALUES (7, 'Burger + Black coffee + Cucumber', 'A regular burger (includes whole wheat bun, beef patty, lettuce, tomato, and a small amount of sauce): 300 calories, a cup of black coffee (unsweetened and dairy-free, about 240 ml): 2 calories, and a medium-sized cucumber (about 300 grams): 45 calories, Total calories: 347kcal. Burger+ Black Coffee+ Cucumber makes up a well-balanced, low-calorie and protein and fiber-rich lunchtime fitness meal, especially suitable for those of you who wish to consume sufficient nutrients and maintain a healthy', 'Brunch', 'Brunch A');
INSERT INTO `diet` VALUES (8, 'Vegetable bun + Tea Egg + Milk', 'A plain veggie bun (approx. 100g): 200 calories, A tea egg (approx. 50g): 70 calories, A cup (240ml) of whole milk: 150 calories, Total calories: 420kcal. Vegetable Bun, Tea Egg and Milk make up a nutritious, balanced and East-meets-West brunch option, especially suitable for those of you who wish to consume a variety of nutrients in the morning.', 'Brunch', 'Brunch B');
INSERT INTO `diet` VALUES (9, 'Dumplings + Soymilk', '10 plain pork and vegetable dumplings (about 200g): 410 calories, one cup (240ml) of unsweetened soy milk: 80 calories, Total: 490kcal. Vegetable Bun, Tea Egg and Milk is a well-balanced, nutritious, rich, and East-meets-West brunch option, especially for those who want to get a variety of nutrients and maintain a healthy diet at brunch time. It is a well-balanced, nutritious option with Chinese and Western flavors, especially for those who want to get a variety of nutrients at brunch time and m', 'Brunch', 'Brunch C');
INSERT INTO `diet` VALUES (10, 'Sandwich + Cucumber + Black Coffee', 'A regular sandwich (including whole-wheat bread, ham, cheese, lettuce and tomato): 300 calories, a medium-sized cucumber (about 300 grams): 45 calories, and a cup of black coffee (unsweetened and non-dairy, about 240 milliliters): 2 calories. Total calories: 347kcal. Sandwich, Cucumber and Black Coffee is a nutritionally balanced, low-calorie and convenient brunch option, especially for those of you who want to consume enough energy at brunch time and maintain a healthy diet.', 'Brunch', 'Brunch D');
INSERT INTO `diet` VALUES (11, 'Bagel + Chicken Breast + Yogurt', 'One plain bagel (approx. 100g): 250 calories, one grilled chicken breast (approx. 100g): 165 calories, one cup (approx. 150g), low-fat plain yogurt: 100 calories, Total: 515kcal. Bagel, Chicken Breast, and Yogurt make for a well-balanced, protein-packed, and energy-packed brunch! It is a well-balanced, protein-packed and energy-packed option, especially for those who want to get enough nutrients at brunch time and maintain a healthy diet.', 'Brunch', 'Brunch E');
INSERT INTO `diet` VALUES (12, 'Oat Milk + Egg + Tomato', 'One cup (240ml) of Oat Milk: 120 calories, one large egg (about 50g): 72 calories, three medium-sized tomatoes (about 369g): 75 calories, Total: 367kcal. Oat Milk, Egg and Tomato make up a light, well-balanced and low-calorie brunch option that is especially suitable for those who wish to take in a variety of nutrients at brunch time and maintain a healthy diet.', 'Brunch', 'Brunch F');
INSERT INTO `diet` VALUES (13, 'Beans with Celery + Rice + Pork with Cauliflower', '100g Stir Fried Dried Beans with Celery: 100 calories, Bowl of White Rice (approx. 150g): 200 calories, 150g Stir Fried Pork and Cauliflower: 250 calories, Total: 550kcal. Stir Fried Dried Beans with Celery, Rice and Stir Fried Pork with Cauliflower make up a nutritionally balanced, protein- and fiber-rich option that is especially good for those of you looking to get a well-rounded nutritional boost at lunchtime and maintain a healthy diet.', 'Lunch', 'Lunch A');
INSERT INTO `diet` VALUES (14, 'Chicken Breast + Purple Potato + Cold Okra', 'One Roasted Chicken Breast (approx. 100g): 165 calories, One Medium Purple Potato (approx. 150g): 115 calories, One Cold Okra (approx. 100g): 35 calories, Total: 315kcal. Chicken Breast, Purple Potato, and Cold Okra make a nutritious, low-fat and high-fiber choice for lunch. It is a nutritious, low-fat, low-calorie and high-fiber option that is especially suitable for those of you who want to get enough nutrients at lunchtime and maintain a healthy diet.', 'Lunch', 'Lunch B');
INSERT INTO `diet` VALUES (15, 'Steak + Spaghetti + Cucumber', 'One serving of steak (about 150 grams): 250 calories, one serving of pasta (about 200 grams, without sauce): 220 calories, one medium-sized cucumber (about 300 grams): 45 calories. Total calories: 515kcal. Steak, Spaghetti and Cucumber makes a nutritionally balanced, protein- and carbohydrate-rich choice for a busy weekday lunch or a leisurely weekend lunch.', 'Lunch', 'Lunch C');
INSERT INTO `diet` VALUES (16, 'Rice + Pork with Mushrooms + Lettuce in Oyster Sau', 'One bowl of white rice (approx. 150g): 200 calories, one serving of Stir-fried Pork with Shiitake Mushrooms (approx. 200g): 300 calories, one serving of Lettuce in Oyster Sauce (approx. 100g): 50 calories, Total: 550kcal. Rice, Stir-fried Pork with Shiitake Mushrooms and Lettuce in Oyster Sauce make up a nutritionally balanced, protein and carbohydrate-rich option for a weekday lunch or family dinner.', 'Lunch', 'Lunch D');
INSERT INTO `diet` VALUES (17, 'Potatoes + Pork with Garlic Moss + Tomato Egg Soup', 'One Steamed Potatoes (approx. 200g): 180 calories, One Stir-fried Pork with Garlic Moss (approx. 200g): 300 calories, One Tomato and Egg Soup (approx. 300g): 100 calories, Total: 580kcal. Steamed Potatoes, Stir-fried Pork with Garlic Moss and Tomato and Egg Soup are a nutritious and well-balanced choice for a family lunch or dinner. Egg Soup make a nutritious, well-balanced and tasty choice for a family lunch or dinner.', 'Lunch', 'Lunch E');
INSERT INTO `diet` VALUES (18, 'Stone Pot Bibimbap + Pomelo', 'One Stone Pot Bibimbap (approx. 400g): 500 calories, One Pomelo (approx. 400g): 200 calories, Total: 700kcal. Stone Pot Bibimbap and Pomelo make up a delicious, nutritious option for lunch, blending Korean cuisine with fresh fruits to provide a rich nutritional and flavorful experience.', 'Lunch', 'Lunch F');
INSERT INTO `diet` VALUES (19, 'Rice + Beans&Celery + Beef&Broccoli', 'One bowl of white rice (approx. 150g): 200 calories, one serving of Stir-fried Dried Beans with Celery and Green Pepper (approx. 200g): 150 calories, one serving of Stir-fried Beef with Broccoli (approx. 200g):250 calories, Total: 600kcal.Rice, Stir-fried Dried Beans with Celery and Green Pepper and Stir-fried Beef with Broccoli comprise a nutritious, protein- and vegetable-rich option for a weekday lunch.', 'Lunch', 'Lunch G');
INSERT INTO `diet` VALUES (20, 'Sushi + Shredded Sea Vegetables', 'One serving of Sushi (approx. 200g): 300 calories, one serving of Shredded Sea Vegetables (approx. 100g): 50 calories, Total: 350kcal. Sushi and Shredded Sea Vegetables make up a tasty, lightweight option for dinner, fusing Japanese cuisine with sea vegetables to add a richness of flavors and nutrients to the meal.', 'Dinner', 'Dinner A');
INSERT INTO `diet` VALUES (21, 'Prawns + Pumpkin + Cucumber', 'One serving of fresh shrimp (approx. 150g): 120 calories, one serving of pumpkin (approx. 200g): 80 calories, one medium-sized cucumber (approx. 300g): 45 calories, Total: 245kcal. Shrimp, Pumpkin and Cucumber make up a low-calorie, highly nutritious choice for a dinner that provides a wealth of proteins, vitamins and minerals for those who want to maintain a healthy diet! .', 'Dinner', 'Dinner B');
INSERT INTO `diet` VALUES (22, 'Tofu Soup + Sweet Potato + Mushroom Fry with Veget', 'One serving of Seaweed and Tofu Soup (approx. 300g): 100 calories, one serving of Sweet Potato (approx. 200g): 180 calories, one serving of Vegetable Stir-Fry with Mushrooms (approx. 200g): 100 calories, Total: 380kcal.Seaweed and Tofu Soup, Sweet Potato and Mushroom Stir-Fry with Vegetables make up a nutritious, low-calorie option for dinner, providing a rich source of protein, vitamins and minerals.', 'Dinner', 'Dinner C');
INSERT INTO `diet` VALUES (23, 'Scrambled Eggs&Tomato + Macaroni + Broccoli', 'One serving of Scrambled Eggs with Tomato (approx. 200g): 250 calories, one serving of Macaroni (approx. 50g): 175 calories, one serving of Broccoli (approx. 200g): 70 calories, Total: 495kcal. Scrambled Eggs with Tomato, Macaroni, and Broccoli make up a simple, tasty, and well-balanced dinner option that provides a rich source of protein, carbohydrates and vegetable fiber for a dinner that needs to satisfy hunger and nutritional needs.', 'Dinner', 'Dinner D');
INSERT INTO `diet` VALUES (24, 'Beef + Corn + Cold Okra', 'One serving of Beef (approx. 100g): 200 calories, one serving of Corn (approx. 150g): 120 calories, one serving of Cold Okra (approx. 150g): 50 calories, Total: 370kcal. Beef, Corn, and Cold Okra make up a nutritious, hearty, and delicious choice for dinner, providing a good dose of protein, carbohydrates, and vegetable fiber.', 'Dinner', 'Dinner E');
INSERT INTO `diet` VALUES (25, 'Chicken Thighs + Rice + Sauteed Bok Choy', 'One serving of Chicken Thighs (approx. 150g): 300 calories, one small bowl of white rice (approx. 75g): 100 calories, one serving of sauteed bok choy (approx. 200g): 50 calories, Total: 450kcal. Chicken Thighs, Rice, and Sauteed Bok Choy make up a well-balanced, tasty option for dinner, providing a plenty of protein, carbohydrates and vegetable fiber.', 'Dinner', 'Dinner F');
INSERT INTO `diet` VALUES (26, 'Steamed Fish + Mixed Rice + Lettuce', 'One serving of Steamed Fish (approx. 150g): 153 calories, One bowl of Mixed Rice (approx. 100g): 141 calories, One serving of Lettuce (approx. 200g): 36 calories, Total: 330kcal. Steamed Fish, Mixed Rice and Lettuce make up a light, healthy, low-calorie and highly nutritious choice for dinner, providing an abundance of protein, carbohydrates and vegetable fiber.', 'Dinner', 'Dinner G');

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `favorite_id` int NOT NULL AUTO_INCREMENT,
  `diet_id` int NOT NULL,
  `user_id` int NOT NULL,
  `diet_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`favorite_id`) USING BTREE,
  UNIQUE INDEX `favorite_favoriteID_uindex`(`favorite_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 1, 1, 'Pancakes + Soymilk');

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson`  (
  `lesson_id` int NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `lesson_description` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `lesson_category` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `lesson_instructor` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `lesson_time` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`lesson_id`) USING BTREE,
  UNIQUE INDEX `lesson_lessonID_uindex`(`lesson_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, 'Yoga for Beginners - Aerobic', 'A beginner-friendly yoga class focusing on basic postures and breathing techniques.', 'Aerobic', 'Emma Wilson', 'Monday 8:00 AM - 9:00 AM');
INSERT INTO `lesson` VALUES (2, 'Advanced Pilates - Aerobic', 'An advanced class that targets core strength, flexibility, and overall body conditioning.', 'Aerobic', 'Sophia Brown', 'Wednesday 6:00 PM - 7:00 PM');
INSERT INTO `lesson` VALUES (3, 'HIIT Workout - Mixed Exercise', 'High-Intensity Interval Training for maximum calorie burn and cardiovascular fitness.', 'Mixed Exercise', 'James Smith', 'Friday 5:30 PM - 6:30 PM');
INSERT INTO `lesson` VALUES (4, 'Spin Class - Aerobic', 'An energetic indoor cycling class set to motivating music.', 'Aerobic', 'Olivia Davis', 'Tuesday 7:00 PM - 8:00 PM');
INSERT INTO `lesson` VALUES (5, 'Zumba Dance - Aerobic', 'A fun, dance-based cardio workout with Latin music and rhythms.', 'Aerobic', 'Isabella Johnson', 'Thursday 6:30 PM - 7:30 PM');
INSERT INTO `lesson` VALUES (6, 'Strength Training - Anaerobic', 'A class focused on building muscle strength using weights and resistance bands.', 'Anaerobic', 'Liam Martinez', 'Saturday 10:00 AM - 11:00 AM');
INSERT INTO `lesson` VALUES (7, 'Body Pump - Anaerobic', 'A full-body workout using a barbell to strengthen and tone your body.', 'Anaerobic', 'Ava Rodriguez', 'Monday 5:00 PM - 6:00 PM');
INSERT INTO `lesson` VALUES (8, 'Kickboxing - Mixed Exercise', 'A high-energy cardio class combining martial arts techniques with fast-paced cardio.', 'Mixed Exercise', 'Mia Hernandez', 'Wednesday 7:00 PM - 8:00 PM');
INSERT INTO `lesson` VALUES (9, 'Aqua Aerobics - Aerobic', 'A low-impact workout in the pool suitable for all fitness levels.', 'Aerobic', 'Benjamin Lopez', 'Friday 9:00 AM - 10:00 AM');
INSERT INTO `lesson` VALUES (10, 'Step Aerobics - Aerobic', 'A choreographed cardio workout using a step platform.', 'Aerobic', 'Charlotte Gonzalez', 'Tuesday 6:00 PM - 7:00 PM');
INSERT INTO `lesson` VALUES (11, 'Core Blast - Anaerobic', 'A short, intense class focused on strengthening the core muscles.', 'Anaerobic', 'Amelia Lee', 'Monday 12:00 PM - 12:30 PM');
INSERT INTO `lesson` VALUES (12, 'Prenatal Yoga - Aerobic', 'A gentle yoga class designed for pregnant women to enhance relaxation and flexibility.', 'Aerobic', 'Harper King', 'Thursday 10:00 AM - 11:00 AM');
INSERT INTO `lesson` VALUES (13, 'Boot Camp - Mixed Exercise', 'An intense outdoor workout that combines strength training and cardio exercises.', 'Mixed Exercise', 'Ethan Scott', 'Saturday 8:00 AM - 9:00 AM');
INSERT INTO `lesson` VALUES (14, 'Barre - Anaerobic', 'A ballet-inspired workout focusing on low-impact, high-intensity movements.', 'Anaerobic', 'Lucas Wright', 'Wednesday 5:00 PM - 6:00 PM');
INSERT INTO `lesson` VALUES (15, 'Functional Training - Mixed Exercise', 'A workout that improves everyday movements and overall body strength.', 'Mixed Exercise', 'Henry Green', 'Friday 7:00 PM - 8:00 PM');
INSERT INTO `lesson` VALUES (16, 'Tai Chi - Aerobic', 'A gentle form of martial arts focusing on slow, controlled movements.', 'Aerobic', 'Ella Adams', 'Tuesday 9:00 AM - 10:00 AM');
INSERT INTO `lesson` VALUES (17, 'Cycling Strength - Mixed Exercise', 'A cycling class that incorporates strength training intervals.', 'Mixed Exercise', 'Sebastian Nelson', 'Monday 6:30 PM - 7:30 PM');
INSERT INTO `lesson` VALUES (18, 'TRX Suspension Training - Anaerobic', 'A workout using TRX straps to perform a variety of exercises.', 'Anaerobic', 'Victoria Carter', 'Thursday 5:30 PM - 6:30 PM');
INSERT INTO `lesson` VALUES (19, 'Meditation and Relaxation - Aerobic', 'A class focusing on guided meditation techniques for stress relief.', 'Aerobic', 'David Mitchell', 'Friday 12:00 PM - 1:00 PM');
INSERT INTO `lesson` VALUES (20, 'CrossFit - Mixed Exercise', 'A high-intensity workout that combines various functional movements.', 'Mixed Exercise', 'Daniel Perez', 'Saturday 11:00 AM - 12:00 PM');

-- ----------------------------
-- Table structure for mycourse
-- ----------------------------
DROP TABLE IF EXISTS `mycourse`;
CREATE TABLE `mycourse`  (
  `mycourse_id` int NOT NULL AUTO_INCREMENT,
  `lesson_id` int NOT NULL,
  `lesson_name` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `lesson_instructor` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `lesson_time` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`mycourse_id`) USING BTREE,
  UNIQUE INDEX `mycourse_mycourseID_uindex`(`mycourse_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mycourse
-- ----------------------------

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_customer_id` int NULL DEFAULT NULL,
  `order_price` double NOT NULL,
  `order_comment` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `order_time` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `order_info_name` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `order_info_number` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `order_info_price` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `order_address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `order_status` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_orderID_uindex`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order1
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `product_category` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_pic_url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `product_price` double NOT NULL DEFAULT 10,
  PRIMARY KEY (`product_id`) USING BTREE,
  UNIQUE INDEX `product_productID_uindex`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Black Ribbed Seamless Petite Tights', 'Feel luxury and cozy at once in our Black Luxe Seamless Tights. Let the soft fabric embraces your body while the luxury design with an elegant seamless structure embraces your soul! Combine it with other Luxe Seamless items for subtle homewear and everyday look. Fabric: 92% Polyamide 8%Elastane', 'Lady Clothes', 'Black Ribbed Seamless Petite Tights', 60);
INSERT INTO `product` VALUES (2, 'Black Sculpting Strappy Bralette', 'Comfortable and slightly supportive sports bra with outstanding details and an elegant seamless structure. Combine it with our Luxe Seamless Tights for a subtle homewear and everyday look. Fabric: 55% Nylon, 40% Polyester, 5% Elastane', 'Lady Clothes', 'Black Sculpting Strappy Bralette', 40);
INSERT INTO `product` VALUES (3, 'Black Sense Midi Biker Shorts', 'Ribbed Seamless Midi Biker Shorts have the same iconic aim’n design as the tights, but in a shorter version. These high-waisted biker shorts are made of a soft and flexible four-way knitted ribbed fabric that follows the bodys movements in a natural way and creates a beautiful shaping effect. Mix and match with other ribbed garments and create dreamy outfits that boost you while you do the activities you love, both at and outside the gym. In other words, these shorts are a must-have for your war', 'Lady Clothes', 'Black Sense Midi Biker Shorts', 30);
INSERT INTO `product` VALUES (4, 'Black Sense Skort', 'Get ready to serve looks in this smashing skirt! Sense Skort is made from our signature recycled, brushed Sense fabric and has integrated inner shorts - perfect for tennis, padel, golf or everyday life. The flowy, moisture-wicking skort has a comfortable high waist and pockets on both sides. The skort has lasercut edges and a monochrome aim’n logo at front. Fit: Regular Material: 80% recycled polyester, 20% spandex.', 'Lady Clothes', 'Black Sense Skort', 35);
INSERT INTO `product` VALUES (5, 'Black Soft Basic Short Sleeve', 'The perfect t-shirt for all your outfits! Cafe au Lait Serif Boxy T-Shirt is made from sturdy 100% organic cotton and has a boxy, slightly loose fit. The t-shirt has a rounded neckline with ribbed details and a printed seasonal aim’n print at the front. Fit: Boxy Material: 100% organic cotton. ', 'Lady Clothes', 'Black Soft Basic Short Sleeve', 45);
INSERT INTO `product` VALUES (6, 'Body Power Jump Rope', 'The Body Power Jump Rope is your essential fitness companion for cardiovascular workouts and agility training. Designed for durability and performance, this jump rope features high-quality materials that ensure smooth and effortless rotations, allowing you to achieve maximum results with each workout session', 'Equipments', 'Body Power Jump Rope', 10);
INSERT INTO `product` VALUES (7, 'Cap-Sky Black', 'Sky blue cap is the perfect accessory for sunny days or outdoor activities. Made from lightweight and breathable material, it offers comfort and protection from the sun. The refreshing sky blue color adds a pop of color to your outfit while keeping you cool and stylish.', 'Gentleman Clothes', 'Cap-Sky Black', 15);
INSERT INTO `product` VALUES (8, 'Dumbbells', 'Dumbbells are essential strength training tools that offer a wide range of benefits for building muscle, improving endurance, and enhancing overall fitness. Available in various weights and designs, dumbbells provide versatility for users of all fitness levels, from beginners to advanced athletes. ', 'Equipments', 'Dumbbells', 10);
INSERT INTO `product` VALUES (9, 'Grey Melange Ease Ribbed Shorts', 'The ultimate shorts for lounging, traveling and comfort 24/7! Made from a super-soft cotton blend, our Ease Ribbed Shorts have a relaxed fit and feature a lightweight, ribbed fabric. Match with our Ease Ribbed Tank Top to create a matching loungewear set. Material: 48% cotton, 48% polyester, 4% elastan. Fit: Relaxed.', 'Lady Clothes', 'Grey Melange Ease Ribbed Shorts', 25);
INSERT INTO `product` VALUES (10, 'Gym Flooring', 'Gym flooring is an essential component of any fitness space, providing protection, stability, and comfort during workouts. Designed to withstand heavy foot traffic and equipment use, gym flooring creates a safe and supportive surface for exercise, reducing the risk of injury and damage to floors. ', 'Equipments', 'Gym Flooring', 12);
INSERT INTO `product` VALUES (11, 'Hoodie-Navy', 'Navy hoodie is a versatile and stylish addition to any wardrobe. Made from soft and cozy cotton-blend fabric, it offers both comfort and warmth. The classic navy color adds a touch of sophistication while remaining easy to pair with various outfits.', 'Gentleman Clothes', 'Hoodie-Navy', 45);
INSERT INTO `product` VALUES (12, 'Joggers -Black', 'Black joggers are the epitome of comfort and style. Crafted from soft and breathable fabric, they provide all-day comfort whether you are lounging at home or running errands. The classic black color adds a touch of sophistication to your casual look, while the tapered fit gives them a modern edge.', 'Gentleman Clothes', 'Joggers -Black', 35);
INSERT INTO `product` VALUES (13, 'Joggers-Light Grey Marl', 'Light grey marl joggers combine style and comfort for your everyday adventures. Crafted from soft and durable fabric, they offer a cozy feel while maintaining a sleek appearance. The light grey marl color adds a modern touch to your casual look, making them versatile for both lounging at home and running errands in style. ', 'Gentleman Clothes', 'Joggers-Light Grey Marl', 30);
INSERT INTO `product` VALUES (14, 'Kettlebells', 'Kettlebells are versatile and effective fitness tools designed for strength, endurance, and functional training. With their distinctive shape and handle, kettlebells offer a unique way to engage multiple muscle groups while performing dynamic movements such as swings, cleans, and snatches. ', 'Equipments', 'Kettlebells', 15);
INSERT INTO `product` VALUES (15, 'Medicine Ball', 'The medicine ball is a versatile fitness tool used for strength training, rehabilitation, and functional exercises. Made of durable materials such as rubber or vinyl, the medicine ball is designed to withstand impact and bouncing, making it suitable for a wide range of exercises.', 'Equipments', 'Medicine Ball', 10);
INSERT INTO `product` VALUES (16, 'Musashi Energy Single Cans', 'For sports people looking for a performance boosting functional beverage charged by more than just caffeine. Scientifically formulated to improve stamina, aid muscle recovery, and improve mental focus. Available in Purple Grape, Blue Raspberry, Fruit Crush, and Mix it Up packs.', 'Food', 'Musashi Energy Single Cans', 5);
INSERT INTO `product` VALUES (17, 'Musashi Night Time Aminos', 'Musashi Night Time Aminos is a scientifically formulated, high performance recovery blend that delivers a matrix of essential Branched Chain Amino Acids (BCAAs), conditional amino acids, minerals including zinc and magnesium, as well as calmative herbal extracts.', 'Food', 'Musashi Night Time Aminos', 12);
INSERT INTO `product` VALUES (18, 'Musashi Protein Energy Single Bars', 'Musashi Protein + Energy Single Bar has been scientifically formulated with a 1:1 protein and carb blend and 1000mg of creatine monohydrate. A delicious and wholesome bar loaded with 15g of protein, it harnesses the nutritional goodness of oats to supply your body with energy before, during, or after sport or exercise.', 'Food', 'Musashi Protein Energy Single Bars', 3);
INSERT INTO `product` VALUES (19, 'Musashi Protein Single Cookie', 'Musashi Protein Cookie is loaded with 15g of quality protein, is low in sugar and filled with delicious choc chunks. With a \"melt in your mouth\", soft bake texture, it is guaranteed to fulfil that sweet craving or as a convenient, protein filled snack on the go.', 'Food', 'Musashi Protein Single Cookie', 4);
INSERT INTO `product` VALUES (20, 'Muscle Nation Natural Daily Greens', 'Loaded with antioxidants and anti-inflammatory ingredients, except, without that unpleasant grassy taste. Bursting with superfoods such as barley grass, spirulina, kale and celery, and a whole bunch of potent ingredients that work to boost your digestive health and immunity - you will be fighting fit in no time at all.Each Muscle Nation 100% Natural Daily Greens scoop is packed with over 20 vitamins and minerals that have all sorts of anti-inflammatory properties and gut health ingredients which', 'Food', 'Muscle Nation Natural Daily Greens', 8);
INSERT INTO `product` VALUES (21, 'Muscle Nation Protein Water', 'Muscle Nation Protein Water a refreshing clear protein water supplement which is a light alternative to the usual milky protein shake. With the benefits of protein, collagen, electrolytes and BCAAs, the Muscle Nation Protein Water is a fruity and thirst quenching drink to use any time of the day.', 'Food', 'Muscle Nation Protein Water', 6);
INSERT INTO `product` VALUES (22, 'Push-up Board', 'The Push-Up Board is a versatile and effective tool for strengthening your upper body and core muscles. Featuring multiple color-coded handgrips, this board allows you to target different muscle groups by simply changing your hand placement. ', 'Equipments', 'Push-up Board', 17);
INSERT INTO `product` VALUES (23, 'Socks-Onyx', ' These black socks are made from high-quality pure cotton, ensuring comfort, softness, and excellent breathability. With a simple design, they are suitable for everyday wear and can be paired with various types of footwear and outfits.', 'Gentleman Clothes', 'Socks-Onyx', 9);
INSERT INTO `product` VALUES (24, 'Stringer-Black', 'Black stringer is designed for both performance and style. Crafted from breathable and moisture-wicking fabric, it keeps you cool and dry during intense workouts. The sleek black color adds a touch of sophistication to your gym attire, while the stringer style offers freedom of movement and ventilation. ', 'Gentleman Clothes', 'Stringer-Black', 20);
INSERT INTO `product` VALUES (25, 'Tights-Black', ' These black tights are crafted from a blend of high-quality materials, offering a sleek and comfortable fit. Perfect for adding a touch of elegance to any outfit, they provide both style and practicality. ', 'Gentleman Clothes', 'Tights-Black', 25);
INSERT INTO `product` VALUES (26, 'White Edge Core Racerback Singlet', 'Our Sense Bralette is made from our luxuriously soft Sense fabric and is a hybrid between a bra and a tank top. The Sense Bralette has a flattering v-cut front and back, removable pads and a metal aim’n-logo badge. Combine with Sense Tights to create a matching set. Fit: Tight Material: 80% recycled polyester, 20% spandex', 'Lady Clothes', 'White Edge Core Racerback Singlet', 20);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` char(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `address` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `money` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `staff_Staffname_uindex`(`login_name` ASC) USING BTREE,
  UNIQUE INDEX `staff_ID_uindex`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'S1', 'S1', 'Male', '1234567890@test.com', 'Auckland', 's1', 200);
INSERT INTO `staff` VALUES (2, 'S2', 'S2', 'Male', '1234567890@test.com', 'Auckland', 's2', 200);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `address` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Money` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UserAccount_ID_uindex`(`id` ASC) USING BTREE,
  UNIQUE INDEX `UserAccount_Username_uindex`(`login_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'M1', 'm1', 'M1', 'Male', '1234567890@test.com', 'Tianjin City', 200);
INSERT INTO `user` VALUES (2, 'M2', 'm2', 'M2', 'Male', '1234567890@test.com', 'Albany, Auckland', 200);

SET FOREIGN_KEY_CHECKS = 1;
