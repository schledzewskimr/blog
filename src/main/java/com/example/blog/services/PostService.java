package com.example.blog.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.blog.models.Post;

/**
 * Services – hold the business logic. Often just call some repository method.
 * Example: create new post / show a post for deleting / delete post.
 * Services may have several implementations: DB based or stub based.
 *
 */
public interface PostService {
    List<Post> getAllPosts();
    void savePost(Post post);
    Post getPostById(Long id);
    void deletePostById(Long id);
    Page<Post> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    List<Post> findLatest5();
    Page<Post> findPaginated(int pageNum, int pageSize);
}
