package Main.server.board_isfj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIsfjMapper implements BoardIsfjMapper {
    @Autowired
    private BoardIsfjMapper mapper;

    @Override
    public BoardIsfj postDtoToBoardIsfj(BoardIsfjDto.Post postDto) {
        return mapper.postDtoToBoardIsfj(postDto);
    }

    @Override
    public BoardIsfj patchDtoToBoardIsfj(BoardIsfjDto.Patch patchDto) {
        return mapper.patchDtoToBoardIsfj(patchDto);
    }

    @Override
    public BoardIsfjDto.Response boardIsfjToResponseDto(BoardIsfj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIsfjDto.Response response = new BoardIsfjDto.Response();

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
                            comment.getBoardIsfj().getId(),
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
    public List<BoardIsfjDto.Response> boardIsfjToResponseDtos(List<BoardIsfj> posts) {
        List<BoardIsfjDto.Response> responses = posts.stream()
                .map(this::boardIsfjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
