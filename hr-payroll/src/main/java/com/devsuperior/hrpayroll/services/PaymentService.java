package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workedId, int days) {

        // Pegando o ID do HR-WORKER (Outro Projeto)
        Worker worker = workerFeignClient.findById(workedId).getBody();

        // Preenchimento dinamico
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
