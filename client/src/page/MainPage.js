// import MainImg from '../images/Main.jpeg';
import MbtiButton from '../components/MbtiButton';
// import MbtiButton2 from './MbtiButton2';
import styled from 'styled-components';
import MainSlide from '../components/MainSlide';

const MainContainer = styled.main`
  width: 100%;
  height: 100vh;
  margin-top: 10vh;
  display: flex;
  flex-direction: column;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50%;
  overflow: hidden;
`;

function MainPage() {
  return (
    <MainContainer>
      <MainSlide />
      <hr />
      <ButtonContainer>
        <MbtiButton />
        {/* <MbtiButton2 /> */}
      </ButtonContainer>
    </MainContainer>
  );
}

export default MainPage;
