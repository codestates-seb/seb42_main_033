package Main.server.board_estj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEstjMapper {
    BoardEstj postDtoToBoardEstj(BoardEstjDto.Post postDto);
    BoardEstj patchDtoToBoardEstj(BoardEstjDto.Patch patchDto);
    BoardEstjDto.Response boardEstjToResponseDto(BoardEstj post);
    List<BoardEstjDto.Response> boardEstjToResponseDtos(List<BoardEstj> posts);
}
