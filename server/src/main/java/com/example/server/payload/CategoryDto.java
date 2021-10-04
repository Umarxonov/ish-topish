package com.example.server.payload;


import com.example.server.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
    private boolean active;
    private CategoryDto parentDto;
    private Category parent;
}
