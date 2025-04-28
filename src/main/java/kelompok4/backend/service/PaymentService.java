package kelompok4.backend.service;

import kelompok4.backend.entity.Payment;
import kelompok4.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment payment) {
        Payment existingPayment = paymentRepository.findById(id).orElse(null);
        if (existingPayment != null) {
            existingPayment.setPaymentMethod(payment.getPaymentMethod());
            existingPayment.setAmountPaid(payment.getAmountPaid());
            existingPayment.setPaymentDate(payment.getPaymentDate());
            existingPayment.setStatus(payment.getStatus());
            existingPayment.setOrder(payment.getOrder());
            existingPayment.setUser(payment.getUser());
            return paymentRepository.save(existingPayment);
        }
        return null;
    }


    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
