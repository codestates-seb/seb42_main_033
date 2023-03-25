import { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import MyPageSidebar from '../components/MypageSidebar.jsx';
/*121212*/

// const comments = [
//   {
//     id: 1,
//     text: '안녕',
//     comments: [
//       {
//         id: 1,
//         text: '반가워요!',
//       },
//       {
//         id: 2,
//         text: '안녕하세요!',
//       },
//     ],
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

function MyPost() {
  const [post, setPost] = useState([]);
  const [selectedComments, setSelectedComments] = useState([]);

  // const { id } = useParams();
  // const postId = localStorage.getItem('id'); 11

  const token = localStorage.getItem('jwtToken');

  const getPost = async () => {
    try {
      const userId = localStorage.getItem('userId');
      console.log();
      const response = await axios.get(
        `${process.env.REACT_APP_API_URL}/board/integrated`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response);
      const userPosts = response.data.filter(
        (post) => post.userId === parseInt(userId)
      );
      const sortedUserPosts = userPosts.sort((a, b) => b.id - a.id);
      return sortedUserPosts;
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getPost().then((data) => {
      setPost(data);
    });
  }, []);

  const deletePosts = async () => {
    try {
      const deletePromises = selectedComments.map((postId) => {
        return axios.delete(
          `${process.env.REACT_APP_API_URL}/board/integrated/${postId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
      });
      await Promise.all(deletePromises);
      setPost(
        post.filter((postItem) => !selectedComments.includes(postItem.id))
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
      <PageContainer>
        <MyPageSidebar />
        <CommentPageContainer>
          <StyledText>내 게시글</StyledText>

          <CommentsContainer>
            <HeaderContainer>선택</HeaderContainer>
            <hr />
            {post &&
              post.map((postItem) => (
                <CommentContainer key={postItem.id}>
                  <input
                    type="checkbox"
                    id={`checkbox-${postItem.id}`}
                    checked={selectedComments.includes(postItem.id)}
                    onChange={() => handleCheckboxClick(postItem.id)}
                  />
                  <CommentText htmlFor={`checkbox-${postItem.id}`}>
                    <p>{postItem.title}</p>
                    <p>({postItem.commentCount})</p>
                  </CommentText>
                </CommentContainer>
              ))}
          </CommentsContainer>
          <DeleteButton onClick={deletePosts}> 삭제 </DeleteButton>
        </CommentPageContainer>
      </PageContainer>
    </>
  );
}
const PageContainer = styled.div`
  display: flex;
`;

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
  position: relative;

  & p:last-child {
    color: orange;
    position: absolute;
    left: 100%;
    bottom: 0;
  }
`;
export default MyPost;

// function MyPost() {
//   const [post, setPost] = useState([]);
//   const [selectedComments, setSelectedComments] = useState([]);

//   // const { id } = useParams();
//   // const postId = localStorage.getItem('id');

//   const token = localStorage.getItem('access_token');

//   const getPost = async () => {
//     try {
//       const userId = localStorage.getItem('userId');
//       console.log();
//       const response = await axios.get(
//         `http://ec2-3-39-227-39.ap-northeast-2.compute.amazonaws.com:8080/board/integrated`,
//         {
//           headers: {
//             Authorization: `Bearer ${token}`,
//           },
//         }
//       );
//       console.log(response);
//       const userPosts = response.data.filter(
//         (post) => post.userId === parseInt(userId)
//       );
//       const sortedUserPosts = userPosts.sort((a, b) => b.id - a.id);
//       return sortedUserPosts;
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   useEffect(() => {
//     getPost().then((data) => {
//       setPost(data);
//     });
//   }, []);

//   const deletePosts = async () => {
//     try {
//       const deletePromises = selectedComments.map((postId) => {
//         return axios.delete(
//           `https://3218-211-217-72-99.jp.ngrok.io/board/integrated/${postId}`,
//           {
//             headers: {
//               Authorization: `Bearer ${token}`,
//             },
//           }
//         );
//       });
//       await Promise.all(deletePromises);
//       setPost(
//         post.filter((postItem) => !selectedComments.includes(postItem.id))
//       );
//       setSelectedComments([]);
//     } catch (error) {
//       console.error(error);
//     }
//   };

//   const handleCheckboxClick = (id) => {
//     if (selectedComments.includes(id)) {
//       setSelectedComments(selectedComments.filter((cid) => cid !== id));
//     } else {
//       setSelectedComments([...selectedComments, id]);
//     }
//   };

//   return (
//     <>
//       <PageContainer>
//         <MyPageSidebar />
//         <CommentPageContainer>
//           <StyledText>내 게시글</StyledText>

//           <CommentsContainer>
//             <HeaderContainer>선택</HeaderContainer>
//             <hr />
//             {post &&
//               post.map((postItem) => (
//                 <CommentContainer key={postItem.id}>
//                   <input
//                     type="checkbox"
//                     id={`checkbox-${postItem.id}`}
//                     checked={selectedComments.includes(postItem.id)}
//                     onChange={() => handleCheckboxClick(postItem.id)}
//                   />
//                   <CommentText htmlFor={`checkbox-${postItem.id}`}>
//                     <p>{postItem.title}</p>
//                     <p>({postItem.commentCount})</p>
//                   </CommentText>
//                 </CommentContainer>
//               ))}
//           </CommentsContainer>
//           <DeleteButton onClick={deletePosts}> 삭제 </DeleteButton>
//         </CommentPageContainer>
//       </PageContainer>
//     </>
//   );
// }
// export default MyPost;
