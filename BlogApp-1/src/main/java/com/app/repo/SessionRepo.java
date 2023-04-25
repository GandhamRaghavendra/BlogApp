package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.model.CurrentUserSession;

@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession,String>{

	public CurrentUserSession findByKey(String key);
}
