package com.saraiva.hrpayroll.services;

import com.saraiva.hrpayroll.entities.Payment;
import com.saraiva.hrpayroll.entities.Worker;
import com.saraiva.hrpayroll.feignClients.WorkerFeignClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WorkerFeignClient feignClient;

    public PaymentService(WorkerFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public Payment getPayment(Long workerId, Integer days) {
        Worker worker = feignClient.findById(workerId).getBody();
        return new Payment(worker.getName(),worker.getDailyIncome(), days);
    }

}
