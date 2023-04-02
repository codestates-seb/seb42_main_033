package Main.server.board_enfj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEnfjMapper implements BoardEnfjMapper {
    @Autowired
    private BoardEnfjMapper mapper;

    @Override
    public BoardEnfj postDtoToBoardEnfj(BoardEnfjDto.Post postDto) {
        return mapper.postDtoToBoardEnfj(postDto);
    }

    @Override
    public BoardEnfj patchDtoToBoardEnfj(BoardEnfjDto.Patch patchDto) {
        return mapper.patchDtoToBoardEnfj(patchDto);
    }

    @Override
    public BoardEnfjDto.Response boardEnfjToResponseDto(BoardEnfj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEnfjDto.Response response = new BoardEnfjDto.Response();

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
                            comment.getBoardEnfj().getId(),
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
    public List<BoardEnfjDto.Response> boardEnfjToResponseDtos(List<BoardEnfj> posts) {
        List<BoardEnfjDto.Response> responses = posts.stream()
                .map(this::boardEnfjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
