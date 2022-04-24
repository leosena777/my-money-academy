package br.com.fiap.mymoney.controllers;

import br.com.fiap.mymoney.dto.DebtsDTO;
import br.com.fiap.mymoney.models.DebtsModel;
import br.com.fiap.mymoney.services.DebtsService;
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
@RequestMapping("/debts")
public class DebtsController {
     final DebtsService debtsService;


    public DebtsController(DebtsService debtsService) {
        this.debtsService = debtsService;
    }


    @PostMapping
    public ResponseEntity<Object> saveDebts(@RequestBody @Valid DebtsDTO debtsDTO){
        var debtsModel = new DebtsModel();
        BeanUtils.copyProperties(debtsDTO, debtsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(debtsService.save(debtsModel));
    }

    @GetMapping
    public ResponseEntity<List<DebtsModel>> getAllDebts(){
        return ResponseEntity.status(HttpStatus.OK).body(debtsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneDebts(@PathVariable(value = "id") UUID id){
        Optional<DebtsModel> debtsModelOptional = debtsService.findById(id);
        if (!debtsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dívida não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(debtsModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDebts(@PathVariable(value = "id") UUID id){
        Optional<DebtsModel> debtsModelOptional = debtsService.findById(id);
        if (!debtsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dívida não encontrado!");
        }
        debtsService.delete(debtsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Dívida deletado com sucesso.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDebts(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid DebtsDTO debtsDTO){
        Optional<DebtsModel> debtsModelOptional = debtsService.findById(id);
        if (!debtsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dívida não encontrado!");
        }
        var debtsModel = new DebtsModel();
        BeanUtils.copyProperties(debtsDTO, debtsModel);
        debtsModel.setId(debtsModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(debtsService.save(debtsModel));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateDebts(@PathVariable(value = "id") UUID id,
                                              @RequestBody DebtsDTO debtsDTO){
        Optional<DebtsModel> debtsModelOptional = debtsService.findById(id);
        if (!debtsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dívida não encontrado!");
        }

        var debtsModel = new DebtsModel();
        BeanUtils.copyProperties(debtsDTO, debtsModel);
        debtsModel.setId(debtsModelOptional.get().getId());


        if(debtsModel.getAmount() == 0.0f){
            debtsModel.setAmount(debtsModelOptional.get().getAmount());
        }

        if(debtsModel.getDueDate() == null) {
            debtsModel.setDueDate(debtsModelOptional.get().getDueDate());
        }

        if(debtsModel.getName() == null) {
            debtsModel.setName(debtsModelOptional.get().getName());
        }

        return ResponseEntity.status(HttpStatus.OK).body(debtsService.save(debtsModel));
    }


}
