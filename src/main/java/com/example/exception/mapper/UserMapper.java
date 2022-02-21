package com.example.exception.mapper;

import com.example.exception.entity.User;
import com.example.exception.model.MRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "name", target = "name")
    MRegisterResponse userDtoToMRegisterResponse(User user);

}
