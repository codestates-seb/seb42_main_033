package Main.server.board_isfp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIsfpMapper {
    BoardIsfp postDtoToBoardIsfp(BoardIsfpDto.Post postDto);
    BoardIsfp patchDtoToBoardIsfp(BoardIsfpDto.Patch patchDto);
    BoardIsfpDto.Response boardIsfpToResponseDto(BoardIsfp post);
    List<BoardIsfpDto.Response> boardIsfpToResponseDtos(List<BoardIsfp> posts);
}
