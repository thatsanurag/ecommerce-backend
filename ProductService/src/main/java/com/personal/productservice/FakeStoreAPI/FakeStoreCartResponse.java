package com.personal.productservice.FakeStoreAPI;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FakeStoreCartResponse {
    private int id;
    private int userId;
    private List<FakeStoreCartProduct> products;
}
