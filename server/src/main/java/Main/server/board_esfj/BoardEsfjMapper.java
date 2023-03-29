package Main.server.board_esfj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEsfjMapper {
    BoardEsfj postDtoToBoardEsfj(BoardEsfjDto.Post postDto);
    BoardEsfj patchDtoToBoardEsfj(BoardEsfjDto.Patch patchDto);
    BoardEsfjDto.Response boardEsfjToResponseDto(BoardEsfj post);
    List<BoardEsfjDto.Response> boardEsfjToResponseDtos(List<BoardEsfj> posts);
}
