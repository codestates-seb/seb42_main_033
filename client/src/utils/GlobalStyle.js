import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  * {
    box-sizing: border-box;
  }

  body {
    padding-left: 45px;
    padding-right: 45px;
    padding-top: 20px;
  }
`;
export default GlobalStyle;
