package br.com.fiap.mymoney.controllers;

import br.com.fiap.mymoney.dto.InvestmentDTO;
import br.com.fiap.mymoney.models.InvestmentModel;
import br.com.fiap.mymoney.services.InvestmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/investments")
public class InvestmentController  {
     final InvestmentService investmentService;


    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }


    @PostMapping
    public ResponseEntity<Object> saveInvestment(@RequestBody @Valid InvestmentDTO investmentDTO){
        var investmentModel = new InvestmentModel();
        BeanUtils.copyProperties(investmentDTO, investmentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(investmentService.save(investmentModel));
    }

    @GetMapping
    public ResponseEntity<List<InvestmentModel>> getAllInvestment(){
        return ResponseEntity.status(HttpStatus.OK).body(investmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneInvestment(@PathVariable(value = "id") UUID id){
        Optional<InvestmentModel> investmentModelOptional = investmentService.findById(id);
        if (!investmentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investimento não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(investmentModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvestment(@PathVariable(value = "id") UUID id){
        Optional<InvestmentModel> investmentModelOptional = investmentService.findById(id);
        if (!investmentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investimento não encontrado!");
        }
        investmentService.delete(investmentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Investimento deletado com sucesso.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvestment(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid InvestmentDTO investmentDTO){
        Optional<InvestmentModel> investmentModelOptional = investmentService.findById(id);
        if (!investmentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investimento não encontrado!");
        }
        var investmentModel = new InvestmentModel();
        BeanUtils.copyProperties(investmentDTO, investmentModel);
        investmentModel.setId(investmentModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(investmentService.save(investmentModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateInvestment(@PathVariable(value = "id") UUID id,
                                                   @RequestBody InvestmentDTO investmentDTO){
        Optional<InvestmentModel> investmentModelOptional = investmentService.findById(id);
        if (!investmentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investimento não encontrado!");
        }
        var investmentModel = new InvestmentModel();
        BeanUtils.copyProperties(investmentDTO, investmentModel);
        investmentModel.setId(investmentModelOptional.get().getId());

        if(investmentModel.getName() == null){
            investmentModel.setName(investmentModelOptional.get().getName());
        }

        if(investmentModel.getAmount() == 0.0f){
            investmentModel.setAmount(investmentModelOptional.get().getAmount());
        }

        if(investmentModel.getEndDate() == null){
            investmentModel.setEndDate(investmentModelOptional.get().getEndDate());
        }

        if(investmentModel.getInitDate() == null){
            investmentModel.setInitDate(investmentModelOptional.get().getInitDate());
        }

        if(investmentModel.getPercentagePerMonth() == 0){
            investmentModel.setPercentagePerMonth(investmentModelOptional.get().getPercentagePerMonth());
        }

        return ResponseEntity.status(HttpStatus.OK).body(investmentService.save(investmentModel));
    }


}
