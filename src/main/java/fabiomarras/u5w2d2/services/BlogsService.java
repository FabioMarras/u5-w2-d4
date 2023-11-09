package fabiomarras.u5w2d2.services;

import fabiomarras.u5w2d2.exceptions.NotFoundException;
import fabiomarras.u5w2d2.entities.Author;
import fabiomarras.u5w2d2.entities.Blog;
import fabiomarras.u5w2d2.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogsService {
    //GET /blogPosts
    //GET /blogPosts/id
    //POST /blogPosts - crea
    //PUT /blogPosts/id - modifica
    //DELETE /blogPosts/id - cancella

    @Autowired
    private BlogsRepository blogsRepository;

    @Autowired
    private AuthorService authorService;

    //GET /blogPosts
    public Page<Blog> getBlogs(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return blogsRepository.findAll(pageable);
    }

    //GET /blogPosts/id
    public Blog findById(int id){
      return blogsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST /blogPosts - crea
    public Blog save(Blog body) {
        Author author = authorService.findById(body.getAuthor().getId());
        body.setAuthor(author);
        return blogsRepository.save(body);
    }

    //PUT /blogPosts/id - modifica
    public Blog findByIdAndUpdate(int id, Blog body){
        Blog blog = this.findById(id);
        blog.setTitolo(body.getTitolo());
        blog.setContenuto(body.getContenuto());
        blog.setTempoDiLettura(body.getTempoDiLettura());
        return blogsRepository.save(blog);
    }

    //DELETE /blogPosts/id - cancella
    public void findByIdAndDelete(int id) {
    Blog blog = this.findById(id);
    blogsRepository.delete(blog);
    }
}
