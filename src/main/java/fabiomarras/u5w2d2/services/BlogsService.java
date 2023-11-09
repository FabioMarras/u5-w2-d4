package fabiomarras.u5w2d2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fabiomarras.u5w2d2.exceptions.NotFoundException;
import fabiomarras.u5w2d2.entities.Author;
import fabiomarras.u5w2d2.entities.Blog;
import fabiomarras.u5w2d2.payloads.NewBlogRequestDTO;
import fabiomarras.u5w2d2.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Autowired
    private Cloudinary cloudinary;

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
    public Blog save(NewBlogRequestDTO body) throws IOException{
        Blog blog = new Blog();
        blog.setCategoria(body.categoria());
        blog.setTitolo(body.titolo());
        blog.setCover(body.cover());
        blog.setContenuto(body.contenuto());
        blog.setTempoDiLettura(body.tempoDiLettura());
        blog.setAvatar(body.avatar());
        Blog saveBlog = blogsRepository.save(blog);
        return saveBlog;
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

    public String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }
}
