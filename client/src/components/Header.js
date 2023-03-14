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
  height: 10%;
  background-color: rgb(255, 255, 255);
  left: 0;
  top: 0;
  z-index: 1;
`;
const Headerimgdiv = styled(Link)``;

const Headerimg = styled.img`
  display: flex;
  width: 180px;
  height: 80px;
  /* margin-top: 1.5rem; */
  margin-left: 1%;
`;
const Headertext = styled(Link)`
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
const Headericon = styled.div`
  width: 15rem;
  height: 7rem;
  margin-right: 5px;
  padding-top: 40px;
`;
const Headericonuser = styled(FaRegUserCircle)`
  width: 2rem;
  height: 5rem;
`;
const Headericonbell = styled(FaRegBell)`
  width: 2rem;
  height: 5rem;
  margin-right: 40px;
`;
const Headertextlogin = styled(Link)`
  display: block;
  color: black;
  text-decoration: none;
  width: 100px;
  font-size: 1.5rem;
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
const Headerimglogin = styled.img`
  display: flex;
  width: 180px;
  height: 80px;
  text-align: center;
  /* margin-top: 1.5rem; */
  margin-right: 20vw;
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
  color: black;
  text-decoration: none;
  font-size: 1.5rem;
  font-weight: 400;
  margin-top: 10px;
  margin-left: 30px;
  :hover {
    font-weight: 700;
  }
`;
const Headertextright = styled.div`
  display: block;
  width: auto;
  font-size: 1.5rem;
  font-weight: 400;
  margin-top: 10px;
  :hover {
    font-weight: 700;
  }
  @media (max-width: 1740px) {
    /* margin-top: 30px; */
    margin-left: 30px;
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
      <Headertext to="/PostlistPage">통합게시판</Headertext>
      <Headertext>MBTI TEST</Headertext>
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
      <Headerimgdiv to="/">
        <Headerimglogin src={Logo} alt="logo" />
      </Headerimgdiv>
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
