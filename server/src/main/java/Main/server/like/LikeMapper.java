package Main.server.like;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    Like likeDtoToLike(LikeDto likeDto);
}
