package br.com.fiap.mymoney.repositories;

import br.com.fiap.mymoney.models.DebtsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DebtsRepository extends JpaRepository<DebtsModel, UUID> {

}
