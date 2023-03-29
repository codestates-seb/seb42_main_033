package Main.server.board_enfp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEnfpMapper {
    BoardEnfp postDtoToBoardEnfp(BoardEnfpDto.Post postDto);
    BoardEnfp patchDtoToBoardEnfp(BoardEnfpDto.Patch patchDto);
    BoardEnfpDto.Response boardEnfpToResponseDto(BoardEnfp post);
    List<BoardEnfpDto.Response> boardEnfpToResponseDtos(List<BoardEnfp> posts);
}
