import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import BoardCard from './BoardCard';
import { Link } from 'react-router-dom';

const BoardList = () => {
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
    border-color: #a1a1a1;
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

  return (
    <BoardLayout>
      <BoardHead>게시판</BoardHead>
      <BoardBox>
        <BoardCard>테스트</BoardCard>
      </BoardBox>
      <WriteButtonLink to="/postpage">
        <WriteButton>글쓰기</WriteButton>
      </WriteButtonLink>
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

export default BoardList;
