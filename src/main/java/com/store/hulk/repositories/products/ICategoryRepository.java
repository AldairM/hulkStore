/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.repositories.products;

import com.store.hulk.models.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
