import styled from 'styled-components';

const Footerbody = styled.div`
  display: flex;
  background-color: #9bcbf8;
  width: 100%;
  height: 4vh;
  position: fixed;
  bottom: 0;
  left: 0;
  text-align: left;
`;
const Footermain = styled.div`
  color: white;
  font-size: 1rem;
  margin-left: 30px;
  margin-top: 4px;
  font-weight: 500;
  bottom: 0;
`;
function Footer() {
  return (
    <Footerbody>
      <Footermain>
        ©Copyright ⓒ
        2023·MBTI=Science·프론트:여주영,김재현,김준경,안병민·백엔드:박세련,박철우,정순원,이현수
      </Footermain>
    </Footerbody>
  );
}
export default Footer;
