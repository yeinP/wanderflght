package com.fly.wanderflight.log.service;

import com.fly.wanderflight.entity.Users;
import com.fly.wanderflight.log.dto.UsersDto;

public interface UserService {

    void registerUser(UsersDto usersDto);

    UsersDto findEmail(String userEmail);


    void updatePassword(Long userNo, String field, String value);

    void validateDuplicateUserEmail(String userEmail);
    void validateDuplicateUserId(String userId);

    default UsersDto usersEntityToDto(Users users){
        UsersDto dto = UsersDto.builder().userNo(users.getUserNo())
                .userId(users.getUserId())
                .userPassword(users.getUserPassword())
                .userBirth(users.getUserBirth())
                .userPhone(users.getUserPhone())
                .userGender(users.getUserGender())
                .userGrade(users.getUserGrade())
                .userRegDate(users.getUserRegDate())
                .userEmail(users.getUserEmail()).build();
        return dto;
    }

    default Users usersDtoToEntity(UsersDto usersDto){
        Users users = Users.builder().userNo(usersDto.getUserNo())
                .userId(usersDto.getUserId())
                .userPassword(usersDto.getUserPassword())
                .userBirth(usersDto.getUserBirth())
                .userPhone(usersDto.getUserPhone())
                .userGender(usersDto.getUserGender())
                .userGrade(usersDto.getUserGrade())
                .userRegDate(usersDto.getUserRegDate())
                .userEmail(usersDto.getUserEmail())
                .build();
        return users;
    }
}
