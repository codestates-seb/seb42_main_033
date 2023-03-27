import styled from 'styled-components';
import { AiFillEye, AiFillHeart } from 'react-icons/ai';
import { FaCommentAlt } from 'react-icons/fa';
import { Link } from 'react-router-dom';
const BoardCard = ({
  key,
  id,
  title,
  content,
  nickname,
  viewCount,
  likeCount,
  commentCount,
  createdAt,
}) => {
  return (
    <div>
      <CardLayout>
        <CardLink to={`/PostViewPage/${id}`}>
          <div className="posttitle"> {title} </div>
          <div className="postcontent">
            <span className="content">{content}</span>
            <span className="username" style={{ marginLeft: 'auto' }}>
              {nickname}
            </span>
          </div>
          <div className="cardbottom">
            <span className="viewCount">
              <AiFillEye size="15px" />
              <span className="view">20 {viewCount}</span>
            </span>
            <span className="likeCount">
              <AiFillHeart size="14px" />
              <span className="like"> 20 {likeCount}</span>
            </span>
            <span className="commentCount">
              <FaCommentAlt size="11px" />
              <span className="cmt"> 20 {commentCount}</span>
            </span>
            <span className="createdAt" style={{ marginLeft: 'auto' }}>
              {createdAt}
            </span>
          </div>
        </CardLink>
      </CardLayout>
    </div>
  );
};
const CardLink = styled(Link)`
  text-decoration: none;
  color: #000;
  :visited {
    color: #000;
  }
  :active {
    color: #000;
  }
`;
const CardLayout = styled.div`
  display: flex;
  flex-direction: column;
  width: 908px;
  height: 111px;
  background-color: #ffffff;
  margin-left: 10px;
  margin-top: 14px;
  text-decoration: none;
  div.posttitle {
    font-size: 1.3rem;
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
  }
  div.postcontent {
    padding-top: 10px;
    padding-left: 10px;
    display: flex;
  }
  div.cardbottom {
    padding-top: 10px;
    display: flex;
  }
  .content {
    font-size: 0.9rem;
    text-align: left;
  }
  .username {
    font-size: 1rem;
    padding-right: 10px;
    padding-top: 10px;
  }
  .viewCount {
    padding-left: 10px;
    padding-right: 10px;
  }
  .view {
    padding-left: 5px;
    font-size: 0.9rem;
  }
  .likecount {
    padding-left: 10px;
    padding-right: 5px;
  }
  .like {
    font-size: 0.9rem;
  }
  .commentCount {
    padding-left: 10px;
    padding-right: 5px;
  }
  .cmt {
    font-size: 0.9rem;
    padding-left: 5px;
    padding-bottom: 10px;
  }
  .createdAt {
    font-size: 0.9rem;
    padding-right: 10px;
  }
`;
export default BoardCard;
