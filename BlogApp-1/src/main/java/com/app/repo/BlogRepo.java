package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.model.Blog;
import com.app.model.Category;


@Repository
public interface BlogRepo extends JpaRepository<Blog,Integer>{

   List<Blog> findByCategory(Category category);
}
