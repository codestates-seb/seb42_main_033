import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
const Infp = () => {
  const [answers, setAnswers] = useState([]);
  // INFP별 게시글 조회
  const [infpId, setInfpId] = useState([]);
  const [infplist, setinfpList] = useState([]);

  // const [nickName, setNickName] = useState([]);

  // const [mbtiArr, setMbtiArr] = useState('');
  // const [enfpId, setenfpId] = useState('');
  // const [entpId, setentpId] = useState('');
  // const [intpId, setintpId] = useState('');
  // const [enfjId, setenfjId] = useState('');
  // const [infjId, setinfjId] = useState('');
  // const [entjId, setentjId] = useState('');
  // const [intjId, setintjId] = useState('');
  // const [esfpId, setesfpId] = useState('');
  // const [isfpId, setisfpId] = useState('');
  // const [estpId, setestpId] = useState('');
  // const [istpId, setistpId] = useState('');
  // const [esfjId, setesfjId] = useState('');
  // const [isfjId, setisfjId] = useState('');
  // const [estjId, setestjId] = useState('');
  // const [istjId, setistjId] = useState('');
  // let enfps = '';
  let infps = '';
  // let entps = '';
  // let intps = '';
  // let enfjs = '';
  // let infjs = '';
  // let entjs = '';
  // let intjs = '';
  // let esfps = '';
  // let isfps = '';
  // let estps = '';
  // let istps = '';
  // let esfjs = '';
  // let isfjs = '';
  // let estjs = '';
  // let istjs = '';

  useEffect(() => {
    for (const i in mbtiArr) {
      // 엠비티아이별 회원 id
      if (mbtiArr[i].mbti === 'INFP' || mbtiArr[i].mbti === 'infp') {
        infps += mbtiArr[i].userId;
        setInfpId(infps);
      } else if (mbtiArr[i].mbti === 'ENFP' || mbtiArr[i].mbti === 'enfp') {
        enfps += mbtiArr[i].userId;
        setenfpId(infps);
      } else if (mbtiArr[i].mbti === 'ENTP' || mbtiArr[i].mbti === 'entp') {
        entps += mbtiArr[i].userId;
        setentpId(entps);
      } else if (mbtiArr[i].mbti === 'INTP' || mbtiArr[i].mbti === 'intp') {
        intps += mbtiArr[i].userId;
        setintpId(intps);
      } else if (mbtiArr[i].mbti === 'ENFJ' || mbtiArr[i].mbti === 'enfj') {
        enfjs += mbtiArr[i].userId;
        setenfjId(enfjs);
      } else if (mbtiArr[i].mbti === 'INFJ' || mbtiArr[i].mbti === 'infj') {
        infjs += mbtiArr[i].userId;
        setinfjId(infjs);
      } else if (mbtiArr[i].mbti === 'ENTJ' || mbtiArr[i].mbti === 'entj') {
        entjs += mbtiArr[i].userId;
        setentjId(entjs);
      } else if (mbtiArr[i].mbti === 'INTJ' || mbtiArr[i].mbti === 'intj') {
        intjs += mbtiArr[i].userId;
        setintjId(intjs);
      } else if (mbtiArr[i].mbti === 'ESFP' || mbtiArr[i].mbti === 'esfp') {
        esfps += mbtiArr[i].userId;
        setesfpId(esfps);
      } else if (mbtiArr[i].mbti === 'ISFP' || mbtiArr[i].mbti === 'isfp') {
        isfps += mbtiArr[i].userId;
        setisfpId(isfps);
      } else if (mbtiArr[i].mbti === 'ESTP' || mbtiArr[i].mbti === 'estp') {
        estps += mbtiArr[i].userId;
        setestpId(estps);
      } else if (mbtiArr[i].mbti === 'ISTP' || mbtiArr[i].mbti === 'istp') {
        istps += mbtiArr[i].userId;
        setistpId(istps);
      } else if (mbtiArr[i].mbti === 'ESFJ' || mbtiArr[i].mbti === 'esfj') {
        esfjs += mbtiArr[i].userId;
        setesfjId(esfjs);
      } else if (mbtiArr[i].mbti === 'ISFJ' || mbtiArr[i].mbti === 'isfj') {
        isfjs += mbtiArr[i].userId;
        setisfjId(isfjs);
      } else if (mbtiArr[i].mbti === 'ESTJ' || mbtiArr[i].mbti === 'estj') {
        estjs += mbtiArr[i].userId;
        setestjId(estjs);
      } else if (mbtiArr[i].mbti === 'ISTJ' || mbtiArr[i].mbti === 'istj') {
        istjs += mbtiArr[i].userId;
        setistjId(istjs);
      }
    }
  });
  useEffect(() => {
    axios
      // 전체회원 조회
      .get(`${process.env.REACT_APP_API_URL}/users`)
      .then((response) => {
        setMbtiArr(response.data.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  // useEffect(() => {
  //   axios
  //     // 엠비티아이별
  //     .get(`${process.env.REACT_APP_API_URL}/board/infp`)
  //     .then((response) => {
  //       console.log(response);
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // }, []);

  // useEffect(() => {
  //   // 엠비티아이별로 나누기
  //   for (const i of infpId) {
  //     axios
  //       .get(`${process.env.REACT_APP_API_URL}/board/integrated/${i}`)
  //       .then((response) => {
  //         setinfpList(response.data);
  //       })
  //       .catch((err) => {
  //         console.log(err);
  //       });
  //   }
  // }, []);
  // userid랑 게시글 id랑 안맞아서 특정 mbti만 불러오기 안되는거였음
  // 해결법 생각해 봤는데 없음 게시글 id로 받아올라 해도 거기에는 mbti 안적혀있지않음
  useEffect(() => {
    for (const i of infpId) {
      console.log(infpId);
      axios
        .get(`${process.env.REACT_APP_API_URL}/board/integrated/${i}`)
        .then((response) => {
          setInfpList((prevList) => [...prevList, response.data]);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, []);

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/board/integrated`)
      .then((response) => {
        setAnswers(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  return (
    <BoardLayout>
      <BoardHead>INFP 게시판</BoardHead>
      <BoardBox>
        {answers.map((answers, index) => (
          <Link key={index} to={`/board/integrated/${answers.id}`}>
            <div>
              <CardLayout>
                <div className="posttitle"> {answers.title} </div>
                <div className="postcontent"> {answers.content} </div>
                <div className="postnickname"> {answers.nickName} </div>
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
