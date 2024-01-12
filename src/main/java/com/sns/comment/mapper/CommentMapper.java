package com.sns.comment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

	//input:x output:List<Map<String, Object>>
	public List<Map<String, Object>> selectCommentlist();
}
