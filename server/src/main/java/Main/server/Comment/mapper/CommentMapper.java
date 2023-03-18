package Main.server.Comment.mapper;

import Main.server.Comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(Main.server.Comment.dto.CommentPostDto postDto);
    Comment commentPatchDtoToComment(Main.server.Comment.dto.CommentPatchDto patchDto);
    Main.server.Comment.dto.CommentResponseDto commentToCommentResponseDto(Comment comment);
}
