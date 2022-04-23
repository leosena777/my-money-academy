package br.com.fiap.mymoney.services;

import br.com.fiap.mymoney.dto.InvestmentDTO;
import br.com.fiap.mymoney.models.InvestmentModel;
import br.com.fiap.mymoney.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestmentService {

    final
    InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Transactional
    public InvestmentModel save(InvestmentModel investmentModel) {
       return investmentRepository.save(investmentModel);
    }

    public List<InvestmentModel> findAll(){
        return investmentRepository.findAll();
    }

    public Optional<InvestmentModel> findById(UUID id) {
        return investmentRepository.findById(id);
    }

    public void delete(InvestmentModel investmentModel) {
        investmentRepository.delete(investmentModel);
    }
}
