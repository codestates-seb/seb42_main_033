package Main.server.board_enfj;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEnfjMapper {
    BoardEnfj postDtoToBoardEnfj(BoardEnfjDto.Post postDto);
    BoardEnfj patchDtoToBoardEnfj(BoardEnfjDto.Patch patchDto);
    BoardEnfjDto.Response boardEnfjToResponseDto(BoardEnfj post);
    List<BoardEnfjDto.Response> boardEnfjToResponseDtos(List<BoardEnfj> posts);
}
