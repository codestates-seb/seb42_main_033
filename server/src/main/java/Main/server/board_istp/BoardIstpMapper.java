package Main.server.board_istp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardIstpMapper {
    BoardIstp postDtoToBoardIstp(BoardIstpDto.Post postDto);
    BoardIstp patchDtoToBoardIstp(BoardIstpDto.Patch patchDto);
    BoardIstpDto.Response boardIstpToResponseDto(BoardIstp post);
    List<BoardIstpDto.Response> boardIstpToResponseDtos(List<BoardIstp> posts);
}
