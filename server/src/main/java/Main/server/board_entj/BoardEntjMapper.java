package Main.server.board_entj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEntjMapper {
    BoardEntj postDtoToBoardEntj(BoardEntjDto.Post postDto);
    BoardEntj patchDtoToBoardEntj(BoardEntjDto.Patch patchDto);
    BoardEntjDto.Response boardEntjToResponseDto(BoardEntj post);
    List<BoardEntjDto.Response> boardEntjToResponseDtos(List<BoardEntj> posts);
}
