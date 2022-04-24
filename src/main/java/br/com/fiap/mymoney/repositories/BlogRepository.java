package br.com.fiap.mymoney.repositories;

import br.com.fiap.mymoney.models.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, UUID> {

}
