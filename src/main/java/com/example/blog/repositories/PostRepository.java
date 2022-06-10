package com.example.blog.repositories;

import com.example.blog.models.Post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value="SELECT p.* FROM posts p ORDER BY p.date DESC", nativeQuery = true)
    List<Post> findLates5Posts(Pageable pageable);
}
