import styled from 'styled-components';
import { AiOutlineEllipsis } from 'react-icons/ai';
import { FaHeart, FaCommentAlt } from 'react-icons/fa';
import PostModal from './PostModal.jsx';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import BoardAnswer from './BoardAnswer.jsx';
import axios from 'axios';

const BoardCarddetail = ({
  id,
  post,
  setPost,
  isLoaded,
  handleEdit,
  handleDelete,
}) => {
  const [like, setLike] = useState(0);
  const [count, setCount] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const token = localStorage.getItem('jwtToken');
  const userId = localStorage.getItem('userId');
  const [comment, setComment] = useState('');
  const [commentList, setCommentList] = useState([]);
  const [isValid, setIsValid] = useState(false);
  const commentCount = commentList.length;
  const postId = post.id;
  //게시글 수정삭제 모달
  const handleClick = () => {
    setIsModalOpen(!isModalOpen);
  };
  //좋아요
  const handleLikeClick = async () => {
    try {
      const response = await axios.post(
        `http://ec2-54-180-158-15.ap-northeast-2.compute.amazonaws.com:8080/board/integrated/${postId}/like`,
        {
          userId: userId,
          postId: postId,
        },
        {
          headers: {
            Authorization: token,
            'Content-Type': 'application/json',
          },
        }
      );
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };
  //댓글 조회
  const getComment = async () => {
    try {
      const response = await axios.get(
        `http://ec2-54-180-158-15.ap-northeast-2.compute.amazonaws.com:8080/board/integrated/${postId}/comment`
      );
      console.log('status:', response.status);
      console.log('data:', response.data);
      return response.data;
    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    getComment().then((data) => {
      setCommentList(data);
    });
  }, [postId]);
  //댓글 등록
  const handleCommentSubmit = async (e) => {
    e.preventDefault();
    if (comment === '' || comment === null || comment === undefined) {
      alert('내용을 작성하십시오.');
      return false;
    }
    try {
      const config = {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };
      const data = {
        userId: userId,
        postId: postId,
        content: comment,
      };
      await axios.post(
        `http://ec2-54-180-158-15.ap-northeast-2.compute.amazonaws.com:8080/board/integrated/${postId}`,
        data,
        config
      );
      const newComment = {
        username: data.username,
        content: data.content,
      };
      setCommentList([...commentList, newComment]);
      setComment('');
      setCount(count + 1);
      setPost({ ...post, commentCount: commentCount + 1 });
    } catch (error) {
      console.log(error);
    }
  };
  //댓글 삭제
  const commentDelete = async () => {
    const cmtuserId = comment.userId;
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    try {
      await axios.delete(
        `http://ec2-54-180-158-15.ap-northeast-2.compute.amazonaws.com:8080/board/integrated/${postId}/comment/${userId}`,
        config
      );
      navigate(`/PostviewPage/${postId}`);
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      {isLoaded && (
        <Container>
          <div className="boardwrap">
            <HeaderLink to="/PostlistPage">통합 게시판</HeaderLink>
            <div className="boardview">
              <div className="boardheader">
                <div className="title">
                  {post.title}
                  <ModalContainer onClick={handleClick}>
                    {/* {userId === post.userId && <EditDeletIcon />} */}
                    {token && userId === post.userId && <EditDeletIcon />}
                  </ModalContainer>
                  {isModalOpen && (
                    <PostModal
                      onClose={() => setIsModalOpen(false)}
                      isOpen={isModalOpen}
                      onEdit={handleEdit}
                      onDelete={handleDelete}
                    />
                  )}
                </div>
                <div className="nickname">{post.nickName}</div>
                <div className="createdate">{post.createdAt}</div>
              </div>
              <div className="boardcontent">
                <div className="content">{post.content}</div>
                <>
                  <div className="like">
                    <span>
                      <Like
                        onClick={() => {
                          setLike(like + 1);
                          handleLikeClick();
                        }}
                        style={{ fontSize: '20px' }}
                      />
                    </span>
                    <span style={{ paddingBottom: '40' }}>
                      {post.likeCount}
                    </span>
                    <span className="commenticon">
                      <CommentIcon
                        onChange={() => {
                          setCount(count);
                        }}
                      />
                    </span>
                    <span style={{ paddingBottom: '40', marginLeft: '10px' }}>
                      {post.commentCount}
                    </span>
                  </div>
                </>
              </div>
            </div>
            <div className="answerview">
              {commentList.map((comment, index) => (
                <BoardAnswer
                  key={index}
                  comment={comment}
                  commentDelete={commentDelete}
                />
              ))}
              <div className="writranswer">
                <input
                  placeholder="댓글 쓰기"
                  style={{
                    width: '700px',
                    height: '35px',
                    marginTop: '20px',
                    paddingLeft: '10px',
                  }}
                  value={comment}
                  onChange={(e) => setComment(e.target.value)}
                  onKeyUp={(e) =>
                    e.target.value.length > 0
                      ? setIsValid(true)
                      : setIsValid(false)
                  }
                />
                <button
                  style={{
                    height: '40px',
                    width: '70px',
                    marginLeft: '10px',
                  }}
                  type="button"
                  onClick={handleCommentSubmit}
                >
                  작성
                </button>
              </div>
            </div>
          </div>
        </Container>
      )}
    </>
  );
};
const Container = styled.div`
  @media only screen and (min-width: 1441px) {
    //해상도 1440보다 큰 모니터
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 800px;
    height: 1500px;
    left: 50%;
    position: absolute;
    top: 100%;
    transform: translate(-50%, -50%);
    box-sizing: border-box;
  }
  @media only screen and (max-width: 1440px) {
    //해상도 1440이하 모니터
    position: absolute;
    display: flex;
    top: 70%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin-top: 45vh;
    width: 800px;
    height: 1500px;
    margin-bottom: 700px;
  }
  div.boardheader {
    height: 70px;
    padding: 10px;
    border-bottom: 1px solid black;
    .title {
      height: 40px;
      display: flex;
      /* align-items: center; */
      font-size: 22px;
    }
    .nickname {
      display: flex;
      font-size: 12px;
      margin-top: 5px;
      color: #959595;
      font-weight: 700;
    }
    .createdate {
      position: absolute;
      right: 0;
      font-size: 10px;
    }
  }
  div.boardcontent {
    height: 200px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 10px;
    margin-top: 10px;
    border-bottom: 1px solid black;
    letter-spacing: 2px;
    .like {
      align-items: center;
      justify-content: center;
      margin-top: auto;
      margin-left: 30%;
      padding-bottom: 10px;
    }
    .commenticon {
      margin-left: 200px;
    }
  }
  div.writranswer {
    height: 70px;
    button {
      background-color: #64b5ff;
      border-radius: 5px;
      border: none;
      color: #ffffff;
      font-weight: 700;
      font-size: 18px;
    }
  }
`;
const HeaderLink = styled(Link)`
  color: #4363c4;
  background-color: #ffffff;
  width: 860;
  height: 40px;
  display: flex;
  align-items: center;
  padding: 10px;
  font-size: 35px;
  font-weight: 700;
  border-bottom: 2px solid #4363c4;
  text-decoration: none;
`;
const EditDeletIcon = styled(AiOutlineEllipsis)`
  position: absolute;
  top: 10;
  right: 0;
  font-size: 25px;
`;

const ModalContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  cursor: pointer;
`;
const Like = styled(FaHeart)`
  color: #64b5ff;
  margin-right: 10px;
`;

const CommentIcon = styled(FaCommentAlt)`
  font-size: 20px;
  color: #64b5ff;
`;
const ButtonLink = styled(Link)`
  text-decoration: none;
`;

export default BoardCarddetail;
