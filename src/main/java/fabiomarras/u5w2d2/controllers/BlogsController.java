package fabiomarras.u5w2d2.controllers;

import fabiomarras.u5w2d2.entities.Author;
import fabiomarras.u5w2d2.entities.Blog;
import fabiomarras.u5w2d2.exceptions.BadRequestException;
import fabiomarras.u5w2d2.payloads.NewBlogRequestDTO;
import fabiomarras.u5w2d2.services.AuthorService;
import fabiomarras.u5w2d2.services.BlogsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
//    @PostMapping("")
//    public Blog saveNewBlog(@Valid @RequestBody Blog body, BindingResult validation) {
//        if (validation.hasErrors()) {
//            throw new BadRequestException(validation.getAllErrors());
//        }
//        if (body.getTitolo() == null || body.getTitolo().isEmpty()) {
//            throw new BadRequestException("Il titolo è obbligatorio."); }
//            return blogsService.save(body);
//        }


    @PostMapping("")
    public Blog saveNewBlog(@RequestBody @Validated NewBlogRequestDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return blogsService.save(body);
        }
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

    @PostMapping("/upload")
    public String uploadExample(@RequestParam("avatar") MultipartFile body) throws IOException {
       System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return blogsService.uploadPicture(body);
    }
}
