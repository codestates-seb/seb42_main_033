package Main.server.board_isfj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIsfjMapper {
    BoardIsfj postDtoToBoardIsfj(BoardIsfjDto.Post postDto);
    BoardIsfj patchDtoToBoardIsfj(BoardIsfjDto.Patch patchDto);
    BoardIsfjDto.Response boardIsfjToResponseDto(BoardIsfj post);
    List<BoardIsfjDto.Response> boardIsfjToResponseDtos(List<BoardIsfj> posts);
}
