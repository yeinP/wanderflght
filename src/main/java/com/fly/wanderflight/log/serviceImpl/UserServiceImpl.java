package com.fly.wanderflight.log.serviceImpl;

import com.fly.wanderflight.common.DuplicateException;
import com.fly.wanderflight.common.UserIdNotFoundException;
import com.fly.wanderflight.entity.Users;
import com.fly.wanderflight.log.dto.UsersDto;
import com.fly.wanderflight.log.repository.UsersRepository;
import com.fly.wanderflight.log.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public void registerUser(UsersDto usersDto) {
        String encodedPassword = passwordEncoder.encode(usersDto.getUserPassword());
        usersDto.setUserPassword(encodedPassword);
        Users users = usersDtoToEntity(usersDto);
        usersRepository.save(users);
    }

    @Override
    public UsersDto findEmail(String userEmail) {
        Optional<Users> usersOptional = usersRepository.findUsersByUserEmail(userEmail);

        if (usersOptional.isPresent()) {
            Users users = usersOptional.get();
            UsersDto usersDto = new UsersDto();
            usersDto.setUserId(users.getUserEmail()); // 필드와 메서드 이름이 정확한지 확인합니다.
            return usersDto;
        } else {
            throw new UserIdNotFoundException("User not found with ID: " + userEmail);
        }
    }


    @Override
    public void updatePassword(Long userNo, String field, String value) {
        Optional<Users> usersOptional = usersRepository.findById(userNo);
        if (usersOptional.isPresent()){
            Users users = usersOptional.get();
            if ("userPassword".equals(field)) {
                users.setUserPassword(value);
                usersRepository.save(users);

            }else{ throw new IllegalArgumentException();}
        }else{throw new UserIdNotFoundException("User not found with ID: " + userNo);}
    }

    @Override
    @Transactional
    public void validateDuplicateUserEmail(String userEmail) {
        if(usersRepository.existsByUserEmail(userEmail)){
            throw  new DuplicateException();
        }
    }

    @Override
    public void validateDuplicateUserId(String userId) {
        if(usersRepository.existsByUserEmail(userId)){
            throw  new DuplicateException();
        }
    }



}
