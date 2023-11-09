package fabiomarras.u5w2d2.controllers;

import fabiomarras.u5w2d2.entities.Author;
import fabiomarras.u5w2d2.entities.Blog;
import fabiomarras.u5w2d2.services.AuthorService;
import fabiomarras.u5w2d2.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @Autowired
    private BlogsService blogsService;

    @Autowired
    private AuthorService authorService;

    //GET /blogPosts
    @GetMapping("")
    public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,  @RequestParam(defaultValue = "id") String orderBy){
        return blogsService.getBlogs(page, size, orderBy);
    }

    //GET /blogPosts/id
    @GetMapping("/{id}")
    public Blog findById(@PathVariable int id){
        return blogsService.findById(id);
    }

    //POST /blogPosts - crea
    @PostMapping("")
    public Blog saveNewBlog(@RequestBody Blog body){
        return blogsService.save(body);
    }

    //PUT /blogPosts/id - modifica
    @PutMapping("/{id}")
    public Blog findByIdAndUpdate(@PathVariable int id, @RequestBody Blog body){
        return blogsService.findByIdAndUpdate(id, body);
    }

    //DELETE /blogPosts/id - cancella
    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable int id){
        blogsService.findByIdAndDelete(id);
    }
}
