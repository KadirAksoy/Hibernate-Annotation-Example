package com.kadiraksoy.HibernateAnnotationExample;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @Column(name = "code")
    private String code;

    // Buradaki mappedBy karşı taraftaki ilişkideki değişkenin adıdır. OneToMany anatasyonuna yazılır
    // Category tablosunda products sütunu yoktur. Bu yapı ilişkiyi belirtir.
    // orphanRemoval = ilişkilendirilen varlıkların kaldırılması gerektiğinde
    // kategori varlığından da kaldırılıp kaldırılmayacağını belirti
    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.REMOVE)
    List<Product> products;
}