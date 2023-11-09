package fabiomarras.u5w2d2.services;

import fabiomarras.u5w2d2.exceptions.NotFoundException;
import fabiomarras.u5w2d2.entities.Author;
import fabiomarras.u5w2d2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    //GET /authors
    //GET /authors/id
    //POST /authors - crea
    //PUT /authors/id - modifica
    //DELETE /authors/id - cancella

    @Autowired
    private AuthorRepository authorRepository;

    //GET /authorPosts
    public List<Author> getAuthors(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return authorRepository.findAll();
    }

    //GET /authorPosts/id
    public Author findById(int id){
      return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST /authorPosts - crea
    public Author save(Author body){
        return authorRepository.save(body);
    }


    //PUT /authorPosts/id - modifica
    public Author findByIdAndUpdate(int id, Author body){
       Author author = this.findById(id);
       author.setNome(body.getNome());
       author.setCognome(body.getCognome());
       author.setEmail(body.getEmail());
       return authorRepository.save(author);
    }

    //DELETE /authorPosts/id - cancella
    public void findByIdAndDelete(int id){
       Author author = this.findById(id);
       authorRepository.delete(author);
    }
}
