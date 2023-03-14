// import React from 'react';
import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faUser,
  faFileClipboard,
  faCommentDots,
  faEnvelope,
  faCircleXmark,
} from '@fortawesome/free-regular-svg-icons';
import { Link } from 'react-router-dom';

const Sidebar = styled.div`
  * {
    margin: 0;
    padding: 0;
  }
  nav {
    right: 80%;
    bottom: 50%;
    width: 240px;
    height: 436px;
    box-shadow: 2px 2px 2px 2px rgb(194, 194, 194);
    border-radius: 5px;
    background-color: #f4f4f4;
    position: fixed;
  }
  .first-li {
    margin-top: 45px;
  }
  li {
    list-style-type: none;
    margin-bottom: 50px;
    margin-left: 35px;
    font-size: 20px;
    font-weight: bold;
  }

  a {
    text-decoration: none;
    color: black;
  }

  span {
    margin-left: 15px;
  }

  .icon {
    color: #5491eb;
  }
`;
const MyPageSidebar = () => {
  return (
    <div>
      <Sidebar>
        <nav>
          <ul>
            <li className="first-li">
              {/* <a href="#">
                내 정보 수정</a> 링크 추가할 것*/}
              <FontAwesomeIcon className="icon" icon={faUser} />
              <span>내 정보 수정</span>
            </li>
            <li>
              <FontAwesomeIcon className="icon" icon={faFileClipboard} />
              <Link to="/Mypost">내 게시글</Link>
            </li>
            <li>
              <FontAwesomeIcon className="icon" icon={faCommentDots} />
              <Link to="/MyComments">내 댓글</Link>
            </li>
            <li>
              <FontAwesomeIcon className="icon" icon={faEnvelope} />
              <span>쪽지함</span>
            </li>
            <li>
              <FontAwesomeIcon className="icon" icon={faCircleXmark} />
              <span>회원 탈퇴</span>
            </li>
          </ul>
        </nav>
      </Sidebar>
    </div>
  );
};

export default MyPageSidebar;
