package com.sns.timeline.post.entity;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.sns.comment.domain.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Table(name="post")
@Entity
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	private String content;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@Column(name="createdAt", updatable=false)
	@UpdateTimestamp
	private ZonedDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private ZonedDateTime updatedAt;
	
}
