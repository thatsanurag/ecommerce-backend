package com.personal.productservice.models;


import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    private long id;
    private String createdBy;
    private Date createdAt;
    private boolean isDeleted;

}
