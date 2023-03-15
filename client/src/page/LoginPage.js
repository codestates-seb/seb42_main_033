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
  font-size: 35px;
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
  margin-bottom: 10%;
`;
const Logintextboxinput = styled.input`
  display: flex;
  height: 55px;
  width: 430px;
  color: black;
  border-radius: 30px;
  border: solid 1px gray;
  font-size: 20px;
`;
const Loginbutton = styled(Link)`
  text-decoration: none;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  width: 410px;
  border-radius: 5px;
  color: white;
  background-color: #64b5ff;
  margin: auto;
  font-size: 30px;
  font-weight: 600;
  border: solid 10px #64b5ff;
`;
const Loginguestbody = styled.div`
  width: 450px;
  display: flex;
  width: 600px;
`;
const Loginguest = styled(Link)`
  display: flex;
  color: black;
  margin-top: 30px;
  margin-left: 18%;
  font-size: 20px;
  text-decoration: none;
`;
const Logingraybox = styled.div`
  height: 20px;
  width: 4px;
  margin-top: 30px;
  margin-left: 80px;
  margin-right: 15px;
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
          placeholder="  아이디"
        ></Logintextboxinput>
      </Logintextbox>
      <Logintextbox>
        <Logintextboxinput
          type="text"
          name="userid"
          placeholder="  비밀번호"
          font-size="10rem"
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
