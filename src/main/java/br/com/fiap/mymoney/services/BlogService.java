package br.com.fiap.mymoney.services;

import br.com.fiap.mymoney.models.BlogModel;
import br.com.fiap.mymoney.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {

    final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Transactional
    public BlogModel save(BlogModel blogModel) {
       return blogRepository.save(blogModel);
    }

    public List<BlogModel> findAll(){
        return blogRepository.findAll();
    }

    public Optional<BlogModel> findById(UUID id) {
        return blogRepository.findById(id);
    }

    public void delete(BlogModel blogModel) {
        blogRepository.delete(blogModel);
    }
}
