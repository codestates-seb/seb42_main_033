import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Loginbody = styled.div`
  position: absolute;
  height: 100%;
  top: 80%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
const Logintext = styled.div`
  position: relative;
  font-size: 50px;
  font-weight: 600;
  display: block;
  text-align: center;
`;
const Logintextbox = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  height: 3%;
  width: 100%;
  border-radius: 10px;
  margin-top: 5%;
  margin-bottom: 13%;
`;
const Logintextboxinput = styled.input`
  display: flex;
  height: 75px;
  width: 600px;
  color: black;
  border-radius: 10px;
  border: solid 3px gray;
  font-size: 30px;
`;
const Loginbutton = styled(Link)`
  text-decoration: none;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 75px;
  width: 610px;
  border-radius: 10px;
  color: white;
  background-color: #64b5ff;
  margin: auto;
  font-size: 30px;
  font-weight: 600;
  border: solid 10px #64b5ff;
`;
const Loginguestbody = styled.div`
  display: flex;
  justify-content: space-evenly;
  width: 600px;
  margin-left: 1%;
`;
const Loginguest = styled(Link)`
  display: flex;
  color: black;
  margin-top: 30px;
  font-size: 30px;
  text-decoration: none;
`;
const Logingraybox = styled.div`
  height: 20px;
  width: 4px;
  margin-top: 38px;
  margin-left: 20px;
  margin-right: 75px;
  background-color: lightgray;
`;
function Login() {
  return (
    <Loginbody>
      <Logintext>로그인</Logintext>
      <Logintextbox>
        <Logintextboxinput
          type="text"
          name="userid"
          placeholder=" 아이디"
        ></Logintextboxinput>
      </Logintextbox>
      <Logintextbox>
        <Logintextboxinput
          type="text"
          name="userid"
          placeholder=" 비밀번호"
        ></Logintextboxinput>
      </Logintextbox>
      <Loginbutton to="/"> 로그인 </Loginbutton>
      <Loginguestbody>
        <Loginguest to="/"> 게스트 로그인 </Loginguest>
        <Logingraybox />
        <Loginguest to="/Signup"> 회원가입 </Loginguest>
      </Loginguestbody>
    </Loginbody>
  );
}
export default Login;
