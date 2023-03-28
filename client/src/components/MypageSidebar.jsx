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
  width: 185px;
  height: 360px;
  box-shadow: 2px 2px 2px 2px rgb(194, 194, 194);
  border-radius: 5px;
  background-color: #f4f4f4;
  position: fixed;
  padding: 10px;
  text-align: left;
  left: 13%;
  bottom: 25%;

  ul {
    padding: 0;
  }
  .first-li {
    margin-top: 38px;
  }
  li {
    list-style-type: none;
    margin-left: 20px;
    margin-bottom: 40px;
    font-size: 18px;
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
              <Link to="/EditProfile">
                <FontAwesomeIcon className="icon" icon={faUser} />
                <span>내 정보 수정</span>
              </Link>
            </li>
            <li>
              <Link to="/Mypost">
                <FontAwesomeIcon className="icon" icon={faFileClipboard} />
                <span>내 게시글</span>
              </Link>
            </li>
            <li>
              <Link to="/MyComments">
                <FontAwesomeIcon className="icon" icon={faCommentDots} />
                <span>내 댓글</span>
              </Link>
            </li>
            <li>
              <FontAwesomeIcon className="icon" icon={faEnvelope} />
              <span>쪽지함</span>
            </li>
            <li>
              <Link to="/Signout">
                <FontAwesomeIcon className="icon" icon={faCircleXmark} />
                <span>회원 탈퇴</span>
              </Link>
            </li>
          </ul>
        </nav>
      </Sidebar>
    </div>
  );
};

export default MyPageSidebar;
