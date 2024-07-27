package io.github.aikusonitradesystem.authserver.session.model.mapper;

import io.github.aikusonitradesystem.authserver.session.model.dto.UserDto;
import io.github.aikusonitradesystem.authserver.session.model.dto.UserSearchDto;
import io.github.aikusonitradesystem.authserver.session.model.entity.UserEntity;
import io.github.aikusonitradesystem.authserver.session.model.form.UserForm;
import io.github.aikusonitradesystem.authserver.session.model.form.UserRegisterForm;
import io.github.aikusonitradesystem.authserver.session.model.form.UserSearchForm;
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
