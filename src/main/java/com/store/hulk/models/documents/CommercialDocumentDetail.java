/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.models.documents;

import com.store.hulk.models.audit.AuditModel;
import com.store.hulk.models.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommercialDocumentDetail extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    private double quantity;

    private BigDecimal unitValue;
}
