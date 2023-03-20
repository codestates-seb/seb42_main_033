import styled from 'styled-components';
import { AiOutlineEllipsis } from 'react-icons/ai';
import { FaHeart, FaCommentAlt } from 'react-icons/fa';
import PostModal from './PostModal'
import { useState } from 'react';
import BoardAnswer from './BoardAnswer';
import { Link } from 'react-router-dom';

const BoardCarddetail = (props) => {
  let [like, setLike] = useState(0);
  let [count, setCount] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const handleClick = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <>
      <Container>
        <div className="boardwrap">
          <HeaderLink to="/PostlistPage">0000 게시판!!</HeaderLink>
          <div className="boardview">
            <div className="boardheader">
              <div className="title">
                {props.title}
                <ModalContainer onClick={handleClick}>
                  <EditDelete />
                </ModalContainer>
                {isModalOpen && (
                  <PostModal
                    onClose={() => setIsModalOpen(false)}
                    isOpen={isModalOpen}
                    id={props.id}
                    title={props.title}
                    content={props.content}
                  />
                )}
              </div>
              <div className="nickname">{props.username}</div>
              <div className="createdate">{props.createdAt}</div>
            </div>
            <div className="boardcontent">
              <div className="content">{props.content}</div>
              <>
                <div className="like">
                  <span>
                    <Like
                      onClick={() => {
                        setLike(like + 1);
                      }}
                      style={{ fontSize: '20px' }}
                    />
                  </span>
                  <span style={{ paddingBottom: '40' }}>{like}</span>
                  <span className="commenticon">
                    <CommentIcon
                      onChange={() => {
                        setCount(count);
                      }}
                    />
                  </span>
                  <span style={{ paddingBottom: '40', marginLeft: '10px' }}>
                    {count}
                  </span>
                </div>
              </>
            </div>
          </div>
          <div className="answerview">
            <BoardAnswer />
            <BoardAnswer />
            <div className="writranswer">
              <input
                placeholder="댓글 쓰기"
                style={{
                  width: '700px',
                  height: '35px',
                  marginTop: '20px',
                  paddingLeft: '10px',
                }}
              />
              <ButtonLink to="/PostlistPage">
                <button
                  style={{
                    height: '40px',
                    width: '70px',
                    marginLeft: '10px',
                  }}
                >
                  작성
                </button>
              </ButtonLink>
            </div>
          </div>
        </div>
      </Container>
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
    height: 1000px;
    left: 50%;
    position: absolute;
    top: 65%;
    transform: translate(-50%, -50%);
    box-sizing: border-box;
  }
  @media only screen and (max-width: 1440px) {
    //해상도 1440이하 모니터
    position: absolute;
    display: flex;
    top: 60%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin-top: 100px;
    width: 800px;
    height: 1100px;
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
    }
  }
  div.boardcontent {
    height: 240px;
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
const EditDelete = styled(AiOutlineEllipsis)`
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
