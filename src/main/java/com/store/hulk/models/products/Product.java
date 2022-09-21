/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.models.products;

import com.store.hulk.models.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private Long stock;

    private BigDecimal price;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Category> categories;
}
