package com.danit.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Developer {

    private int id;
    private String name;
    private List<Category> categories;

}
