package Main.server.board_entp;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardEntpMapper {
    BoardEntp postDtoToBoardEntp(BoardEntpDto.Post postDto);
    BoardEntp patchDtoToBoardEntp(BoardEntpDto.Patch patchDto);
    BoardEntpDto.Response boardEntpToResponseDto(BoardEntp post);
    List<BoardEntpDto.Response> boardEntpToResponseDtos(List<BoardEntp> posts);
}
