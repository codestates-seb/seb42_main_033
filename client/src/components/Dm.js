import styled from 'styled-components';

const Dmbody = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
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
  font-size: 50px;
  font-weight: 600;
  text-align: left;
  margin-left: 10%;
  margin-top: 10%;
`;
const Dmtextbox = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  height: 3%;
  width: 100%;
  border-radius: 10px;
  margin-bottom: 3%;
`;
const Dmtextboxinput = styled.input`
  display: flex;
  height: 3rem;
  width: 80%;
  color: black;
  border-radius: 10px;
  border: solid 3px gray;
  font-size: 30px;
  margin-top: 2%;
`;
const Dmsubtitle = styled.div`
  font-size: 30px;
  font-weight: 600;
  text-align: left;
  margin-bottom: 2%;
  margin-left: 10%;
  color: #8c8c8c;
`;
const Dmtextboxinput2 = styled.input`
  display: flex;
  height: 35rem;
  width: 80%;
  color: black;
  border-radius: 10px;
  border: solid 3px gray;
  font-size: 30px;
`;
const Dmbuttons = styled.div`
  display: flex;
  justify-content: space-around;
`;
const Dmsendbutton = styled.button`
  display: block;
  color: white;
  background-color: #64b5ff;
  border: solid 0px rgba(0, 0, 0, 0);
  height: 5rem;
  width: 13rem;
  font-size: 35px;
  font-weight: 600;
  border-radius: 8%;
`;
const Dmcancelbutton = styled.button`
  display: block;
  color: white;
  background-color: #a1a1a1;
  border: solid 0px rgba(0, 0, 0, 0);
  height: 5rem;
  width: 13rem;
  font-size: 35px;
  font-weight: 600;
  border-radius: 8%;
`;
function Dm() {
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
            <Dmcancelbutton> 취소 </Dmcancelbutton>
            <Dmsendbutton> 보내기 </Dmsendbutton>
          </Dmbuttons>
        </Dmdiv>
      </Dmmodal>
    </Dmbody>
  );
}
export default Dm;
