import styled from 'styled-components';
import { FaRegUser, FaRegPaperPlane, FaPowerOff } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const Modalbody = styled.div`
  position: fixed;
  box-sizing: border-box;
  left: 85%;
  top: 10vh;
  height: 22vh;
  width: 14vw;
  padding: 30px;
  background-color: #f7f7f8;
  border-radius: 10px;
  transform: translateX(-0%) translateY(-0%);
  z-index: 1;
  @media (max-width: 2000px) {
    left: 82%;
    height: 300px;
    width: 17vw;
  }
  @media (max-width: 1740px) {
    left: 78%;
    height: 300px;
    width: 20vw;
  }
  @media (max-width: 1580px) {
    left: 78%;
    height: 300px;
    width: 21vw;
  }
  @media (max-width: 1340px) {
    left: 74%;
    height: 300px;
    width: 23vw;
  }
  @media (max-width: 1250px) {
    left: 74%;
    height: 300px;
    width: 24vw;
  }
  @media (max-width: 1170px) {
    left: 74%;
    height: 300px;
    width: 260px;
  }
  @media (max-height: 1200px) {
    height: 250px;
    margin-top: 0rem;
  }
  @media (max-height: 800px) {
    height: 220px;
    margin-top: 0rem;
  }
  @media (max-height: 600px) {
    height: 200px;
    margin-top: 0rem;
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
  justify-content: space-between;
  height: 5vh;
  width: 100%;
  margin-top: 1rem;
  border-bottom: solid 4px #a1a1a1;
`;
const Modaldiv2 = styled.div`
  display: flex;
  justify-content: space-between;
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
const Modalword = styled(Link)`
  width: 13rem;
  text-decoration: none;
  font-size: 2.5em;
  margin-top: 0.5rem;
  color: #3a3a3a;
  @media (max-width: 1740px) {
    width: 13rem;
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-width: 1580px) {
    width: 100%;
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-width: 1340px) {
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-width: 1250px) {
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-width: 1170px) {
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-width: 1044px) {
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-width: 954px) {
    font-size: 2em;
    margin-top: 0.5rem;
  }
  @media (max-height: 800px) {
    margin-top: 0.1rem;
  }
  @media (max-height: 600px) {
    margin-top: 0rem;
  }
`;
// const Modaltriangle = styled.div`
//   outline: none;
//   position: fixed;
//   box-sizing: border-box;
//   top: 1vh;
//   left: 96%;
//   width: 0px;
//   height: 0px;
//   margin-top: 9rem;
//   text-align: center;
//   border-radius: 10px;
//   z-index: 1;
//   transform: translateX(-50%) translateY(-50%);
//   border-bottom: calc(36px * 1.732) solid #f7f7f8;
//   border-left: 36px solid transparent;
//   border-right: 36px solid transparent;
//   /* @media (max-width: 1500px) {
//     display: block;
//     position: fixed;
//     left: 96%;
//     width: 0px;
//     height: 0px;
//     margin-top: 9rem;
//     text-align: center;
//     border-radius: 10px;
//     transform: translateX(-50%) translateY(-50%);
//     border-bottom: calc(36px * 1.732) solid #f0f0ee;
//     border-left: 36px solid transparent;
//     border-right: 36px solid transparent;
//   } */
// `;

function Modal() {
  return (
    <>
      {/* <Modaltriangle /> */}
      <Modalbody>
        <Modaldiv>
          <Modaldiv1>
            <Modaluser>
              <FaRegUser />
            </Modaluser>
            <Modalword to="/MyComments">마이페이지</Modalword>
          </Modaldiv1>
          <Modaldiv1>
            <Modalmail>
              <FaRegPaperPlane />
            </Modalmail>
            <Modalword>쪽지함 가기</Modalword>
          </Modaldiv1>
          <Modaldiv2>
            <Modallogout>
              <Modallogout />
            </Modallogout>
            <Modalword to="/">로그아웃</Modalword>
          </Modaldiv2>
        </Modaldiv>
      </Modalbody>
    </>
  );
}
export default Modal;
