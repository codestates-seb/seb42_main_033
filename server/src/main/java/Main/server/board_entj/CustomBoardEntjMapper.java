package Main.server.board_entj;

import Main.server.comment.CommentDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Mapper(componentModel = "spring")
public class CustomBoardEntjMapper implements BoardEntjMapper {
    @Autowired
    private BoardEntjMapper mapper;

    @Override
    public BoardEntj postDtoToBoardEntj(BoardEntjDto.Post postDto) {
        return mapper.postDtoToBoardEntj(postDto);
    }

    @Override
    public BoardEntj patchDtoToBoardEntj(BoardEntjDto.Patch patchDto) {
        return mapper.patchDtoToBoardEntj(patchDto);
    }

    @Override
    public BoardEntjDto.Response boardEntjToResponseDto(BoardEntj post) {
        if(post == null) {
            return null;
        }
        else {
            BoardEntjDto.Response response = new BoardEntjDto.Response();

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
                            comment.getBoardEntj().getId(),
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
    public List<BoardEntjDto.Response> boardEntjToResponseDtos(List<BoardEntj> posts) {
        List<BoardEntjDto.Response> responses = posts.stream()
                .map(this::boardEntjToResponseDto)
                .collect(Collectors.toList());

        return responses;
    }
}
