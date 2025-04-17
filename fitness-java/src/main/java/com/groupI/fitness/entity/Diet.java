package com.groupI.fitness.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName(value = "diet")
public class Diet {
    @TableId(type = IdType.AUTO)
    private Integer dietId;
    private String dietName;
    private String dietCategory;
    private String dietDescription;
    private String dietPicUrl;


    public Integer getDietId() {
        return dietId;
    }

    public void setDietId(Integer dietId) {
        this.dietId = dietId;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDietCategory() {
        return dietCategory;
    }

    public void setDietCategory(String dietCategory) {
        this.dietCategory = dietCategory;
    }

    public String getDietDescription() {
        return dietDescription;
    }

    public void setDietDescription(String dietDescription) {
        this.dietDescription = dietDescription;
    }

    public String getDietPicUrl() {
        return dietPicUrl;
    }

    public void setDietPicUrl(String dietPicUrl) {
        this.dietPicUrl = dietPicUrl;
    }

    public Diet() {

    }

    public Diet(String diet_name, String category, String description, String picUrl) {
        this.dietName = diet_name;
        this.dietCategory = category;
        this.dietDescription = description;
        this.dietPicUrl = picUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + dietId +
                ", dietName='" + dietName + '\'' +
                ", category='" + dietCategory + '\'' +
                ", description='" + dietDescription + '\'' +
                ", picUrl='" + dietPicUrl + '\'' +
                '}';
    }


}
