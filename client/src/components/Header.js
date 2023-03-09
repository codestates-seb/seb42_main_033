import styled from 'styled-components';
import Logo from './../Image/Logo.png';
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
const Headerimg = styled.img`
  display: flex;
  width: 300px;
  height: 160px;
  margin-top: 1.5rem;
  margin-left: 1%;
`;
const Headertext = styled.div`
  display: block;
  width: auto;
  font-size: 2.5rem;
  font-weight: 400;
  margin-top: 10px;
  :hover {
    font-weight: 700;
  }
`;
const Headericon = styled.div`
  width: 20rem;
  height: 7rem;
  margin-right: 10px;
`;
const Headericonuser = styled(FaRegUserCircle)`
  width: 4rem;
  height: 7rem;
`;
const Headericonbell = styled(FaRegBell)`
  width: 4rem;
  height: 7rem;
  margin-right: 70px;
`;
const Headertextlogin = styled.div`
  display: block;
  width: 150px;
  font-size: 2.5rem;
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
    font-size: 2.5rem;
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
  width: 300px;
  height: 160px;
  text-align: center;
  margin-top: 1.5rem;
  margin-right: 18%;
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
const Headertextleft = styled.div`
  display: block;
  width: auto;
  font-size: 2.5rem;
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
  font-size: 2.5rem;
  font-weight: 400;
  margin-top: 10px;
  :hover {
    font-weight: 700;
  }
  @media (max-width: 1740px) {
    margin-top: 30px;
    margin-left: 30px;
  }
`;
const Headerbox = styled.div`
  height: 35px;
  width: 1%;
  margin-top: 15px;
  background-color: lightgray;
  @media (max-width: 1740px) {
    background-color: rgba(0, 0, 0, 0);
  }
`;
function HeaderLogin({ setLogin, setModal }) {
  return (
    <Headerbody>
      <Headerimg src={Logo} alt="logo" />
      <Headertext>통합게시판</Headertext>
      <Headertext>MBTI TEST</Headertext>
      <Headericon>
        <Headericonbell
          onClick={() => {
            setModal(true);
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
        <Headertextleft>통합게시판</Headertextleft>
        <Headerbox />
        <Headertextright>MBTI TEST</Headertextright>
      </Headertextdiv>
      <Headerimglogin src={Logo} alt="logo" />
      <Headertextlogin
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
