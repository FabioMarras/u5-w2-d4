package fabiomarras.u5w2d2.controllers;

import fabiomarras.u5w2d2.entities.Author;
import fabiomarras.u5w2d2.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    //GET /authorPosts
    @GetMapping("")
    public List<Author> getAuthor(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,  @RequestParam(defaultValue = "id") String orderBy){
        return authorService.getAuthors(page, size, orderBy);
    }

    //GET /authorPosts/id
    @GetMapping("/{id}")
    public Author findById(@PathVariable int id){
        return authorService.findById(id);
    }

    //POST /blogPosts - crea
    @PostMapping("")
    public Author saveNewauthor(@RequestBody Author body){
        return authorService.save(body);
    }

    //PUT /authorPosts/id - modifica
    @PutMapping("/{id}")
    public Author findByIdAndUpdate(@PathVariable int id, @RequestBody Author body){
        return authorService.findByIdAndUpdate(id, body);
    }

    //DELETE /authorPosts/id - cancella
    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable int id){
        authorService.findByIdAndDelete(id);
    }
}
