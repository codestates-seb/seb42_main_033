package Main.server.comment.mapper;

import Main.server.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(Main.server.comment.dto.CommentPostDto postDto);
    Comment commentPatchDtoToComment(Main.server.comment.dto.CommentPatchDto patchDto);
    Main.server.comment.dto.CommentResponseDto commentToCommentResponseDto(Comment comment);
}
