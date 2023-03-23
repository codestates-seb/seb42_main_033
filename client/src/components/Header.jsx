import styled from 'styled-components';
import Logo from './../images/Logo.png';
import { Link } from 'react-router-dom';
import { FaRegUserCircle, FaRegBell } from 'react-icons/fa';

function HeaderIcon({ setModal, modal }) {
  return (
    <Headerbody>
      <Headerimgdiv to="/">
        <Headerimg src={Logo} alt="logo" />
      </Headerimgdiv>
      <Headertext2>
        <Headertext to="/PostlistPage">통합게시판</Headertext>
      </Headertext2>
      <Headertext2>
        <Headertext>MBTI TEST</Headertext>
      </Headertext2>
      <Headericon>
        <Headericonbell>
          <FaRegBell />
        </Headericonbell>
        <Headericonuser
          onClick={() => {
            setModal(!modal);
          }}
        >
          <FaRegUserCircle />
        </Headericonuser>
      </Headericon>
    </Headerbody>
  );
}
function HeaderLogin() {
  return (
    <Headerbody>
      <Headerimgdiv to="/">
        <Headerimg src={Logo} alt="logo" />
      </Headerimgdiv>
      <Headertext2>
        <Headertext to="/PostlistPage">통합게시판</Headertext>
        <Headertext>MBTI TEST</Headertext>
      </Headertext2>
      <Headertextlogin to="/Login"> 로그인</Headertextlogin>
    </Headerbody>
  );
}
const Headerbody = styled.div`
  display: flex;
  position: fixed;
  align-items: center;
  text-align: right;
  justify-content: flex-end;
  width: 100%;
  height: 8vh;
  background-color: #ffffff;
  border-bottom: solid 1.5px lightgray;
  left: 0;
  top: 0;
  z-index: 1;
`;
const Headerimgdiv = styled(Link)`
  text-decoration: none;
  display: flex;
  width: 100%;
  height: 100%;
  margin-left: 13vw;
`;

const Headerimg = styled.img`
  display: flex;
  width: auto;
  height: auto;
`;
const Headertext2 = styled.div`
  display: flex;
  width: auto;
  height: 7vh;
`;
const Headertext = styled(Link)`
  display: flex;
  width: 10rem;
  text-decoration: none;
  color: black;
  font-size: 1.3em;
  letter-spacing: 0.01rem;
  margin-top: 2vh;
  :hover {
    color: black;
    font-weight: 700;
  }
`;
const Headericon = styled.div`
  display: flex;
  width: 15rem;
  height: 10rem;
  margin-left: 2vw;
  margin-right: 13vw;
`;
const Headericonuser = styled(FaRegUserCircle)`
  display: flex;
  width: 2rem;
  height: 10rem;
  color: black;
  :hover {
    color: black;
  }
`;
const Headericonbell = styled(FaRegBell)`
  display: flex;
  width: 2rem;
  height: 10rem;
  margin-right: 2vw;
  color: black;
  :hover {
    color: black;
  }
`;
const Headertextlogin = styled(Link)`
  display: flex;
  width: 10rem;
  text-decoration: none;
  color: black;
  font-size: 1.3rem;
  letter-spacing: 0.1rem;
  margin-top: 0.7vh;
  margin-right: 10%;
  :hover {
    font-weight: 700;
    color: black;
  }
`;
export { HeaderIcon, HeaderLogin };
