package Main.server.board_integrated;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIntegratedMapper implements BoardIntegratedMapper {
    @Autowired
    private BoardIntegratedMapper mapper;

    @Override
    public BoardIntegrated postDtoToBoardIntegrated(BoardIntegratedDto.Post postDto) {
        return mapper.postDtoToBoardIntegrated(postDto);
    }

    @Override
    public BoardIntegrated patchDtoToBoardIntegrated(BoardIntegratedDto.Patch patchDto) {
        return mapper.patchDtoToBoardIntegrated(patchDto);
    }

    @Override
    public BoardIntegratedDto.Response boardIntegratedToResponseDto(BoardIntegrated post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIntegratedDto.Response response = new BoardIntegratedDto.Response();

            response.setId(post.getId());
            response.setUserId(post.getUsers().getUserId());
            response.setNickName(post.getUsers().getNickName());
            response.setTitle(post.getTitle());
            response.setContent(post.getContent());
            response.setTag(post.getTag());
            response.setLikeCount(post.getLikeCount());
            response.setCategory(post.getCategory());

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
                            comment.getBoardIntegrated().getId(),
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
    public List<BoardIntegratedDto.Response> boardIntegratedToResponseDtos(List<BoardIntegrated> posts) {
        List<BoardIntegratedDto.Response> responses = posts.stream()
                .map(this::boardIntegratedToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
