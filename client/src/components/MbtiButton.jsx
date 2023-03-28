import { useState } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const buttons = [
  { id: 1, text: 'INFP' /*,  path: '/INFP'*/ },
  { id: 2, text: 'ENFP' },
  { id: 3, text: 'ISFP' },
  { id: 4, text: 'ESFP' },
  { id: 5, text: 'INTP' },
  { id: 6, text: 'ENTP' },
  { id: 7, text: 'ISTP' },
  { id: 8, text: 'ESTP' },
  { id: 9, text: 'INFJ' },
  { id: 10, text: 'ENFJ' },
  { id: 11, text: 'ISFJ' },
  { id: 12, text: 'ESFJ' },
  { id: 13, text: 'INTJ' },
  { id: 14, text: 'ENTJ' },
  { id: 15, text: 'ISTJ' },
  { id: 16, text: 'ESTJ' },
];

const MbtiButton = () => {
  const [showButtons, setShowButtons] = useState(true);
  const [text, setText] = useState('>>MBTI별 게시판');

  const buttonRows = [];
  for (let i = 0; i < buttons.length; i += 4) {
    buttonRows.push(buttons.slice(i, i + 4));
  }

  const handleClick = () => {
    setShowButtons(!showButtons);
    setText(
      text === '>>MBTI별 게시판 펼치기'
        ? '>>MBTI별 게시판 접기'
        : '>>MBTI별 게시판 펼치기'
    );
  };

  const userMbti = localStorage.getItem('mbti');
  const handleButtonClick = (buttonMbti) => {
    if (buttonMbti !== userMbti) {
      alert(`${userMbti} 게시판을 이용해주세요`);
    }
  };
  return (
    <>
      <StyledText onClick={handleClick}>{text}</StyledText>
      {showButtons && (
        <>
          <StyledContainer>
            {buttonRows.map((row, index) => (
              <StyledRow key={index}>
                {row.map((button) => (
                  <StyledButtonLink
                    to={
                      button.text.toLocaleLowerCase() ===
                      userMbti.toLocaleLowerCase()
                        ? `${button.text}list`
                        : '#'
                    }
                    key={button.id}
                    button={button}
                  >
                    <StyledButton
                      onClick={() => handleButtonClick(button.text)}
                      key={button.id}
                      button={button}
                    >
                      {button.text}
                    </StyledButton>
                  </StyledButtonLink>
                ))}
              </StyledRow>
            ))}
          </StyledContainer>
        </>
      )}
    </>
  );
};
const StyledText = styled.div`
  font-size: 2vh;
  margin-bottom: 2vh;
  position: absolute;
  bottom: 28%;
  left: 11%;
  cursor: pointer;
`;

const StyledContainer = styled.div`
  width: 3vh;
  height: 2vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: absolute;
  bottom: 28%;
  left: 50%;
`;
const StyledButtonLink = styled(Link)`
  text-decoration: none;
`;
const StyledButton = styled.button`
  text-decoration: none;
  background-color: #edf8de;
  /* background-color: #eff1de; */
  border: none;
  color: #676767;

  margin: 2vh 5vw;

  text-align: center;
  font-size: 2vw;
  width: 12vw;
  height: 5vw;

  border-radius: 2vh;

  &:hover {
    cursor: pointer;
    color: black;
    border: solid;
    border-color: black;
    background-color: white;
    background-size: contain;
    background-position: center;
    background-repeat: no-repeat;
    /* transform: scale(2, 3); */

    /* transition: transform 0.1s; */
  }
`;

const StyledRow = styled.div`
  display: flex;
  justify-content: center;
`;

export default MbtiButton;

// import React, { useState } from 'react';
// import styled from 'styled-components';
// // import { Link } from 'react-router-dom';

// const buttons = [
//   { id: 1, text: 'INFP' /*, path: '/IFNP' */ },
//   { id: 2, text: 'ENFP' },
//   { id: 3, text: 'ISFP' },
//   { id: 4, text: 'ESFP' },
//   { id: 5, text: 'INTP' },
//   { id: 6, text: 'ENTP' },
//   { id: 7, text: 'ISTP' },
//   { id: 8, text: 'ESTP' },
//   { id: 9, text: 'INFJ' },
//   { id: 10, text: 'ENFJ' },
//   { id: 11, text: 'ISFJ' },
//   { id: 12, text: 'ESFJ' },
//   { id: 13, text: 'INTJ' },
//   { id: 14, text: 'ENTJ' },
//   { id: 15, text: 'ISTJ' },
//   { id: 16, text: 'ESTJ' },
// ];

// const images = {
//   INTP: require('./intp.png'),
//   INTJ: require('./intj.png'),
//   INFJ: require('./infj.png'),
//   ENTP: require('./entp.png'),
// };

// const StyledText = styled.div`
//   font-size: 2vh;
//   margin-bottom: 2vh;
//   position: absolute;
//   bottom: 42%;
//   left: 6%;
//   cursor: pointer;
// `;
// const StyledContainer = styled.div`
//   width: 80vw;
//   height: 2vh;
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   position: absolute;
//   bottom: 50%;
//   left: 50%;
//   transform: translate(-50%, 0);
// `;

// const StyledButton = styled.button`
//   background-color: #edf8de;
//   border: none;
//   color: black;
//   padding: 3vh;
//   margin: 3vh;

//   text-align: center;
//   font-size: 2vw;
//   width: 10vw;
//   height: 5vw;

//   border-radius: 2vh;

//   &:hover {
//     cursor: pointer;
//     background-image: url(${props => images[props.button.text]});
//     color: black;
//     border: solid;
//     border-color: black;
//     background-color: white;
//     background-size: contain;
//     background-position: center;
//     background-repeat: no-repeat;
//   }
// `;

// const StyledRow = styled.div`
//   display: flex;
//   justify-content: center;
// `;

// const ButtonPage = () => {
//   const [showButtons, setShowButtons] = useState(true);
//   const [text, setText] = useState('>>MBTI별 게시판');

//   const buttonRows = [];
//   for (let i = 0; i < buttons.length; i += 4) {
//     buttonRows.push(buttons.slice(i, i + 4));
//   }

//   const handleClick = () => {
//     setShowButtons(!showButtons);
//     setText(
//       text === '>>MBTI별 게시판 펼치기'
//         ? '>>MBTI별 게시판 접기'
//         : '>>MBTI별 게시판 펼치기'
//     );
//   };

//   return (
//     <>
//       <StyledText onClick={handleClick}>{text}</StyledText>
//       {showButtons && (
//         <>
//           <StyledContainer>
//             {buttonRows.map((row, index) => (
//               <StyledRow key={index}>
//                 {row.map(button => (
//                   <StyledButton key={button.id} button={button}>
//                     {button.text}
//                   </StyledButton>
//                 ))}
//               </StyledRow>
//             ))}
//           </StyledContainer>
//         </>
//       )}
//     </>
//   );
// };

// export default ButtonPage;
