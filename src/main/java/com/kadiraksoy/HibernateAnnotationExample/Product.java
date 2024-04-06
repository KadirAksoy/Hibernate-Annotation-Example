package com.kadiraksoy.HibernateAnnotationExample;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer quantity;

    // JoinColumn ile sütun oluşturmuş oluruz
    @OneToOne(cascade = CascadeType.ALL) // CascadeType.All tüm işlemleri yapar güncelle, kaydet, sil vs
    @JoinColumn(name = "product_detail_id", referencedColumnName = "ID")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ID")  // sütun oluşturur category_id adında. ve referans alınan sütunün adı yazılır
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST) // ManyToMany yapısında jointable kullanılması uygundur.
    @JoinTable(
            name = "PRODUCT_TAG_REL",     // tabloya isim verir
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "ID"), // tablo sütunu
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "ID")
    )
    private Set<Tag> tags;

}