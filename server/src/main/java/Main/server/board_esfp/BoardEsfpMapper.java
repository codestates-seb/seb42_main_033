package Main.server.board_esfp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEsfpMapper {
    BoardEsfp postDtoToBoardEsfp(BoardEsfpDto.Post postDto);
    BoardEsfp patchDtoToBoardEsfp(BoardEsfpDto.Patch patchDto);
    BoardEsfpDto.Response boardEsfpToResponseDto(BoardEsfp post);
    List<BoardEsfpDto.Response> boardEsfpToResponseDtos(List<BoardEsfp> posts);
}
