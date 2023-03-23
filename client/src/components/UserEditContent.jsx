import styled from 'styled-components';
import Button from './Button.jsx';
import MyPageSidebar from './MypageSidebar.jsx';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

function UserEditContent() {
  const [name, setName] = useState('');
  const { id } = useParams();
  const handleChangeName = (e) => {
    setName(e.target.value);
  };

  const handleClickSubmit = (e) => {
    e.preventDefault();
    axios
      .patch(`https://0bfa-211-217-72-99.jp.ngrok.io/users/${id}`, {
        name,
      })
      .then((res) => {
        setName(res.data.data.name);
        window.alert('수정 완료');
      })
      .catch(() => {
        window.alert('오류 발생');
      });
  };
  useEffect(() => {
    axios
      .get(`https://0bfa-211-217-72-99.jp.ngrok.io/users/${id}`)
      .then((res) => {
        setName(res.data.data.name);
      })
      .catch(() => {
        window.alert('오류 발생');
      });
  });
  return (
    <>
      <MyPageSidebar />
      <Container>
        <Title>내 정보 수정하기</Title>
        <InputWrapper onSubmit={UserEditContent}>
          <Input
            type="text"
            placeholder="닉네임"
            id="nickName"
            onChange={handleChangeName}
          />
          <Input type="text" placeholder="MBTI" id="mbit" />
          <Input type="password" placeholder="비밀번호" id="password1" />
          <Input type="password" placeholder="비밀번호 확인" id="password2" />
        </InputWrapper>
        <BtnWrapper>
          <Button background="#D9D9D9">취소</Button>
          <Button onClick={handleClickSubmit}>수정</Button>
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

const InputWrapper = styled.form`
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
  transform: translate(-50%, -50%);
`;
export default UserEditContent;
