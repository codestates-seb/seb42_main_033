package Main.server.board_entp;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEntpMapper implements BoardEntpMapper {
    @Autowired
    private BoardEntpMapper mapper;

    @Override
    public BoardEntp postDtoToBoardEntp(BoardEntpDto.Post postDto) {
        return mapper.postDtoToBoardEntp(postDto);
    }

    @Override
    public BoardEntp patchDtoToBoardEntp(BoardEntpDto.Patch patchDto) {
        return mapper.patchDtoToBoardEntp(patchDto);
    }

    @Override
    public BoardEntpDto.Response boardEntpToResponseDto(BoardEntp post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEntpDto.Response response = new BoardEntpDto.Response();

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
                            comment.getBoardEntp().getId(),
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
    public List<BoardEntpDto.Response> boardEntpToResponseDtos(List<BoardEntp> posts) {
        List<BoardEntpDto.Response> responses = posts.stream()
                .map(this::boardEntpToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
