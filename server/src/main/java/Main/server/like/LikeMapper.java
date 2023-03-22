package Main.server.like;

import Main.server.like.LikeDto;
import Main.server.like.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    Like likeDtoToLike(LikeDto likeDto);
}
