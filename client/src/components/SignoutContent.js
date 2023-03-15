import styled from 'styled-components';
import Button from './Button';
import MyPageSidebar from './MypageSidebar';

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
  transform: translate(-50%, -50%);
`;
function SignoutContent() {
  return (
    <>
      <MyPageSidebar />
      <Container>
        <Title>회원 탈퇴</Title>
        <InputWrapper>
          <Input type="text" placeholder="이메일" />
          <Input type="password" placeholder="비밀번호" />
          <Input type="password" placeholder="비밀번호 확인" />
        </InputWrapper>
        <BtnWrapper>
          <Button background="#D9D9D9">예</Button>
          <Button>아니오</Button>
        </BtnWrapper>
      </Container>
    </>
  );
}
export default SignoutContent;
