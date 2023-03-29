import styled from 'styled-components';
import { FaRegUser, FaRegPaperPlane, FaPowerOff } from 'react-icons/fa';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
function Modal({ setModal, modal }) {
  const navigate = useNavigate();
  const logoutHandler = () => {
    localStorage.clear();
    navigate('/');
  };
  const Logout = async () => {
    try {
      const response = await axios.post(
        `http://ec2-54-180-158-15.ap-northeast-2.compute.amazonaws.com:8080/user/logout`
      );
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <Modalbody>
        <Modaldiv>
          <Modaldiv1>
            <Modaluser>
              <FaRegUser />
            </Modaluser>
            <ModalLink to="/EditProfile">
              <Modalword>마이페이지</Modalword>
            </ModalLink>
          </Modaldiv1>
          <Modaldiv1>
            <Modalmail>
              <FaRegPaperPlane />
            </Modalmail>
            <ModalLink>
              <Modalword>쪽지함 가기</Modalword>
            </ModalLink>
          </Modaldiv1>
          <Modaldiv2
            onClick={() => {
              logoutHandler();
              Logout();
            }}
          >
            <Modallogout>
              <Modallogout />
            </Modallogout>
            <ModalLink>
              <Modalword
                onClick={() => {
                  setModal(!modal);
                }}
              >
                로그아웃
              </Modalword>
            </ModalLink>
          </Modaldiv2>
        </Modaldiv>
      </Modalbody>
    </>
  );
}

const Modalbody = styled.div`
  position: fixed;
  box-sizing: border-box;
  left: 77%;
  top: 8%;
  height: 23%;
  width: 14%;
  padding: 8px;
  border: solid 2px lightgray;
  border-radius: 10px;
  background-color: #fcfcfc;
  transform: translateX(-0%) translateY(-0%);
  z-index: 1;
  @media (max-width: 1000px) {
    width: 150px;
    height: 150px;
  }
`;
const Modaldiv = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;
const Modaldiv1 = styled.div`
  display: flex;
  justify-content: space-around;
  height: 5vh;
  width: 100%;
  margin-top: 1rem;
  border-bottom: solid 1.5px #a1a1a1;
`;
const Modaldiv2 = styled.div`
  display: flex;
  justify-content: space-around;
  height: 5vh;
  width: 100%;
  margin-top: 1rem;
`;
const Modaluser = styled(FaRegUser)`
  height: 70%;
  width: 20%;
  margin-left: 0.5rem;
  color: #707070;
`;
const Modalmail = styled(FaRegPaperPlane)`
  height: 70%;
  width: 20%;
  margin-left: 0.5rem;
  color: #707070;
`;
const Modallogout = styled(FaPowerOff)`
  height: 70%;
  width: 20%;
  margin-left: 0.5rem;
  color: #707070;
`;
const ModalLink = styled(Link)``;
const Modalword = styled.button`
  width: auto;
  text-decoration: none;
  font-size: 1.7rem;
  color: #3a3a3a;
  background-color: rgba(0, 0, 0, 0);
  border: solid 1px rgba(0, 0, 0, 0);
  @media (max-width: 1740px) {
    width: 100%;
    font-size: 1.7rem;
  }
  @media (max-width: 1580px) {
    width: 100%;
    font-size: 1.7rem;
  }
  @media (max-width: 1340px) {
    font-size: 1.3rem;
  }
  @media (max-width: 1250px) {
    font-size: 1.3rem;
  }
  @media (max-width: 1170px) {
    font-size: 1.3rem;
  }
  @media (max-width: 1044px) {
    font-size: 1rem;
  }
  @media (max-width: 954px) {
    font-size: 1rem;
  }
  @media (max-height: 800px) {
    font-size: 1rem;
  }
  @media (max-height: 700px) {
    font-size: 1rem;
  }
  @media (max-height: 600px) {
    font-size: 1rem;
  }
`;

export default Modal;
