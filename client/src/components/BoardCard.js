import { Link } from 'react-router-dom';
import styled from 'styled-components';

const BoardCard = () => {
  const CardLayout = styled(Link)`
    display: flex;
    width: 908px;
    height: 111px;
    background-color: #ffffff;
    justify-content: center;
    align-items: center;
    margin-left: 10px;
    margin-top: 14px;
    text-decoration: none;
  `;
  return (
    <div>
      <CardLayout to="/PostviewPage">!!테스트</CardLayout>
      <CardLayout>!!테스트</CardLayout>
      <CardLayout>!!테스트</CardLayout>
      <CardLayout>!!테스트</CardLayout>
      <CardLayout>!!테스트</CardLayout>
    </div>
  );
};

export default BoardCard;
