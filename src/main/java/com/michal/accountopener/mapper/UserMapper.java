package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.User;
import com.michal.accountopener.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(userDto.getId(),
                userDto.getUserName(),
                userDto.getSurname(),
                userDto.getAccounts());

    }
}
