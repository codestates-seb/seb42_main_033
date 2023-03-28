package Main.server.board_istj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardIstjMapper implements BoardIstjMapper {
    @Autowired
    private BoardIstjMapper mapper;

    @Override
    public BoardIstj postDtoToBoardIstj(BoardIstjDto.Post postDto) {
        return mapper.postDtoToBoardIstj(postDto);
    }

    @Override
    public BoardIstj patchDtoToBoardIstj(BoardIstjDto.Patch patchDto) {
        return mapper.patchDtoToBoardIstj(patchDto);
    }

    @Override
    public BoardIstjDto.Response boardIstjToResponseDto(BoardIstj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardIstjDto.Response response = new BoardIstjDto.Response();

            response.setId(post.getId());
            response.setUserId(post.getUsers().getUserId());
            response.setNickName(post.getUsers().getNickName());
            response.setTitle(post.getTitle());
            response.setContent(post.getContent());
            response.setTag(post.getTag());

            if(post.getCommentCount() != null) {
                response.setCommentCount(post.getCommentCount());
            }

            if(post.getViewCount() != null) {
                response.setViewCount(post.getViewCount());
            }

            response.setCreatedAt(post.getCreatedAt());
            response.setModifiedAt(post.getModifiedAt());

            List<CommentDto.Response> comments = post.getComment().stream()
                    .map(comment -> new CommentDto.Response(
                            comment.getId(),
                            comment.getUser().getUserId(),
                            comment.getBoardIstj().getId(),
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
    public List<BoardIstjDto.Response> boardIstjToResponseDtos(List<BoardIstj> posts) {
        List<BoardIstjDto.Response> responses = posts.stream()
                .map(this::boardIstjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }

}
