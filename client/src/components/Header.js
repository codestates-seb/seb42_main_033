import styled from 'styled-components';
import Logo from './../images/Logo.png';
import { Link } from 'react-router-dom';
import { FaRegUserCircle, FaRegBell } from 'react-icons/fa';
const Headerbody = styled.div`
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 7vh;
  background-color: rgb(255, 255, 255);
  left: 0;
  top: 0;
  z-index: 1;
`;
const Headerimgdiv = styled(Link)`
  text-decoration: none;
  display: flex;
  width: 10%;
  height: 100%;
`;

const Headerimg = styled.img`
  display: flex;
  width: auto;
  height: auto;
`;
const Headertext2 = styled.div`
  display: flex;
  justify-content: space-around;
  width: 50%;
  height: 7vh;
`;
const Headertext = styled(Link)`
  display: flex;
  width: auto;
  text-decoration: none;
  color: black;
  font-size: 2.4em;
  font-weight: 400;
  margin-top: 2vh;
  :hover {
    font-weight: 700;
  }
`;
const Headericon = styled.div`
  width: 15rem;
  height: 7rem;
  margin-right: 5px;
`;
const Headericonuser = styled(FaRegUserCircle)`
  width: 20%;
  height: 100%;
`;
const Headericonbell = styled(FaRegBell)`
  width: 20%;
  height: 100%;
  margin-right: 40px;
`;
const Headertextlogin = styled(Link)`
  display: block;
  color: black;
  text-decoration: none;
  width: 100px;
  font-size: 2rem;
  font-weight: 400;
  margin-top: 10px;
  margin-right: 2.5rem;
  :hover {
    font-weight: 700;
  }
  @media (max-width: 1740px) {
    align-items: center;
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    flex-shrink: 0;
    flex-wrap: wrap;
    margin-bottom: 10px;
    width: auto;
    font-size: 1.5rem;
    font-weight: 400;
    margin-top: 10px;
    margin-right: 2.5rem;
    line-height: 17px;
    text-align: left;
    vertical-align: baseline;
  }
`;
const Headerimgdivlink = styled(Link)`
  text-decoration: none;
  display: flex;
  margin-right: 18vw;
  width: 10%;
  height: 100%;
`;
const Headerimglogin = styled.img`
  display: flex;
  width: auto;
  height: auto;
  margin-right: 100vw;
`;
const Headertextdiv = styled.div`
  display: flex;
  width: 23%;
  margin-left: 30px;
  justify-content: space-between;
  @media (max-width: 1740px) {
    align-items: center;
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    flex-shrink: 0;
    flex-wrap: wrap;
    margin-bottom: 10px;
    width: 23%;
    font-size: 2.3rem;
    font-weight: 400;
    margin-top: 10px;
    margin-right: 0%;
    line-height: 17px;
    text-align: left;
    vertical-align: baseline;
  }
`;
const Headertextleft = styled(Link)`
  display: block;
  width: auto;
  text-decoration: none;
  color: black;
  font-size: 2rem;
  font-weight: 400;
  margin-top: 10px;
  :hover {
    font-weight: 700;
  }
`;
const Headertextright = styled.div`
  display: block;
  width: auto;
  font-size: 2rem;
  font-weight: 400;
  margin-top: 10px;
  :hover {
    font-weight: 700;
  }
`;
const Headerbox = styled.div`
  height: 35px;
  width: 1%;
  margin-top: 10px;
  background-color: lightgray;
  @media (max-width: 1740px) {
    background-color: rgba(0, 0, 0, 0);
  }
`;
function HeaderLogin({ setModal, setLogin, modal }) {
  return (
    <Headerbody>
      <Headerimgdiv to="/">
        <Headerimg src={Logo} alt="logo" />
      </Headerimgdiv>
      <Headertext2>
        <Headertext to="/PostlistPage">통합게시판</Headertext>
        <Headertext>MBTI TEST</Headertext>
      </Headertext2>
      <Headericon>
        <Headericonbell
          onClick={() => {
            setModal(!modal);
          }}
        >
          <FaRegBell />
        </Headericonbell>
        <Headericonuser
          onClick={() => {
            setLogin(true);
          }}
        >
          <FaRegUserCircle />
        </Headericonuser>
      </Headericon>
    </Headerbody>
  );
}
function HeaderLogout({ setLogin }) {
  return (
    <Headerbody>
      <Headertextdiv>
        <Headertextleft to="/PostlistPage">통합게시판</Headertextleft>
        <Headerbox />
        <Headertextright>MBTI TEST</Headertextright>
      </Headertextdiv>
      <Headerimgdivlink to="/">
        <Headerimglogin src={Logo} alt="logo" />
      </Headerimgdivlink>
      <Headertextlogin
        to="/Login"
        onClick={() => {
          setLogin(false);
        }}
      >
        로그인
      </Headertextlogin>
    </Headerbody>
  );
}
export { HeaderLogin, HeaderLogout };
