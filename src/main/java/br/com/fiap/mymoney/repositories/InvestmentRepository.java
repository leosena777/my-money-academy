package br.com.fiap.mymoney.repositories;

import br.com.fiap.mymoney.models.InvestmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<InvestmentModel, UUID> {

}
