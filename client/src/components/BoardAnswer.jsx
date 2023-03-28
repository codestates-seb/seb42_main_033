import styled from 'styled-components';

const BoardAnswer = ({ comment, commentDelete }) => {
  return (
    <AnswerForm>
      <div className="answernickname">{comment.user.nickName}</div>
      {/* {comment.userId === userId && ( */}
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        {/* <AnswerButton>수정</AnswerButton> */}
        <AnswerButton
          onClick={() => {
            commentDelete();
          }}
        >
          삭제
        </AnswerButton>
      </div>
      {/* )} */}
      <div className="answercontent"> {comment.content} </div>
    </AnswerForm>
  );
};

const AnswerForm = styled.div`
  color: #767676;
  height: 120px;
  border-bottom: 1px solid black;
  text-align: left;
  padding-left: 10px;
  .answernickname {
    font-size: 15px;
    font-weight: 700;
    padding-top: 5px;
    width: 700px;
  }
  .answercontent {
    font-size: 17px;
    margin-top: 20px;
    width: 700px;
    margin-top: 10px;
  }
`;
const AnswerButton = styled.button`
  background-color: rgba(0, 0, 0, 0);
  border: solid 0px rgba(0, 0, 0, 0);
  width: 50px;
`;

export default BoardAnswer;
