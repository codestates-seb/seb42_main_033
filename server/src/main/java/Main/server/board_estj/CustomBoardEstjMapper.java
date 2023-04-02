package Main.server.board_estj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEstjMapper implements BoardEstjMapper {
    @Autowired
    private BoardEstjMapper mapper;

    @Override
    public BoardEstj postDtoToBoardEstj(BoardEstjDto.Post postDto) {
        return mapper.postDtoToBoardEstj(postDto);
    }

    @Override
    public BoardEstj patchDtoToBoardEstj(BoardEstjDto.Patch patchDto) {
        return mapper.patchDtoToBoardEstj(patchDto);
    }

    @Override
    public BoardEstjDto.Response boardEstjToResponseDto(BoardEstj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEstjDto.Response response = new BoardEstjDto.Response();

            response.setId(post.getId());
            response.setUserId(post.getUsers().getUserId());
            response.setNickName(post.getUsers().getNickName());
            response.setTitle(post.getTitle());
            response.setContent(post.getContent());
            response.setTag(post.getTag());
            response.setLikeCount(post.getLikeCount());
            response.setCategory(post.getUsers().getMbti());

            if(post.getCommentCount() != null) {
                response.setCommentCount(post.getCommentCount());
            }

            if(post.getViewCount() != null) {
                response.setViewCount(post.getViewCount());
            }

            if(post.getLikeCount() != null) {
                response.setLikeCount(post.getLikeCount());
            }

            response.setCreatedAt(post.getCreatedAt());
            response.setModifiedAt(post.getModifiedAt());

            List<CommentDto.Response> comments = post.getComment().stream()
                    .map(comment -> new CommentDto.Response(
                            comment.getId(),
                            comment.getUser().getUserId(),
                            comment.getBoardEstj().getId(),
                            comment.getContent(),
                            comment.getUser().getNickName(),
                            comment.getCategory()
                    ))
                    .collect(Collectors.toList());

            response.setComments(comments);

            return response;
        }
    }

    @Override
    public List<BoardEstjDto.Response> boardEstjToResponseDtos(List<BoardEstj> posts) {
        List<BoardEstjDto.Response> responses = posts.stream()
                .map(this::boardEstjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
