import styled from 'styled-components';
import { useState } from 'react';
import axios from 'axios';

const BoardAnswer = ({ username, content }) => {
  return (
    <AnswerForm>
      <div className="answernickname">인프피 맞습니다{username}</div>
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        <span>수정</span>
        <span>삭제</span>
      </div>

      <div className="answercontent">
        이정도면 괜찮을거 같기도 하고 아닐거 같기도 하고 잘
        모르겠네여어어어어어어어{content}
      </div>
    </AnswerForm>
  );
};

const AnswerForm = styled.div`
  color: #767676;
  height: 130px;
  border-bottom: 1px solid black;
`;

export default BoardAnswer;
