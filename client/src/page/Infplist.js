import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
const Infp = () => {
  const URL = `https://5293-211-217-72-99.jp.ngrok.io`;
  const [answers, setAnswers] = useState([]);
  const [nickName, setNickName] = useState([]);
  const [mbtiArr, setMbtiArr] = useState([]);
  const [enfpId, setenfpId] = useState([]);
  const [infpId, setinfpId] = useState([]);
  const [entpId, setentpId] = useState([]);
  const [intpId, setintpId] = useState([]);
  const [enfjId, setenfjId] = useState([]);
  const [infjId, setinfjId] = useState([]);
  const [entjId, setentjId] = useState([]);
  const [intjId, setintjId] = useState([]);
  const [esfpId, setesfpId] = useState([]);
  const [isfpId, setisfpId] = useState([]);
  const [estpId, setestpId] = useState([]);
  const [istpId, setistpId] = useState([]);
  const [esfjId, setesfjId] = useState([]);
  const [isfjId, setisfjId] = useState([]);
  const [estjId, setestjId] = useState([]);
  const [istjId, setistjId] = useState([]);
  useEffect(() => {
    axios
      .get(`${URL}/users`)
      .then((response) => {
        setMbtiArr(response.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  for (const i in mbtiArr) {
    console.log(mbtiArr[i].mbti);
    if (mbtiArr[i].mbti === 'ENFP') {
      setenfpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'INFP') {
      setinfpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ENTP') {
      setentpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'INTP') {
      setintpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ENFJ') {
      setenfjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'INFJ') {
      setinfjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ENTJ') {
      setentjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'INTJ') {
      setintjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ESFP') {
      setesfpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ISFP') {
      setisfpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ESTP') {
      setestpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ISTP') {
      setistpId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ESFJ') {
      setesfjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ISFJ') {
      setisfjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ESTJ') {
      setestjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    } else if (mbtiArr[i].mbti === 'ISTJ') {
      setistjId(
        'userid' +
          ':' +
          mbtiArr[i].userId +
          ',' +
          'mbti' +
          ':' +
          mbtiArr[i].mbti
      );
    }
  }
  useEffect(() => {
    axios
      .get(`${URL}/board/integrated`)
      .then((response) => {
        setAnswers(response.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  useEffect(() => {
    axios
      .get(`${URL}users/1`)
      .then((response) => {
        setNickName(response.data.nickName);
        console.log(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  useEffect(() => {
    axios
      .get(`${URL}users/${infpId.userId}`)
      .then((response) => {
        setNickName(response.data.nickName);
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
        {console.log(infpId)}
        {answers.map((answers) => (
          <Link key={answers.id} to={`/board/integrated`}>
            <div>
              <CardLayout>
                <h1>a</h1>
                <div className="posttitle"> {answers.title} </div>
                <div className="postcontent"> {answers.content} </div>
                <div className="postnickname"> {nickName} </div>
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
