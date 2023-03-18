import styled from 'styled-components';

function Footer() {
  return (
    <Footerbody>
      <Footermain>
        ©Copyright ⓒ 2023·MBTI=Science·프론트:
        <Footera href="https://github.com/yeojuyeong">여주영</Footera>,
        <Footera href="https://github.com/hyeonhyeon-K">김재현</Footera>,
        <Footera>김준경</Footera>,
        <Footera href="https://github.com/ByeongminBRO">안병민</Footera>
        ·백엔드:
        <Footera href="https://github.com/5selny">박세련</Footera>,
        <Footera href="https://github.com/klouxia">박철우</Footera>,
        <Footera>정순원</Footera>,
        <Footera href="https://github.com/p1Zzal">이현수</Footera>
      </Footermain>
    </Footerbody>
  );
}

const Footerbody = styled.div`
  display: flex;
  background-color: #9bcbf8;
  width: 100%;
  height: 5vh;
  position: fixed;
  bottom: 0;
  left: 0;
  text-align: left;
`;
const Footermain = styled.div`
  color: white;
  font-size: 1.3rem;
  margin-left: 30px;
  margin-top: 4px;
  font-weight: 500;
  bottom: 0;
`;
const Footera = styled.a`
  color: white;
`;
export default Footer;
