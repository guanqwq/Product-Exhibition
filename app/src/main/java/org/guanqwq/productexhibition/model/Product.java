package org.guanqwq.productexhibition.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private double price;
    private String description;
    private int imgID;
}
