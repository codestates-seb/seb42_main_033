package Main.server.user.mapper;

import Main.server.user.dto.UserDto;
import Main.server.user.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users userDtoPostToUser(UserDto.Post post);
    Users userDtoPatchToUser(UserDto.Patch patch);
    UserDto.Response userToUserDtoResponse(Users users);
    List<UserDto.Response> usersToUserDtoResponse(List<Users> users);
}
