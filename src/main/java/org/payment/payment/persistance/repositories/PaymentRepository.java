package org.payment.payment.persistance.repositories;

import org.payment.payment.persistance.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
