package Main.server.board_intp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIntpMapper {
    BoardIntp postDtoToBoardIntp(BoardIntpDto.Post postDto);
    BoardIntp patchDtoToBoardIntp(BoardIntpDto.Patch patchDto);
    BoardIntpDto.Response boardIntpToResponseDto(BoardIntp post);
    List<BoardIntpDto.Response> boardIntpToResponseDtos(List<BoardIntp> posts);
}
