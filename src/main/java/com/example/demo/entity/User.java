package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity(name="users")
@Data
@TableName("users")
@ApiModel("用户实体")
@NoArgsConstructor
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "用户id",example = "123")
    @Id
    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    @Length(max =12)
    @ApiModelProperty("用户姓名")
    private String name;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty("用户密码")
    private String password;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
