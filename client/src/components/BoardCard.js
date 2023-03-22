import styled from 'styled-components';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AiFillEye, AiFillHeart } from 'react-icons/ai';
import { FaCommentAlt } from 'react-icons/fa';
const BoardCard = ({
  postId,
  title,
  content,
  username,
  likeCount,
  viewCount,
  commentCount,
  createdAt,
}) => {
  const navigate = useNavigate();

  return (
    <div>
      <CardLayout
        onClick={() => {
          navigate(`/PostviewPage/${postId}`);
        }}
      >
        <div className="posttitle"> 제목{title} </div>
        <div className="postcontent">
          <span className="content">내용{content}</span>
          <span className="username" style={{ marginLeft: 'auto' }}>
            닉네임{username}
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
            작성시간 {createdAt}
          </span>
        </div>
      </CardLayout>
    </div>
  );
};

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
