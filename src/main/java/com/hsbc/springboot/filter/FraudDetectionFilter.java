package com.hsbc.springboot.filter;

import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.service.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudDetectionFilter
 * @package: com.hsbc.springboot.filter
 * @author: bruce
 * @date: 2025/1/24 14:56
 */
@Component
public class FraudDetectionFilter extends OncePerRequestFilter {

    @Autowired
    private final FraudDetectionService fraudDetectionService;

    @Autowired
    private Executor syncfraudDetectionExecutor;

    public FraudDetectionFilter(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
//        this.fraudDetectionExecutor = asyncExecutor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Extract transaction from the request (example: reading from JSON payload)
        Transaction transaction = extractTransactionFromRequest(request);

        if (transaction != null) {
            syncfraudDetectionExecutor.execute(() -> {
                fraudDetectionService.analyzeTransaction(transaction);
                fraudDetectionService.handleComplexAnalysis(transaction);
            });
        }

        filterChain.doFilter(request, response);
    }

    private Transaction extractTransactionFromRequest(HttpServletRequest request) {
        // TODO: Implement logic to parse transaction data from the request
        return new Transaction(); // Example transaction
    }
}
