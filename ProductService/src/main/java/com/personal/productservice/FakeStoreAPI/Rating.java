package com.personal.productservice.FakeStoreAPI;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Rating {
    Double rate;
    Integer count;
}
