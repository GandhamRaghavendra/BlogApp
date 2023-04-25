package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Comments;


@Repository
public interface CommentsRepo extends JpaRepository<Comments,Integer>{

}
