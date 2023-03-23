import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import BoardCard from './../components/BoardCard.jsx';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
const list = ['1', '2', '3', '4', '5', '6', '7'];
const Infp = () => {
  //   const [answers, setAnswers] = useState([]);
  useEffect(() => {
    axios
      .get(`https://b7d7-211-217-72-99.jp.ngrok.io/board/integrated`)
      .then((response) => {
        console.log(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <BoardLayout>
      <BoardHead>INFP 게시판</BoardHead>
      <BoardBox>
        {list.map((list) => (
          <Link key={list} to={`/board/integrated`}>
            <div>
              <CardLayout>
                <div className="posttitle"> 제목 </div>
                <div className="postcontent"> 나의 질문은 이것이다 휴먼 </div>
                <div className="postnickname"> 알파고 </div>
              </CardLayout>
            </div>
          </Link>
        ))}
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
const CardLayout = styled.div`
  display: flex;
  flex-direction: column;
  width: 908px;
  height: 111px;
  background-color: #ffffff;
  /* justify-content: center;
    align-items: center; */
  margin-left: 10px;
  margin-top: 14px;
  text-decoration: none;
  div.posttitle {
    font-size: 1.8rem;
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
  }
  div.postcontent {
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
  }
  div.postnickname {
    text-align: right;
    padding-right: 10px;
  }
`;

export default Infp;
