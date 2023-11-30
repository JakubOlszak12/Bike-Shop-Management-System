package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
