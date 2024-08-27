package com.example.springboot;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
  private String fiel;

  Product() {}

  Product(Long id, String name, String type, String version, String description, String price, String fiel) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.version = version;
    this.description = description;
    this.price = price;
    this.fiel = fiel;
  }
}