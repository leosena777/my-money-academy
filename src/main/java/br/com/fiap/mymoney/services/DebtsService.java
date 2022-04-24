package br.com.fiap.mymoney.services;

import br.com.fiap.mymoney.models.DebtsModel;
import br.com.fiap.mymoney.repositories.DebtsRepository;
import br.com.fiap.mymoney.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DebtsService {

    final DebtsRepository debtsRepository;

    public DebtsService(DebtsRepository debtsRepository) {
        this.debtsRepository = debtsRepository;
    }

    @Transactional
    public DebtsModel save(DebtsModel debtsModel) {
       return debtsRepository.save(debtsModel);
    }

    public List<DebtsModel> findAll(){
        return debtsRepository.findAll();
    }

    public Optional<DebtsModel> findById(UUID id) {
        return debtsRepository.findById(id);
    }

    public void delete(DebtsModel debtsModel) {
        debtsRepository.delete(debtsModel);
    }
}
