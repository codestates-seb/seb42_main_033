import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';
import { useState } from 'react';

function SignupPage() {
  const [nickName, setNickName] = useState('');
  const [confirmNickName, setConfirmNickName] = useState('');
  const [userId, setUserId] = useState('');
  const [confirmuserId, setConfirmuserId] = useState('');
  const [password, setPassword] = useState('');
  const [confirmpassword, setConfirmpassword] = useState('');
  const [confirmpassword2, setConfirmpassword2] = useState('');
  const [mbti, setMbti] = useState('');
  const navigate = useNavigate();
  const URL = `https://5293-211-217-72-99.jp.ngrok.io`;
  const SignUpPost = async (event) => {
    event.preventDefault();
    if (nickName === '') {
      setConfirmNickName('Display name cannot be empty.');
    } else {
      setConfirmNickName('a');
    }
    if (userId === '') {
      setConfirmuserId('userId cannot be empty.');
    } else {
      setConfirmuserId('a');
    }
    if (password === '') {
      setConfirmpassword2('Password cannot be empty.');
    } else {
      setConfirmpassword2('a');
    }
    try {
      const response = await axios.post(`${URL}/users`, {
        nickName: nickName,
        email: userId,
        password1: password,
        password2: confirmpassword,
        mbti: mbti,
      });
      console.log(response);
      navigate('/login');
    } catch {
      console.log('에러');
    }
  };
  return (
    <Signupbody>
      <form onSubmit={SignUpPost}>
        <Signuptext> 회원가입 </Signuptext>
        <Signuptextbox>
          {console.log(nickName)}
          {console.log(userId)}
          {console.log(password)}
          {console.log(confirmpassword)}
          {console.log(mbti)}
          {console.log(confirmNickName)}
          {console.log(confirmuserId)}
          {console.log(confirmpassword2)}
          <Signuptextboxinput
            type="text"
            name="usernickname"
            placeholder=" 닉네임"
            onChange={(e) => setNickName(e.target.value)}
          />
        </Signuptextbox>
        <Signuptextbox>
          <Signuptextboxinput
            type="text"
            name="userid"
            placeholder=" 아이디"
            id="userid"
            onChange={(e) => setUserId(e.target.value)}
          />
        </Signuptextbox>
        <Signuptextbox>
          <Signuptextboxinput
            type="password"
            name="userpassword"
            placeholder=" 비밀번호"
            id="signupPassword"
            onChange={(e) => setPassword(e.target.value)}
          />
        </Signuptextbox>
        <Signuptextbox>
          <Signuptextboxinput
            type="password"
            name="userpasswordco"
            placeholder=" 비밀번호확인"
            onChange={(e) => setConfirmpassword(e.target.value)}
          />
        </Signuptextbox>
        <Signuptextbox>
          <Signuptextboxinput
            type="text"
            name="userid"
            placeholder=" MBTI를 입력해주세요."
            onChange={(e) => setMbti(e.target.value)}
          />
        </Signuptextbox>
        <Signupbutton type="submit">회원가입</Signupbutton>
      </form>
    </Signupbody>
  );
}

const Signupbody = styled.div`
  position: absolute;
  height: 100%;
  top: 70%;
  left: 50%;
  transform: translate(-50%, -50%);
`;
const Signuptext = styled.div`
  position: relative;
  font-size: 30px;
  font-weight: 600;
  display: block;
  text-align: center;
`;
const Signuptextbox = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  height: 2%;
  width: 100%;
  border-radius: 5px;
  margin-top: 10%;
`;
const Signuptextboxinput = styled.input`
  display: flex;
  height: 45px;
  width: 400px;
  color: black;
  border-radius: 3px;
  border: solid 1px gray;
  font-size: 1rem;
`;
const Signupbutton = styled.button`
  display: block;
  align-items: center;
  justify-content: center;
  height: 55px;
  width: 160px;
  border-radius: 5px;
  color: white;
  background-color: #64b5ff;
  font-size: 1.5rem;
  font-weight: 600;
  margin: auto;
  margin-top: 10%;
  border: solid 10px #64b5ff;
  text-decoration: none;
  :hover {
    background-color: #79bfff;
    border: solid 10px #79bfff;
  }
`;

export default SignupPage;
