package com.microservice.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="quiz_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Category {
   @Id
    private String id;
    private String title;
    private String description;
    private boolean active;


}
