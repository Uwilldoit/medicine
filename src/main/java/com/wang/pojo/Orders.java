package com.wang.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Orders {
    private Integer id;
    private String ordersNo;
    private String name;
    private Double price;
    private Integer number;
    private Double overall;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private String description;
}
