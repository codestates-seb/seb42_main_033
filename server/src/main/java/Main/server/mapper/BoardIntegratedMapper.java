package Main.server.board_integrated.mapper;

import Main.server.board_integrated.dto.BoardIntegratedDto;
import Main.server.board_integrated.entity.BoardIntegrated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIntegratedMapper {
    BoardIntegrated postDtoToBoardIntegrated(BoardIntegratedDto.Post postDto);
    BoardIntegrated patchDtoToBoardIntegrated(BoardIntegratedDto.Patch patchDto);
    BoardIntegratedDto.Response boardIntegratedToResponseDto(BoardIntegrated post);
    List<BoardIntegratedDto.Response> boardIntegratedToResponseDtos(List<BoardIntegrated> posts);
}
