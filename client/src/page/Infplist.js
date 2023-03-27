import styled from 'styled-components';
import { FaSearch } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
const Infp = () => {
  const [answers, setAnswers] = useState([]);
  const [nickName, setNickName] = useState([]);
  const [infplist, setinfpList] = useState([]);
  const [mbtiArr, setMbtiArr] = useState('');
  const [enfpId, setenfpId] = useState('');
  const [infpId, setinfpId] = useState('');
  const [entpId, setentpId] = useState('');
  const [intpId, setintpId] = useState('');
  const [enfjId, setenfjId] = useState('');
  const [infjId, setinfjId] = useState('');
  const [entjId, setentjId] = useState('');
  const [intjId, setintjId] = useState('');
  const [esfpId, setesfpId] = useState('');
  const [isfpId, setisfpId] = useState('');
  const [estpId, setestpId] = useState('');
  const [istpId, setistpId] = useState('');
  const [esfjId, setesfjId] = useState('');
  const [isfjId, setisfjId] = useState('');
  const [estjId, setestjId] = useState('');
  const [istjId, setistjId] = useState('');
  let enfps = '';
  let infps = '';
  let entps = '';
  let intps = '';
  let enfjs = '';
  let infjs = '';
  let entjs = '';
  let intjs = '';
  let esfps = '';
  let isfps = '';
  let estps = '';
  let istps = '';
  let esfjs = '';
  let isfjs = '';
  let estjs = '';
  let istjs = '';
  let mbtiList = [];
  useEffect(() => {
    for (const i in mbtiArr) {
      // 엠비티아이별 회원 id
      if (mbtiArr[i].mbti === 'ENFP' || mbtiArr[i].mbti === 'enfp') {
        enfps += mbtiArr[i].userId;
        setenfpId(enfps);
      } else if (mbtiArr[i].mbti === 'INFP' || mbtiArr[i].mbti === 'infp') {
        infps += mbtiArr[i].userId;
        setinfpId(infps);
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
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  // useEffect(() => {
  //   const fetchData = async () => {
  //     const promises = [infpId].map((id) => {
  //       return axios.get(
  //         `${process.env.REACT_APP_API_URL}/board/integrated/${id}`
  //       );
  //     });
  //     const responses = await Promise.all(promises);
  //     const data = responses.map((response) => response.data);
  //     setinfpList((prevList) => [...prevList, ...data]);
  //   };
  //   fetchData().catch((err) => {
  //     console.log(err);
  //   });
  // }, []);

  useEffect(() => {
    // 엠비티아이id .map 으로 get
    for (const i of infpId) {
      axios
        .get(`${process.env.REACT_APP_API_URL}/board/integrated/${i}`)
        .then((response) => {
          setinfpList(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, []);

  // useEffect(() => {
  //   Promise.all(
  //     [infpId].map((i) =>
  //       axios
  //         .get(`${process.env.REACT_APP_API_URL}/board/integrated/${i}`)
  //         .then((response) => {
  //           setinfpList(response);
  //           console.log(i);
  //         })
  //     )
  //   );
  // }, []);
  // useEffect(() => {
  //   async function fetchInfpList() {
  //     const results = await Promise.all(
  //       infpId.map((i) =>
  //         axios.get(`${process.env.REACT_APP_API_URL}/board/integrated/${i}`)
  //       )
  //     );
  //     const data = results.map((response) => response.data);
  //     setinfpList(data);
  //   }
  //   fetchInfpList();
  // }, [infpId]);

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const responses = await Promise.all(
  //         infpId.map((id) =>
  //           axios.get(`${process.env.REACT_APP_API_URL}/board/integrated/${id}`)
  //         )
  //       );
  //       const infpList = responses.map((response) => response.data);
  //       setinfpList(infpList);
  //     } catch (error) {
  //       console.error(error);
  //     }
  //   };
  //   fetchData();
  // }, []);
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
  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/users/1`)
      .then((response) => {
        setNickName(response.data.nickName);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <BoardLayout>
      {console.log(infpId)}
      <BoardHead>INFP 게시판</BoardHead>
      <BoardBox>
        {[infplist].map((infplist, index) => (
          <Link key={index} to={`/board/integrated/${infplist.id}`}>
            <div>
              <CardLayout>
                <div className="posttitle"> {infplist.title} </div>
                <div className="postcontent"> {infplist.content} </div>
                <div className="postnickname"> {infplist.nickName} </div>
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
