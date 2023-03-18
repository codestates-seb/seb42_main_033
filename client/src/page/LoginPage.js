import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useState } from 'react';
function LoginPage() {
  const [userId, setUserId] = useState('');
  const [confirmID, setConfirmId] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();
  const LoginSubit = async (event) => {
    event.preventDefault();
    if (userId === '') {
      setConfirmId('Id cannot be empty.');
    } else {
      setConfirmId('');
    }
    if (password === '') {
      setConfirmPassword('Password cannot be empty.');
    } else {
      setConfirmPassword('');
    }
    try {
      const response = await axios.post(
        `http://ec2-54-180-158-124.ap-northeast-2.compute.amazonaws.com:8080/user/login`,
        {
          ID: userId,
          PASSWORD: password,
        }
      );
      console.log(response);
      const accessToken = response.headers.authorization;
      localStorage.setItem('acces_token', accessToken);
      navigate('/');
    } catch (error) {
      console.log(error);
    }
  };
  const Guestlogin = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(
        `http://ec2-54-180-158-124.ap-northeast-2.compute.amazonaws.com:8080/user/login/guest`,
        {
          ID: userId,
          PASSWORD: password,
        }
      );
      console.log(response);
      navigate('/');
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <Loginbody>
      <Logintext>로그인</Logintext>
      {console.log(confirmPassword)}
      {console.log(confirmID)}
      <form onSubmit={LoginSubit}>
        <Logintextbox>
          <Logintextboxinput
            type="text"
            name="userid"
            placeholder="  아이디"
            onChange={(e) => setUserId(e.target.value)}
          ></Logintextboxinput>
          {console.log(userId)}
          {console.log(password)}
        </Logintextbox>
        <Logintextbox>
          <Logintextboxinput
            type="password"
            name="userid"
            placeholder="  비밀번호"
            font-size="10rem"
            onChange={(e) => setPassword(e.target.value)}
          ></Logintextboxinput>
        </Logintextbox>
        <Loginbutton type="submit"> 로그인 </Loginbutton>
      </form>
      <Loginguestbody>
        <form onSubmit={Guestlogin}>
          <Loginguest> 게스트 로그인 </Loginguest>
        </form>
        <ToSigup to="/Signup"> 회원가입 </ToSigup>
      </Loginguestbody>
    </Loginbody>
  );
}
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
// const Loginbuttonlink = styled(Link)`
//   text-decoration: none;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   height: 50px;
//   width: 410px;
//   border-radius: 5px;
//   color: white;
//   background-color: #64b5ff;
//   margin: auto;
//   font-size: 30px;
//   font-weight: 600;
//   border: solid 10px #64b5ff;
// `;
const Loginbutton = styled.button`
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
  display: flex;
  justify-content: space-evenly;
`;
const Loginguest = styled.button`
  display: flex;
  color: black;
  margin-top: 30px;
  margin-left: 18%;
  width: 15vw;
  font-size: 20px;
  background-color: rgba(0, 0, 0, 0);
  border: solid 0px rgba(0, 0, 0, 0);
`;
const ToSigup = styled(Link)`
  display: flex;
  color: black;
  margin-top: 30px;
  margin-left: 20%;
  margin-right: 10%;
  font-size: 20px;
  text-decoration: none;
`;
export default LoginPage;
