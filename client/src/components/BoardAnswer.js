import styled from 'styled-components';
import { FaHeart } from 'react-icons/fa';
import { useState } from 'react';

const BoardAnswer = () => {
  let [comlike, setComlike] = useState(0);

  const Like = styled(FaHeart)`
    color: #64b5ff;
    margin-right: 10px;
  `;
  const AnswerForm = styled.div`
    color: #767676;
    height: 130px;
    border-bottom: 1px solid black;
    padding: 10px;
    .answernickname {
      font-size: 15px;
      font-weight: 700;
    }
    .answercontent {
      font-size: 17px;
      margin-top: 20px;
    }
    .answerlike {
      margin-top: 30px;
    }
  `;

  return (
    <AnswerForm>
      <div className="answernickname">인프피 맞습니다</div>
      <div
        className="answerbutton"
        style={{ marginLeft: '730px', fontSize: '13px' }}
      >
        <span>수정</span> <span>삭제</span>
      </div>
      <div className="answercontent">
        이정도면 괜찮을거 같기도 하고 아닐거 같기도 하고 잘
        모르겠네여어어어어어어어
      </div>
      <div className="answerlike">
        <Like
          onClick={() => {
            setComlike(comlike + 1);
          }}
        />
        {comlike}
      </div>
    </AnswerForm>
  );
};

export default BoardAnswer;
