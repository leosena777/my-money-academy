package br.com.fiap.mymoney.controllers;

import br.com.fiap.mymoney.dto.BlogDTO;
import br.com.fiap.mymoney.models.BlogModel;
import br.com.fiap.mymoney.services.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/blogs")
public class BlogController {
     final BlogService blogService;


    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @PostMapping
    public ResponseEntity<Object> saveBlog(@RequestBody @Valid BlogDTO blogDTO){
        var blogModel = new BlogModel();
        BeanUtils.copyProperties(blogDTO, blogModel);
        blogModel.setDate(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(blogService.save(blogModel));
    }

    @GetMapping
    public ResponseEntity<List<BlogModel>> getAllBlog(){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBlog(@PathVariable(value = "id") UUID id){
        Optional<BlogModel> blogModelOptional = blogService.findById(id);
        if (!blogModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(blogModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBlog(@PathVariable(value = "id") UUID id){
        Optional<BlogModel> investmentModelOptional = blogService.findById(id);
        if (!investmentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        blogService.delete(investmentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Post deletado com sucesso.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBlog(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid BlogDTO blogDTO){
        Optional<BlogModel> blogModelOptional = blogService.findById(id);
        if (!blogModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        var blogModel = new BlogModel();
        BeanUtils.copyProperties(blogDTO, blogModel);
        blogModel.setId(blogModelOptional.get().getId());
        blogModel.setDate(blogModelOptional.get().getDate());
        return ResponseEntity.status(HttpStatus.OK).body(blogService.save(blogModel));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdateBlog(@PathVariable(value = "id") UUID id,
                                             @RequestBody  BlogDTO blogDTO){
        Optional<BlogModel> blogModelOptional = blogService.findById(id);
        if (!blogModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado!");
        }
        var blogModel = new BlogModel();
        BeanUtils.copyProperties(blogDTO, blogModel);
        blogModel.setId(blogModelOptional.get().getId());

        if(blogModel.getTitle() == null){
            blogModel.setTitle(blogModelOptional.get().getTitle());
        }

        if(blogModel.getText() == null){
            blogModel.setText(blogModelOptional.get().getText());
        }

        if(blogModel.getDescription() == null){
            blogModel.setDescription(blogModelOptional.get().getDescription());
        }

        if(blogModel.getDate() == null){
            blogModel.setDate(blogModelOptional.get().getDate());
        }


        return ResponseEntity.status(HttpStatus.OK).body(blogService.save(blogModel));
    }


}
