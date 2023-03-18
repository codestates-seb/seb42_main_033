package Main.server.board_integrated.mapper;

import Main.server.board_integrated.dto.BoardIntegratedDto;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.board_integrated.like.entity.BoardIntegratedLike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardIntegratedMapper {
    BoardIntegrated postDtoToBoardIntegrated(BoardIntegratedDto.Post postDto);
    BoardIntegrated patchDtoToBoardIntegrated(BoardIntegratedDto.Patch patchDto);
    BoardIntegratedDto.Response boardIntegratedToResponseDto(BoardIntegrated post);
    List<BoardIntegratedDto.Response> boardIntegratedToResponseDtos(List<BoardIntegrated> posts);
}
