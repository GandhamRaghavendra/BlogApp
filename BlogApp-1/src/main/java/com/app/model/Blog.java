package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int blogId;
	
	@Enumerated
	private Category category;
	
	private String content;

	private LocalDateTime dateTime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
	@JsonIgnore
	private List<Comments> comments = new ArrayList<>();
}
