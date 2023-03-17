package Main.server.board_infj.mapper;

import Main.server.board_infj.dto.BoardInfjDto.Patch;
import Main.server.board_infj.dto.BoardInfjDto.Post;
import Main.server.board_infj.dto.BoardInfjDto.Response;
import Main.server.board_infj.entity.BoardInfj;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T16:54:46+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class BoardInfjMapperImpl implements BoardInfjMapper {

    @Override
    public BoardInfj postDtoToBoardInfj(Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        BoardInfj boardInfj = new BoardInfj();

        boardInfj.setTitle( postDto.getTitle() );
        boardInfj.setContent( postDto.getContent() );
        boardInfj.setTag( postDto.getTag() );

        return boardInfj;
    }

    @Override
    public BoardInfj patchDtoToBoardInfj(Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        BoardInfj boardInfj = new BoardInfj();

        boardInfj.setTitle( patchDto.getTitle() );
        boardInfj.setContent( patchDto.getContent() );
        boardInfj.setTag( patchDto.getTag() );

        return boardInfj;
    }

    @Override
    public Response boardInfjToResponseDto(BoardInfj post) {
        if ( post == null ) {
            return null;
        }

        Response response = new Response();

        if ( post.getId() != null ) {
            response.setId( post.getId() );
        }
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
    public List<Response> boardInfjToResponseDtos(List<BoardInfj> posts) {
        if ( posts == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( posts.size() );
        for ( BoardInfj boardInfj : posts ) {
            list.add( boardInfjToResponseDto( boardInfj ) );
        }

        return list;
    }
}
