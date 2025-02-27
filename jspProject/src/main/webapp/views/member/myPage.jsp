<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <style>
        .outer {
            background-color: rgb(154, 163, 247);
            color: black;
            width: 1000px;
            margin: auto;
            margin-top: 50px;
            padding: 10px 0px 50px;
        }

        .outer table {
            margin: auto;
        }

        #update-pwd-modal .modal-body form{
            display: flex;
            flex-direction: column;
        }

        #delete-modal .modal-body form{
            display: flex;
            flex-direction: column;
        }
    </style>
</head>

<body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <br>
        <h2 align="center">내 정보</h2>
        <form action="${pageContext.request.contextPath}/update.me" method="post" id="update-form">
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="15" readonly value="${loginUser.userId}">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="8" readonly value="${loginUser.userName}">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="text" name="phone" placeholder="- 포함해서 입력" value="${loginUser.phone}"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="text" name="email" value="${loginUser.email}"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="address" value="${loginUser.address}"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>관심분야</td>
                    <td colspan="2">
                        <label>
                            <input type="checkbox" name="interest" value="운동">운동
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="등산">등산
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="낚시">낚시
                        </label>
                        <br>
                        <label>
                            <input type="checkbox" name="interest" value="요리">요리
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="게임">게임
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="영화">영화
                        </label>
                        <label>
                            <input type="checkbox" name="interest" value="기타">기타
                        </label>
                    </td>
                </tr>
            </table>
            <script>
                const interest = "${loginUser.interest}";
                const inputArr = document.querySelectorAll("input[name=interest]");

                for (let input of inputArr) {
                    if (interest.includes(input.value)) { // interest에 input.value의 값이 포함되어 있다면
                        input.checked = true;
                    }
                }
            </script>

            <br><br>

            <div align="center">
                <button type="submit" class="btn btn-sm btn-success">정보수정</button>
                <button type="button" class="btn btn-sm btn-warning" data-bs-toggle="modal"
                    data-bs-target="#update-pwd-modal">비밀번호 변경</button>
                <button type="button" class="btn btn-sm btn-danger"  data-bs-toggle="modal"
                data-bs-target="#delete-modal">회원탈퇴</button>
            </div>
        </form>
    </div>
    
        	<!-- 
		탈퇴하기 버튼 클릭시
		탈퇴 후 복구가 불가능합니다.
		정말 탈퇴하시겠습니까?
		비밀벊호 : 
		탈퇴하기버튼 -> /delete.me
		=>  /delete.me받은 서버는 비밀번호가 맞는지 확인 후 status -> N으로 변경하고 로그아웃
	 -->
     <div class="modal" id="delete-modal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원 탈퇴</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/delete.me" method="post">
                        <table align="center">
                            <tr>
                                <b align="center">탈퇴 후 복구가 불가능합니다. <br>
                                    정말 탈퇴하시겠습니까?</b>
                                    <br>
                                <td>비밀번호</td>
                                <td><input type="password" name="userPwd" required></td>
                            </tr>
                        </table>
                        <br>
                        <button type="submit" class="btn btn-sm btn-danger">탈퇴하기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function checkPwd() {
            const pwd = document.querySelector("#enroll-form input[name=userPwd]").value;
            const pwdcheck = document.querySelector("#enroll-form input[name=userPwdCheck]").value;

            if (pwd !== pwdcheck) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }
        }
    </script>
    <!-- 비밀번호 변경 팝업 -->
    <div class="modal" id="update-pwd-modal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">비밀번호 변경</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/updatePwd.me" method="post">
                        <table align="center">
                            <tr>
                                <td>현재 비밀번호</td>
                                <td><input type="password" name="userPwd" required></td>
                            </tr>
                            <tr>
                                <td>변경할 비밀번호</td>
                                <td><input type="password" name="updatePwd" required></td>
                            </tr>
                            <tr>
                                <td>변경할 비밀번호 확인</td>
                                <td><input type="password" name="checkPwd" required></td>
                            </tr>
                        </table>
                        <br>
                        <button id="edit-pwd-btn" type="submit" class="btn btn-sm btn-primary">비밀번호 변경</button>
                    </form>

                    <script>
                        document.getElementById('edit-pwd-btn').onclick = function(){
                            const pwd = document.querySelector("#update-pwd-modal input[name=updatePwd]").value;
                            const pwdcheck = document.querySelector("#update-pwd-modal input[name=checkPwd]").value;

                            if (pwd !== pwdcheck) {
                                alert("변경할 비밀번호가 일치하지 않습니다.");
                                return false;
                            }
                        }
                    </script>

                </div>
            </div>
        </div>
    </div>
</body>

</html>