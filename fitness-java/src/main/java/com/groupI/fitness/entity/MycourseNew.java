package com.groupI.fitness.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "mycoursenew")
public class MycourseNew {
    @TableId(type = IdType.AUTO)
    private Integer mycourseId;
    private Integer lessonId;
    private String lessonName;
    private String lessonInstructor;
    private String lessonTime;
    private Integer userId;

    public MycourseNew() {
    }

    public MycourseNew(Integer mycourseId, Integer lessonId, String lessonName, String lessonInstructor, String lessonTime, Integer userId) {
        this.mycourseId = mycourseId;
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonInstructor = lessonInstructor;
        this.lessonTime = lessonTime;
        this.userId= userId;
    }

    @Override
    public String toString() {
        return "Mycourse{" +
                "mycurseId=" + mycourseId +
                ", lessonId=" + lessonId +
                ", lessonName=" + lessonName +
                ", lessonInstructor=" + lessonInstructor +
                ", lessonTime=" + lessonTime +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Integer getMycourseId() {
        return mycourseId;
    }

    public void setMycourseId(Integer mycourseId) {
        this.mycourseId = mycourseId;
    }

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

    public String getLessonInstructor() {
        return lessonInstructor;
    }

    public void setLessonInstructor(String lessonInstructor) {
        this.lessonInstructor = lessonInstructor;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
