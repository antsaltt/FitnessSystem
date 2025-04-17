package com.groupI.fitness.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "lesson")
public class Lesson {

    @TableId(type = IdType.AUTO)
    private Integer lessonId;
    private String lessonName;

    private String lessonDescription;
    private String lessonCategory;
    private String lessonInstructor;
    private String lessonTime;

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }



    public String getLessonCategory() {
        return lessonCategory;
    }

    public void setLessonCategory(String lessonCategory) {
        this.lessonCategory = lessonCategory;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }



    public String getLessonInstructor() {
        return lessonInstructor;
    }

    public void setLessonInstructor(String lessonInstructor) {
        this.lessonInstructor = lessonInstructor;
    }


    public Lesson(String lessonId, String lessonName, String lessonDescription,String lessonCategory,String lessonInstructor, String lessonTime) {
         this.lessonId= Integer.valueOf(lessonId);
        this.lessonName = lessonName;
        this.lessonDescription = lessonDescription;
        this.lessonInstructor = lessonInstructor;
        this.lessonTime = lessonTime;
        this.lessonCategory = lessonCategory;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + lessonId +
                ", lessonName='" + lessonName + '\'' +
                ", lessonDescription='" + lessonDescription + '\'' +
                ", lessonCategory='" + lessonCategory + '\'' +
                ", lessonInstructor='" + lessonInstructor + '\'' +
                ", lessonTime='" + lessonTime + '\'' +
                '}';
    }


    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }


}
