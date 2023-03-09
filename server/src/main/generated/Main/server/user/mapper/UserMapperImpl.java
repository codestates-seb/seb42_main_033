package Main.server.user.mapper;

import Main.server.user.dto.UserDto.Patch;
import Main.server.user.dto.UserDto.Post;
import Main.server.user.dto.UserDto.Response;
import Main.server.user.entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-08T21:14:24+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public Users userDtoPostToUser(Post post) {
        if ( post == null ) {
            return null;
        }

        Users users = new Users();

        users.setNickName( post.getNickName() );
        users.setEmail( post.getEmail() );
        users.setPassword1( post.getPassword1() );
        users.setPassword2( post.getPassword2() );
        users.setMbti( post.getMbti() );

        return users;
    }

    @Override
    public Users userDtoPatchToUser(Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Users users = new Users();

        users.setUserId( patch.getUserId() );
        users.setNickName( patch.getNickName() );
        users.setEmail( patch.getEmail() );
        users.setPassword1( patch.getPassword1() );
        users.setPassword2( patch.getPassword2() );
        users.setMbti( patch.getMbti() );

        return users;
    }

    @Override
    public Response userToUserDtoResponse(Users users) {
        if ( users == null ) {
            return null;
        }

        Response response = new Response();

        response.setUserId( users.getUserId() );
        response.setNickName( users.getNickName() );
        response.setEmail( users.getEmail() );
        response.setPassword1( users.getPassword1() );
        response.setPassword2( users.getPassword2() );
        response.setMbti( users.getMbti() );

        return response;
    }

    @Override
    public List<Response> usersToUserDtoResponse(List<Users> users) {
        if ( users == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( users.size() );
        for ( Users users1 : users ) {
            list.add( userToUserDtoResponse( users1 ) );
        }

        return list;
    }
}
