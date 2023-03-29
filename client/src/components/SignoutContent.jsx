import axios from 'axios';
import styled from 'styled-components';
import Button from './Button.jsx';
import MyPageSidebar from './MypageSidebar.jsx';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function SignoutContent() {
  const [users, setUsers] = useState([]);
  const userId = localStorage.getItem('userId');
  const token = localStorage.getItem('jwtToken');
  const navigate = useNavigate();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get(
      `http://ec2-43-201-29-212.ap-northeast-2.compute.amazonaws.com:8080/users/${userId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setUsers(result.data);
  };

  const deleteUser = async () => {
    window.alert('탈퇴가 완료되었습니다.');
    // window.location.replace('/');
    await axios.delete(
      `http://ec2-43-201-29-212.ap-northeast-2.compute.amazonaws.com:8080/users/${userId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    loadUsers();
    localStorage.clear();
  };

  return (
    <>
      <MyPageSidebar />
      <Container>
        <Title>회원 탈퇴</Title>
        <InputWrapper>
          <Input type="text" placeholder="이메일" />
          <Input type="password" placeholder="비밀번호" />
        </InputWrapper>
        <BtnWrapper>
          <Button background="#D9D9D9">취소</Button>
          <Button onClick={deleteUser}>탈퇴</Button>
        </BtnWrapper>
      </Container>
    </>
  );
}
const Title = styled.div`
  position: relative;
  font-size: 30px;
  font-weight: bold;
  display: block;
  text-align: center;
`;

const Input = styled.input`
  display: flex;
  font-size: 20px;
  border: solid 1px gray;
  padding: 15px;
  margin: 15px;
  width: 350px;
`;

const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
`;

const BtnWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: absolute;
  top: 52%;
  left: 50%;
  transform: translate(-27%, -48%);
`;

export default SignoutContent;
