package Main.server.board_enfp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEnfpMapper implements BoardEnfpMapper {
    @Autowired
    private BoardEnfpMapper mapper;

    @Override
    public BoardEnfp postDtoToBoardEnfp(BoardEnfpDto.Post postDto) {
        return mapper.postDtoToBoardEnfp(postDto);
    }

    @Override
    public BoardEnfp patchDtoToBoardEnfp(BoardEnfpDto.Patch patchDto) {
        return mapper.patchDtoToBoardEnfp(patchDto);
    }

    @Override
    public BoardEnfpDto.Response boardEnfpToResponseDto(BoardEnfp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEnfpDto.Response response = new BoardEnfpDto.Response();

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
                            comment.getBoardEnfp().getId(),
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
    public List<BoardEnfpDto.Response> boardEnfpToResponseDtos(List<BoardEnfp> posts) {
        List<BoardEnfpDto.Response> responses = posts.stream()
                .map(this::boardEnfpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
