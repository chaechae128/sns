package com.sns.like.domain;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Like {
	private int postId;
	private int userId;
	private Date createdAt;
}
