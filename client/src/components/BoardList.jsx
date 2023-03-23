import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import moment from 'moment';
import { Pagination } from '@mui/material';
import BoardCard from './BoardCard.jsx';

const BoardList = () => {
  const [boardData, setBoardData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 5;

  useEffect(() => {
    const fetchData = async () => {
      const response = await axios.get(
        `https://b7d7-211-217-72-99.jp.ngrok.io/board/integrated`
      );
      setBoardData(response.data);
    };
    fetchData();
  }, []);

  const boardList = [];

  for (let i = 0; i < boardData.length; i += 5) {
    boardList.push(boardData.slice(i, i + 5));
  }

  // useEffect(() => {
  //   // 페이지에 해당하는 게시물 가져오기
  //   const getBoardList = async () => {
  //     const { data } = await axios.get(
  //       `https://9b33-211-217-72-99.jp.ngrok.io/board/integrated?page=${currentPage}&size=${pageSize}`
  //     );
  //     return data;
  //   };
  //   // 현재 페이지에 해당하는 게시물로 상태 변경하기
  //   getBoardList().then((result) => setBoardList(result));
  //   // 게시물 전체 갯수 구하기
  //   const getTotalBoard = async () => {
  //     // const id = searchParams.get('id');
  //     const { data } = await axios.get(
  //       `https://9b33-211-217-72-99.jp.ngrok.io/board/integrated`
  //     );
  //     return data.total;
  //   };
  //   // 페이지 카운트 구하기: (전체 board 갯수) / (한 페이지 갯수) 결과 올림
  //   // getTotalBoard().then((result) => setPageCount(Math.ceil(result / 4)));
  // }, [currentPage, pageSize]);
  return (
    <BoardLayout>
      <BoardHead>게시판</BoardHead>
      <BoardBox>
        {boardList.map((boardList, index) => {
          <div key={index}>
            {boardList.map((board) => (
              <BoardCard
                key={board.id}
                username={board.user.username}
                createdAt={moment(board.createdAt)
                  .add(9, 'hour')
                  .format('YYYY-MM-DD')}
                title={board.title}
                content={board.content}
                id={board.id}
                likeCount={board.likeCount}
                viewCount={board.viewCount}
                commentCount={board.commentCount}
              />
            ))}
          </div>;
        })}
      </BoardBox>
      <div>
        <WriteButtonLink to="/postpage">
          <WriteButton>글쓰기</WriteButton>
        </WriteButtonLink>
        {/* 페이지네이션: count에 페이지 카운트, page에 페이지 번호 넣기 */}
        <Pagination
          variant="outlined"
          color="primary"
          page={currentPage}
          count={Math.ceil(boardData.length / pageSize)}
          size="large"
          onChange={(e, value) => {
            // window.location.href = `https://9b33-211-217-72-99.jp.ngrok.io/board/integrated/${value}`;
            setCurrentPage(value);
          }}
          showFirstButton
          showLastButton
        />
      </div>
      <Search>
        <input placeholder="검색어를 입력해주세요." />
        <button>
          <FaSearch size="10px" />
          검색
        </button>
      </Search>
    </BoardLayout>
  );
};

const BoardLayout = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin-bottom: 300px;
`;

const BoardHead = styled.div`
  width: 898px;
  height: 68px;
  background-color: #9bcbf8;
  color: #ffffff;
  font-weight: 700;
  font-size: 30px;
  display: flex;
  align-items: center;
  margin-top: 110px;
  padding-left: 30px;
`;
const BoardBox = styled.form`
  width: 928px;
  height: 640px;
  background-color: #fafafa;
  margin-top: 10px;
`;
const WriteButtonLink = styled(Link)`
  text-decoration: none;
`;
const WriteButton = styled.button`
  width: 99px;
  height: 35px;
  margin-top: 10px;
  margin-left: -830px;
  background-color: #ffffff;
  border-color: rgba(161, 161, 161, 1);
  border-radius: 4px;
  border-width: thin;
  color: #ff8686;
  font-size: 18px;
  font-weight: 500;
  text-decoration: none;
`;
const Search = styled.div`
  display: flex;
  margin-top: 15px;
  input {
    width: 242px;
    height: 33px;
    padding-left: 10px;
  }
  button {
    margin-left: 10px;
    width: 58px;
    height: 39px;
    background-color: #9fd0fe;
    border: none;
  }
`;

export default BoardList;
