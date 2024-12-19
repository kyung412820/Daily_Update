<p align="center">
 <img width="100px" src="https://res.cloudinary.com/anuraghazra/image/upload/v1594908242/logo_ccswme.svg" align="center" alt="GitHub Readme Stats" />
    <h2 align="center">[Daily] 일정표</h2>
</p>
<br/>

<h2>목차</h2>

- [📜 소개](#소개)
- [👨‍👧‍👦 팀원](#팀원)
- [⚙️ 개발 환경](#개발-환경)
- [🎲 기능 목록](#기능-목록)
- [🧩 핵심 기능](#핵심-기능)
- [✍ Trouble Shooting](https://kyunghun0515.tistory.com/30)
<br/>

<h2>소개</h2>

이 프로젝트는 간단한 일정 관리 애플리케이션으로, 사용자가 일정 데이터를 입력하고 관리할 수 있는 기능을 제공합니다. 추가로, 게시물 생성 및 조회, 수정, 삭제와 같은 기본적인 CRUD 기능도 구현되었습니다. 사용자 친화적인 UI와 직관적인 사용 방법을 통해 누구나 쉽게 활용할 수 있습니다. 이 애플리케이션은 Java Spring Framework를 기반으로 개발되었으며, Git을 사용해 버전 관리가 이루어졌습니다.

주요 기능은 아래와 같습니다:

게시물 생성 및 저장:

작성자와 작업 내용을 입력하여 새로운 게시물을 생성하고 저장할 수 있습니다.


게시물 조회:

모든 게시물을 목록 형태로 조회할 수 있으며, 특정 조건(ID, 날짜)으로 검색할 수 있는 기능도 제공됩니다.


게시물 수정:

기존 게시물의 데이터를 수정할 수 있으며, 실시간으로 반영됩니다.


게시물 삭제:

선택한 게시물을 삭제하여 데이터베이스에서 제거할 수 있습니다.


에러 처리:

잘못된 데이터 입력(예: ID 누락) 또는 연산 오류에 대해 사용자에게 명확한 에러 메시지를 제공합니다.


사용자 인터페이스:

직관적이고 사용하기 쉬운 UI를 제공하여, 사용자 경험을 향상시킵니다.

<br/>

## 개발 환경
- JAVA
- GIT
<br/>

## 팀원

<table>
   <tr>
    <td align="center"><b><a href="https://github.com/kyung412820">이경훈</a></b></td>
  <tr>
    <td align="center"><a href="https://github.com/kyung412820"><img src="https://avatars.githubusercontent.com/u/71320521?v=4" width="100px" /></a></td>
  </tr>
  <tr>
    <td align="center"><b>프로젝트 총괄</b></td>
</table>
<br/>


## 기능 목록

 - DB, 클라, 서버간의 통신 구현
 - JDBC를 이용하여 DB와의 통신
 - Servlet을 통한 서버 데이터 처리, 응답

<br/>

## 핵심 기능

#### 1. PostController (컨트롤러)
역할:
클라이언트 요청을 처리하고 결과를 반환합니다.
게시물 저장, 조회, 수정, 삭제 등 주요 기능을 처리합니다.
메서드별 기능
savePost (POST /mvc/posts/save)

사용자 입력 데이터를 받아 새로운 게시물을 저장합니다.
postuser 테이블과 postwork 테이블에 데이터를 저장합니다.
입력값 유효성 검사를 수행합니다.
listPosts (GET /mvc/posts/list)

저장된 모든 게시물을 조회하여 화면에 표시합니다.
searchPosts (GET /mvc/posts/search)

특정 ID나 날짜에 해당하는 게시물을 검색하여 결과를 반환합니다.
editPost (GET /mvc/posts/edit)

특정 ID에 해당하는 게시물을 조회하여 수정 화면으로 이동합니다.
updatePost (POST /mvc/posts/update)

수정된 게시물 데이터를 받아 기존 게시물을 업데이트합니다.
deletePost (GET /mvc/posts/delete)

특정 ID에 해당하는 게시물을 삭제합니다.

#### 2. PostRepository (저장소)
역할:
데이터베이스와 직접적으로 연결되어 CRUD 작업을 수행합니다.


주요 메서드

savePostUser: postuser 테이블에 사용자 정보를 저장합니다.

savePostWork: postwork 테이블에 작업 정보를 저장합니다.

findAllPosts: 모든 게시물을 조회합니다.

findPostUserById: 특정 ID에 해당하는 게시물 데이터를 조회합니다.

updatePostUser, updatePostWork: 게시물 데이터를 수정합니다.

deletePostUserById, deletePostWorkByUserId: 특정 ID에 해당하는 게시물을 삭제합니다.

findPostSearch: ID나 날짜 조건으로 게시물을 검색합니다.

#### 3. 데이터베이스 테이블
게시물 데이터를 저장하기 위해 두 개의 테이블(postuser, postwork)을 사용했습니다.


postuser 테이블

작성자 정보가 저장됩니다.

컬럼: id, name, email, origindate, date


postwork 테이블

작업 관련 정보가 저장됩니다.

컬럼: id, work, password, date, user_id
#### 4. 주요 기능
게시물 저장

/mvc/posts/save로 POST 요청을 보내면 Post 객체를 데이터베이스에 저장합니다.


입력값 검증:

@Valid 어노테이션으로 데이터 유효성을 검사합니다.

나누기 0 같은 오류는 예외 처리로 대응.


게시물 목록 조회

/mvc/posts/list로 GET 요청을 보내면 모든 게시물을 화면에 출력합니다.


게시물 검색

/mvc/posts/search로 GET 요청을 보내 ID 또는 날짜를 조건으로 게시물을 검색합니다.


게시물 수정

/mvc/posts/edit로 특정 ID에 해당하는 게시물을 수정합니다.

수정된 데이터를 /mvc/posts/update로 POST 요청하여 저장합니다.


게시물 삭제

/mvc/posts/delete로 GET 요청을 보내 특정 ID의 게시물을 삭제합니다.


프로젝트 구조
Controller: PostController

클라이언트 요청을 받아 처리.

데이터를 Repository와 JSP로 전달.

Repository: PostRepository

데이터베이스 CRUD 작업 담당.

JSP 뷰:

데이터를 화면에 표시하거나 입력받는 역할.

------------------------------------------------------------------------
<br/>

## Trouble Shooting

[https://kyunghun0515.tistory.com/90
<br/>
<br/>
<br/>
<br/>
