package Main.server.board_infj.mapper;

import Main.server.board_infj.dto.BoardInfjDto;
import Main.server.board_infj.entity.BoardInfj;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardInfjMapper {
    BoardInfj postDtoToBoardInfj(BoardInfjDto.Post postDto);
    BoardInfj patchDtoToBoardInfj(BoardInfjDto.Patch patchDto);
    BoardInfjDto.Response boardInfjToResponseDto(BoardInfj post);
    List<BoardInfjDto.Response> boardInfjToResponseDtos(List<BoardInfj> posts);
}
