import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import { Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Pagination } from '@mui/material';
import BoardCard from './BoardCard.jsx';

const BoardList = ({ boardList }) => {
  const [searchInput, setSearchInput] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 5;
  const handleInputChange = (e) => {
    setSearchInput(e.target.value);
  };

  const filteredBoardList = boardList.filter((board) =>
    board.title.includes(searchInput)
  );
  const pageCount = Math.ceil(boardList.length / pageSize);
  const currentBoardList = filteredBoardList.slice(
    (currentPage - 1) * pageSize,
    currentPage * pageSize
  );
  return (
    <BoardLayout>
      <BoardHead>게시판</BoardHead>
      <BoardBox>
        {currentBoardList.map((board, idx) => (
          <BoardCard
            key={board.idx}
            id={board.id}
            title={board.title}
            content={board.content}
            nickName={board.nickName}
            viewCount={board.viewCount}
            likeCount={board.likeCount}
            commentCount={board.commentCount}
            createdAt={board.createdAt}
          />
        ))}
      </BoardBox>
      <div>
        <WriteButtonLink to="/postpage">
          <WriteButton>글쓰기</WriteButton>
        </WriteButtonLink>
        <Pagination
          variant="outlined"
          color="primary"
          page={currentPage}
          count={pageCount}
          size="large"
          onChange={(e, value) => {
            setCurrentPage(value);
          }}
          showFirstButton
          showLastButton
        />
      </div>
      <Search>
        <input
          placeholder="검색어를 입력해주세요."
          value={searchInput}
          onChange={handleInputChange}
        />
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
  margin-top: -50px;
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
