package Main.server.board_integrated.mapper;

import Main.server.board_integrated.dto.BoardIntegratedDto.Patch;
import Main.server.board_integrated.dto.BoardIntegratedDto.Post;
import Main.server.board_integrated.dto.BoardIntegratedDto.Response;
import Main.server.board_integrated.entity.BoardIntegrated;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-13T14:21:10+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class BoardIntegratedMapperImpl implements BoardIntegratedMapper {

    @Override
    public BoardIntegrated postDtoToBoardIntegrated(Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        BoardIntegrated boardIntegrated = new BoardIntegrated();

        boardIntegrated.setTitle( postDto.getTitle() );
        boardIntegrated.setContent( postDto.getContent() );
        boardIntegrated.setTag( postDto.getTag() );

        return boardIntegrated;
    }

    @Override
    public BoardIntegrated patchDtoToBoardIntegrated(Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        BoardIntegrated boardIntegrated = new BoardIntegrated();

        boardIntegrated.setTitle( patchDto.getTitle() );
        boardIntegrated.setContent( patchDto.getContent() );
        boardIntegrated.setTag( patchDto.getTag() );

        return boardIntegrated;
    }

    @Override
    public Response boardIntegratedToResponseDto(BoardIntegrated post) {
        if ( post == null ) {
            return null;
        }

        Response response = new Response();

        if ( post.getId() != null ) {
            response.setId( post.getId() );
        }
        response.setTitle( post.getTitle() );
        response.setContent( post.getContent() );
        response.setTag( post.getTag() );
        if ( post.getCommentCount() != null ) {
            response.setCommentCount( post.getCommentCount() );
        }
        if ( post.getViewCount() != null ) {
            response.setViewCount( post.getViewCount() );
        }
        if ( post.getLikeCount() != null ) {
            response.setLikeCount( post.getLikeCount() );
        }
        response.setCreatedAt( post.getCreatedAt() );
        response.setModifiedAt( post.getModifiedAt() );

        return response;
    }

    @Override
    public List<Response> boardIntegratedToResponseDtos(List<BoardIntegrated> posts) {
        if ( posts == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( posts.size() );
        for ( BoardIntegrated boardIntegrated : posts ) {
            list.add( boardIntegratedToResponseDto( boardIntegrated ) );
        }

        return list;
    }
}
