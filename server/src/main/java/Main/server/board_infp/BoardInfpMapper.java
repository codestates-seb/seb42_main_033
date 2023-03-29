package Main.server.board_infp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardInfpMapper {
    BoardInfp postDtoToBoardInfp(BoardInfpDto.Post postDto);
    BoardInfp patchDtoToBoardInfp(BoardInfpDto.Patch patchDto);
    BoardInfpDto.Response boardInfpToResponseDto(BoardInfp post);
    List<BoardInfpDto.Response> boardInfpToResponseDtos(List<BoardInfp> posts);
}
