package Main.server.like.mapper;

import Main.server.like.dto.LikeDto;
import Main.server.like.entity.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    Like likeDtoToLike(LikeDto likeDto);
}
