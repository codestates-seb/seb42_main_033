package Main.server.board_estp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEstpMapper {
    BoardEstp postDtoToBoardEstp(BoardEstpDto.Post postDto);
    BoardEstp patchDtoToBoardEstp(BoardEstpDto.Patch patchDto);
    BoardEstpDto.Response boardEstpToResponseDto(BoardEstp post);
    List<BoardEstpDto.Response> boardEstpToResponseDtos(List<BoardEstp> posts);
}
