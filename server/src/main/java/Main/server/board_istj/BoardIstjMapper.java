package Main.server.board_istj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIstjMapper {
    BoardIstj postDtoToBoardIstj(BoardIstjDto.Post postDto);
    BoardIstj patchDtoToBoardIstj(BoardIstjDto.Patch patchDto);
    BoardIstjDto.Response boardIstjToResponseDto(BoardIstj post);
    List<BoardIstjDto.Response> boardIstjToResponseDtos(List<BoardIstj> posts);
}
