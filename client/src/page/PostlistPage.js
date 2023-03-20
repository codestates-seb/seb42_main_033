import BoardList from '../components/BoardList';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { boardActions } from '../slice/boardSlice';

const PostlistPage = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(boardActions.getBoard());
  }, [dispatch]);
  const { board, isSuccess, error } = useSelector((state) => ({
    // isLoading,
    board: state.boardReducers.board,
    // isLoading: state.boardReducers.isLoading,
    isSuccess: state.boardReducers.isSuccess,
    error: state.boardReducers.error,
  }));

  return (
    <div>
      {error ? (
        <h2>에러 발생: {error}</h2>
      ) : isSuccess && board.length > 0 ? (
        <BoardList board={board} />
      ) : isSuccess && board.length <= 0 ? (
        <p>조회할 내용이 없습니다.</p>
      ) : (
        <p> 목록을 불러오는 중입니다. </p>
      )}
    </div>
  );
};

export default PostlistPage;
