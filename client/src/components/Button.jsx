import styled from 'styled-components';

const StyledButton = styled.button`
  color: black;
  font-size: 1.3rem;
  border-radius: 0.5rem;
  padding: 1rem 3rem;
  margin: 2rem;
  border: none;
  width: 10rem;
  background: ${(props) => props.background || '#64B5FF'};
`;

function Button({ children, ...props }) {
  return <StyledButton {...props}>{children}</StyledButton>;
}

export default Button;
