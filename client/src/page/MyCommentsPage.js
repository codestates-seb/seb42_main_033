import { useState, useEffect } from 'react';
import styled from 'styled-components';
import MyPageSidebar from '../components/MypageSidebar.jsx';
import axios from 'axios';
import { useParams } from 'react-router-dom';
/*123*/

// const comments = [
//   {
//     id: 1,
//     text: '안녕',
//   },
//   {
//     id: 2,
//     text: '하세요',
//   },
//   {
//     id: 3,
//     text: '오오오오오',
//   },
//   {
//     id: 4,
//     text: '오오오오오',
//   },
//   {
//     id: 5,
//     text: '오오오오오',
//   },
//   {
//     id: 6,
//     text: '오오오오오',
//   },
//   {
//     id: 7,
//     text: '오오오오오',
//   },
//   {
//     id: 8,
//     text: '오오오오오',
//   },
// ];

function MyComments() {
  const [comments, setComments] = useState([]);
  const [selectedComments, setSelectedComments] = useState([]);
  const userId = localStorage.getItem('userId');
  const token = localStorage.getItem('jwtToken');


  const userId = useParams();
  const token = localStorage.getItem('access_token');

  const URL = `http://ec2-3-39-235-30.ap-northeast-2.compute.amazonaws.com:8080`;

  const getComments = async () => {
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_API_URL}/board/integrated/${userId}/comment/`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const userComments = response.data.filter(
        (comment) => comment.userId === parseInt(userId)
      );
      const sortedUserComments = userComments.sort((a, b) => b.id - a.id);
      return sortedUserComments;
    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    getComments();
  }, []);

  const deleteComment = async () => {
    try {
      const deletePromises = selectedComments.map((commentId) => {
        return axios.delete(`${URL}/board/integrated/1/comment/${commentId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
      });
      await Promise.all(deletePromises);
      setComments(
        comments.filter(
          (comment) => !selectedComments.includes(comment.content)
        )
      );
      setSelectedComments([]);
    } catch (error) {
      console.error(error);
    }
  };

  const handleCheckboxClick = (id) => {
    if (selectedComments.includes(id)) {
      setSelectedComments(selectedComments.filter((cid) => cid !== id));
    } else {
      setSelectedComments([...selectedComments, id]);
    }
  };

  return (
    <>
      <MyPageSidebar />
      <CommentPageContainer>
        <StyledText>내 댓글</StyledText>
        <CommentsContainer>
          <HeaderContainer>선택</HeaderContainer>
          <hr />
          {comments &&
            comments.map((commentItem) => (
              <CommentContainer key={commentItem.id}>
                <input
                  type="checkbox"
                  id={`checkbox-${commentItem.id}`}
                  checked={selectedComments.includes(commentItem.id)}
                  onChange={() => handleCheckboxClick(commentItem.id)}
                />
                <CommentText htmlFor={`checkbox-${commentItem.id}`}>
                  <p>{commentItem.content}</p>
                </CommentText>
              </CommentContainer>
            ))}
        </CommentsContainer>
        <DeleteButton onClick={deleteComment}> 삭제 </DeleteButton>
      </CommentPageContainer>
    </>
  );
}
const CommentPageContainer = styled.div`
  font-size: 1vw;
  display: flex;
  flex-direction: column;
  align-items: center;

  justify-content: center;
  position: absolute;
  top: 15%;
  left: 55%;
  transform: translate(-50%, 20%);
`;

const CommentsContainer = styled.div`
  border: 0.05vh solid black;
  padding: 10px;
  width: 45vw;
  height: 100%;
  flex: 1 1 auto;
  width: 60vw;
`;

const CommentContainer = styled.div`
  display: flex;
  align-items: center;
  border-bottom: 0.05vh solid black;
  padding: 1px;
`;

const StyledText = styled.div`
  font-size: 4vh;
  font-weight: bold;
  margin-bottom: 2vh;
  position: absolute;
  bottom: 100%;
  left: 1%;
`;

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

const DeleteButton = styled.button`
  background-color: gray;
  width: 13vh;
  height: 6vh;
  color: white;
  padding: 5px 10px;
  margin: auto;
  border: none;
  border-radius: 5px;
  position: absolute;
  right: 10%;
  bottom: -15%;
  cursor: pointer;
`;

const CommentText = styled.label`
  margin-left: 5vw;
  margin-top: 0px;
`;
export default MyComments;
