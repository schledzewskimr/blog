package com.example.blog.services;

import com.example.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.blog.models.Post;
import com.example.blog.models.User;

@Service
public class PostServiceImpl  implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /* (non-Javadoc)
     * @see spring.blog.services.PostService#findById(java.lang.Long)
     */
    @Override
    public Post getPostById(Long id) {
        Optional<Post> optional = postRepository.findById(id);
        Post post = null;
        if(optional.isPresent()){
            post = optional.get();
        }else {
            throw new RuntimeException("Post not found for id :: "+id);
        }
        return post;    }
    /* (non-Javadoc)
     * @see spring.blog.services.PostService#create(spring.blog.models.Post)
     */
    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }
    @Override
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);
    }
    @Override
    public Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.postRepository.findAll(pageable);
    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepository
                .findAll( PageRequest.of(0, 5,Sort.Direction.DESC,"id") ).stream()
                .sorted( (a,b) -> b.getDate().compareTo(a.getDate()) )
                .collect(Collectors.toList());
    }

    @Override
    public Page<Post> findPaginated(int pageNum, int pageSize) {
        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize).withSort(sort);
        return this.postRepository.findAll(pageable);
    }
}
