import styled from 'styled-components';
import { useState } from 'react';
import axios from 'axios';

const BoardAnswer = ({ username, content }) => {
  return (
    <AnswerForm>
      <div className="answernickname">{username}</div>
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        <span>수정</span>
        <span>삭제</span>
      </div>

      <div className="answercontent">{content}</div>
    </AnswerForm>
  );
};

const AnswerForm = styled.div`
  color: #767676;
  height: 130px;
  border-bottom: 1px solid black;
`;

export default BoardAnswer;
