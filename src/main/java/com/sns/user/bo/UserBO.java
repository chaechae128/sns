package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	
	//input: loginId  output:UserEntity
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	//input: 파라미터 4개 output: Integer Id
	public Integer addUser(String loginId, String password, String name, String email) {
		UserEntity userEntity = UserEntity.builder()
										.loginId(loginId)
										.password(password)
										.name(name)
										.email(email)
										.build();
		UserEntity user = userRepository.save(userEntity);
		
		return user == null ? null : user.getId();
	}
	
	//input: loginId, password  output:USerEntity
	public UserEntity getUserEntityByLoginIdAndPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
	//input:userId	ouput:UserEntity
	public UserEntity getUerEntityById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	

}
