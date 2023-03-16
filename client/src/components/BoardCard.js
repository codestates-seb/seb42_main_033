import { Link } from 'react-router-dom';
import styled from 'styled-components';

const BoardCard = (props) => {
  const CardLayout = styled(Link)`
    display: flex;
    flex-direction: column;
    width: 908px;
    height: 111px;
    background-color: #ffffff;
    /* justify-content: center;
    align-items: center; */
    margin-left: 10px;
    margin-top: 14px;
    text-decoration: none;
    div.posttitle {
      font-size: 1.8rem;
      text-align: left;
      padding-top: 10px;
      padding-left: 10px;
    }
    div.postcontent {
      text-align: left;
      padding-top: 10px;
      padding-left: 10px;
    }
    div.postnickname {
      text-align: right;
      padding-right: 10px;
    }
  `;

  return (
    <div>
      <CardLayout to="/PostviewPage">
        <div className="posttitle"> {props.title} </div>
        <div className="postcontent"> {props.contents} </div>
        <div className="postnickname">post.nickname</div>
      </CardLayout>
    </div>
  );
};

export default BoardCard;
