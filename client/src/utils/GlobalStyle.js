import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  * {
    box-sizing: border-box;
  }

  body {
    padding-left: 50px;
    padding-right: 50px;
    background-color: pink;
    padding-top: 20px;
  }
`;
export default GlobalStyle;
