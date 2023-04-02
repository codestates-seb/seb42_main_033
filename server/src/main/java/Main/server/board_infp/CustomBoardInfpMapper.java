package Main.server.board_infp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardInfpMapper implements BoardInfpMapper {
    @Autowired
    private BoardInfpMapper mapper;

    @Override
    public BoardInfp postDtoToBoardInfp(BoardInfpDto.Post postDto) {
        return mapper.postDtoToBoardInfp(postDto);
    }

    @Override
    public BoardInfp patchDtoToBoardInfp(BoardInfpDto.Patch patchDto) {
        return patchDtoToBoardInfp(patchDto);
    }

    @Override
    public BoardInfpDto.Response boardInfpToResponseDto(BoardInfp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardInfpDto.Response response = new BoardInfpDto.Response();

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
                            comment.getBoardInfp().getId(),
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
    public List<BoardInfpDto.Response> boardInfpToResponseDtos(List<BoardInfp> posts) {
        List<BoardInfpDto.Response> responses = posts.stream()
                .map(this::boardInfpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
