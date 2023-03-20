import styled from 'styled-components';
import { Link } from 'react-router-dom';

function SendDm() {
  return (
    <Dmbody>
      <Dmmodal>
        <Dmdiv>
          <Dmtext>쪽지 보내기</Dmtext>
          <Dmtextbox>
            <Dmtextboxinput
              type="text"
              name="userid"
              placeholder=" 받는 사람"
            />
          </Dmtextbox>
          <Dmsubtitle>쪽지내용</Dmsubtitle>
          <Dmtextbox>
            <Dmtextboxinput2
              type="text"
              name="userid"
              placeholder=" 쪽지내용"
            />
          </Dmtextbox>
          <Dmbuttons>
            {/* 보낸페이지에 계속 남게 */}
            {/* 보내기 창 말고 다른곳 누르면 닫히게 */}
            <Dmlink to="/PostviewPage">
              <Dmcancelbutton> 취소 </Dmcancelbutton>
            </Dmlink>
            <Dmlink to="/PostviewPage">
              <Dmsendbutton> 보내기 </Dmsendbutton>
            </Dmlink>
          </Dmbuttons>
        </Dmdiv>
      </Dmmodal>
    </Dmbody>
  );
}

const Dmbody = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 4;
`;
const Dmmodal = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  width: 40%;
  height: 70%;
  padding: 40px;
  text-align: center;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
  transform: translateX(-50%) translateY(-50%);
  z-index: 4;
`;
const Dmdiv = styled.div`
  display: flex;
  flex-direction: column;
`;
const Dmtext = styled.div`
  font-size: 30px;
  font-weight: 500;
  text-align: left;
  margin-left: 10%;
`;
const Dmtextbox = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  width: 100%;
  font-size: 1rem;
  border-radius: 10px;
  margin-bottom: 0.8%;
`;
const Dmtextboxinput = styled.input`
  display: flex;
  height: 2.5rem;
  width: 80%;
  color: black;
  border-radius: 10px;
  border: solid 1.5px gray;
  font-size: 1rem;
  margin-top: 2%;
`;
const Dmsubtitle = styled.div`
  font-size: 1.5em;
  font-weight: 600;
  text-align: left;
  margin-bottom: 1%;
  margin-left: 10%;
  color: #8c8c8c;
`;
const Dmtextboxinput2 = styled.input`
  display: flex;
  height: 20rem;
  width: 80%;
  color: black;
  border-radius: 10px;
  border: solid 1.5px gray;
  font-size: 1rem;
  margin-bottom: 3%;
`;
const Dmbuttons = styled.div`
  display: flex;
  justify-content: space-around;
`;
const Dmsendbutton = styled.button`
  display: block;
  color: white;
  background-color: #64b5ff;
  border: rgba(0, 0, 0, 0);
  height: 3rem;
  width: 9rem;
  font-size: 1.5rem;
  font-weight: 600;
  border-radius: 8%;
`;
const Dmcancelbutton = styled.button`
  display: block;
  color: white;
  background-color: #a1a1a1;
  border: rgba(0, 0, 0, 0);
  height: 3rem;
  width: 9rem;
  font-size: 1.5rem;
  font-weight: 600;
  border-radius: 8%;
`;
const Dmlink = styled(Link)`
  text-decoration: none;
`;
export default SendDm;
