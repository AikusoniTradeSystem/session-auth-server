package io.github.aikusoni.ats.spring.session.model.mapper;

import io.github.aikusoni.ats.spring.session.model.dto.UserDto;
import io.github.aikusoni.ats.spring.session.model.dto.UserSearchDto;
import io.github.aikusoni.ats.spring.session.model.entity.UserEntity;
import io.github.aikusoni.ats.spring.session.model.form.UserForm;
import io.github.aikusoni.ats.spring.session.model.form.UserRegisterForm;
import io.github.aikusoni.ats.spring.session.model.form.UserSearchForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserModelMapper {
    UserDto toUserDto(UserEntity userForm);

    UserDto toUserDto(UserRegisterForm userRegisterForm);

    UserDto toUserDto(UserForm userForm);

    UserSearchDto toUserSearchDto(UserSearchForm userSearchForm);

    List<UserDto> toUserDtoList(List<UserEntity> userEntityList);

    UserEntity toUserEntity(UserDto userDto);
}
