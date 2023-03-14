import styled from 'styled-components';
import Button from './Button';

const Section = styled.section`
  text-align: center;

  div {
    font-size: 30px;
    font-weight: bold;
    margin: 50px;
  }
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
  margin-top: 100px;
`;

const BtnWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  min-height: 10rem;
`;

function SignoutContent() {
  return (
    <>
      <Section>
        <div>회원 탈퇴</div>
      </Section>
      <InputWrapper>
        <Input type="text" placeholder="이메일" />
        <Input type="password" placeholder="비밀번호" />
        <Input type="password" placeholder="비밀번호 확인" />
      </InputWrapper>
      <BtnWrapper>
        <Button background="#D9D9D9">예</Button>
        <Button>아니오</Button>
      </BtnWrapper>
    </>
  );
}
export default SignoutContent;
