package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    // Pegando o valor de dentro do applicastion.properties
    @Value("${hr-worker.host}")
    private String workerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(long workedId, int days) {

        // Map > Dicionario de parametros
        Map<String, String> uriVariables = new HashMap<>();

        // Adicionando o parametro ID da url
        uriVariables.put("id", "" + workedId + "");

        // Comunicacao entre dois projetos
        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables );

        // Preenchimento dinamico
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
