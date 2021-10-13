package org.guanqwq.productexhibition.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Type {
    private String name;
    private Product[] products;
    private int icon;
}
