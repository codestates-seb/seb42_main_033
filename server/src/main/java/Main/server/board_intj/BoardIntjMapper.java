package Main.server.board_intj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIntjMapper {
    BoardIntj postDtoToBoardIntj(BoardIntjDto.Post postDto);
    BoardIntj patchDtoToBoardIntj(BoardIntjDto.Patch patchDto);
    BoardIntjDto.Response boardIntjToResponseDto(BoardIntj post);
    List<BoardIntjDto.Response> boardIntjToResponseDtos(List<BoardIntj> posts);
}
