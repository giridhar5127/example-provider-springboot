package com.example.springboot;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
class Product {
  @JsonFormat( shape = JsonFormat.Shape.STRING)
  private @Id Long id;
  private String name;
  private String type;
  private String version;
  private String description;
  private String price;

  Product() {}

  Product(Long id, String name, String type, String version, String description, String price) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.version = version;
    this.description = description;
    this.price = price;
  }
}