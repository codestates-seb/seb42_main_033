import styled, { keyframes } from 'styled-components';
import { useState, useEffect } from 'react';
import { MdKeyboardArrowLeft, MdKeyboardArrowRight } from 'react-icons/md';

const MainSlide = () => {
  const [currentSlide, setCurrentSlide] = useState(1);
  const handleClickPrev = () => {
    setCurrentSlide((prev) => (prev === 1 ? 3 : prev - 1));
  };

  const handleClickNext = () => {
    setCurrentSlide((prev) => (prev === 3 ? 1 : prev + 1));
  };

  const Container = styled.div`
    /* margin-top: 30px; */
    width: 300vw;
    /* height: 500px; */
    transition: transform 0.5s ease-in-out;
    transform: translateX(-${(currentSlide - 1) * 100}vw);
    overflow: hidden;
    div.inner {
      width: 100vw;
      float: left;
    }
    div.Img {
      width: 100vw;
      object-fit: none;
      /* position: absolute; */
    }
  `;

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentSlide((prev) => (prev === 3 ? 1 : prev + 1));
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  const LeftIcon = styled(MdKeyboardArrowLeft)`
    position: absolute;
    top: 40%;
    left: 15%;
    transform: translateY(-50%);
  `;

  const RightIcon = styled(MdKeyboardArrowRight)`
    position: absolute;
    top: 40%;
    right: 15%;
    transform: translateY(-50%);
  `;
  const slideAnimation = keyframes`
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
`;
  const Img = styled.img`
    animation: ${slideAnimation} 0.5s ease-in-out;
    width: 80%;
    height: auto;
    margin-bottom: -30px;
  `;

  return (
    <div style={{ overflow: 'hidden' }}>
      <Container>
        <div className="inner">
          <Img src="./Group1.png" alt="배너1" />
        </div>
        <div className="inner">
          <Img src="./Group2.png" alt="배너2" />
        </div>
        <div className="inner">
          <Img src="./Group3.png" alt="배너3" />
        </div>
      </Container>
      <LeftIcon size={70} className="icon" onClick={handleClickNext} />
      <RightIcon size={70} className="icon" onClick={handleClickPrev} />
    </div>
  );
};

export default MainSlide;
