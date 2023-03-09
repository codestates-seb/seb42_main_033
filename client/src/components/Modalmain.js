import styled from 'styled-components';
import { FaRegUser, FaRegPaperPlane, FaPowerOff } from 'react-icons/fa';

const Modalbody = styled.div`
  outline: none;
  position: fixed;
  box-sizing: border-box;
  left: 85%;
  top: 10rem;
  height: 21.5rem;
  width: 22.5rem;
  padding: 40px;
  background-color: #f7f7f8;
  border-radius: 10px;
  box-shadow: 0px 7px 10px 0px lightgray;
  transform: translateX(-0%) translateY(-0%);
  z-index: 1;
  @media (max-width: 1500px) {
    position: absolute;
    width: 280px;
    top: 21rem;
    height: 21.5rem;
    width: 22.5rem;
    padding: 40px;
    text-align: center;
    background-color: #f0f0ee;
    border-radius: 10px;
    box-shadow: 0px 7px 10px 0px lightgray;
    transform: translateX(-50%) translateY(-50%);
    z-index: 1;
  }
`;
const Modaldiv = styled.div`
  display: flex;
  flex-direction: column;
  position: fixed;
  width: 300px;
  height: 400px;
`;
const Modaldiv1 = styled.div`
  display: flex;
  justify-content: space-between;
  height: 5rem;
  width: 18rem;
  margin-top: 1rem;
  border-bottom: solid 4px #a1a1a1;
`;
const Modaldiv2 = styled.div`
  display: flex;
  justify-content: space-between;
  height: 5rem;
  width: 17.5rem;
  margin-top: 1rem;
`;
const Modaluser = styled(FaRegUser)`
  width: 60px;
  height: 60px;
  margin-left: 0.5rem;
  color: #707070;
`;
const Modalmail = styled(FaRegPaperPlane)`
  width: 55px;
  height: 55px;
  margin-left: 0.5rem;
  color: #707070;
`;
const Modallogout = styled(FaPowerOff)`
  width: 55px;
  height: 55px;
  margin-left: 0.5rem;
  color: #707070;
`;
const Modalword = styled.a`
  font-size: 40px;
  margin-top: 0.5rem;
  color: #3a3a3a;
`;
const Modaltriangle = styled.div`
  outline: none;
  position: fixed;
  box-sizing: border-box;
  top: 0.6rem;
  left: 96%;
  width: 0px;
  height: 0px;
  margin-top: 9rem;
  text-align: center;
  border-radius: 10px;
  z-index: 3;
  transform: translateX(-50%) translateY(-50%);
  border-bottom: calc(36px * 1.732) solid #f7f7f8;
  border-left: 36px solid transparent;
  border-right: 36px solid transparent;
  @media (max-width: 1500px) {
    display: block;
    position: fixed;
    left: 96%;
    width: 0px;
    height: 0px;
    margin-top: 9rem;
    text-align: center;
    border-radius: 10px;
    transform: translateX(-50%) translateY(-50%);
    border-bottom: calc(36px * 1.732) solid #f0f0ee;
    border-left: 36px solid transparent;
    border-right: 36px solid transparent;
  }
`;

function Modal() {
  return (
    <>
      <Modaltriangle />
      <Modalbody
        horizontal-align="auto"
        vertical-align="top"
        aria-disabled="false"
        prevent-autonav="true"
      >
        <Modaldiv>
          <Modaldiv1>
            <Modaluser>
              <FaRegUser />
            </Modaluser>
            <Modalword>마이페이지</Modalword>
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
            <Modalword>로그아웃</Modalword>
          </Modaldiv2>
        </Modaldiv>
      </Modalbody>
    </>
  );
}
export default Modal;
