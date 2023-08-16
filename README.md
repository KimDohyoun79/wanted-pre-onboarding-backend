
# 🏷️wanted_pre_onBoarding BackEnd 선발과제

<div align="center">
 <img src="https://img.shields.io/badge/SpringBoot-6DB33F.svg?logo=Spring-Boot&logoColor=white" />
 <img src="https://img.shields.io/badge/SpringBoot-6DB33F.svg?logo=Spring-Boot&logoColor=white" />
 <img src="https://img.shields.io/badge/SpringSecurity-6DB33F.svg?logo=Spring-Security&logoColor=white" />
 <img src="https://img.shields.io/badge/MySQL-3776AB.svg?logo=MySql&logoColor=white" />
 <img src="https://img.shields.io/badge/Docker-2496ED.svg?logo=Docker&logoColor=white" />
 <img src="https://img.shields.io/badge/AmazonEC2-FF9900.svg?logo=Amazon-EC2&logoColor=white" />
 <img src="https://img.shields.io/badge/Gradle-02303A.svg?logo=Gradle&logoColor=white" />

</div>

# 지원자
### 김도현

<br>

---
# 테스트 코드
![controllerCep](https://github.com/KimDohyoun79/wanted-pre-onboarding-backend/assets/57426594/b65b9460-996d-443c-893e-5f37222f90ff)


--- 

# 애플리케이션 실행 방법

[//]: # (Environment Variable에 아래 값들을 설정하고 실행 합니다.)
[//]: # ()
[//]: # (|환경변수명| 예제                                                                             |)

[//]: # (|---|--------------------------------------------------------------------------------|)

[//]: # (|SPRING_DATASOURCE_URL| jdbc:mysql://ec2-1-23-456-789.ap-northeast-2.compute.amazonaws.com:3306/wanted |)

[//]: # (|SPRING_DATASOURCE_USERNAME| root                                                                           |)

[//]: # (|SPRING_DATASOURCE_PASSWORD| password                                                                       |)


--- 

# 데이터베이스 테이블 구조
![erd](https://github.com/KimDohyoun79/wanted-pre-onboarding-backend/assets/57426594/d2537358-be96-45dc-a785-9a9e5316adac)
--- 

# 구현한 API의 동작을 촬영한 데모 영상 링크

--- 

# 구현 방법 및 이유에 대한 간략한 설명

--- 

# API 명세(request/response 포함)

### UserController

<details>
<summary>회원가입 </summary>
<div markdown="1">

- [POST] `/api/v1/users/signup`
  - #### Request
  ```json
    {
      "email" : "dokim123@naver.com",
      "password" : "dokim12345",
      "userName": "dokim"
    }
    ```
  - #### Response 성공
  ```json
  {
      "id": 1,
      "email": "dokim123@naver.com",
      "userName": "dokim"
  }
    ```
  
</div>
</details>


<details>
<summary> 로그인 </summary>
<div markdown="1">

- [POST] `/api/v1/users/login`
  - #### Request
  ```json
  {
      "email" : "dokim123@naver.com",
      "password" : "dokim12345"
  }
  ```

  - #### Response 성공
  ```json
  {
      "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InN0cmluZzEyQDEyIiwiaWF0IjoxNjkyMTkxMjE1LCJleHAiOjE2OTIxOTQ4MTV9.LQmSW0w0-AAJoA8LoaQ_gjzej3dTUXNoVixeXx3LnV4"
  }
  ```

</div>
</details>


### PostController

<details>
<summary> 게시글 생성 </summary>
<div markdown="1">

- [POST] `/api/v1/posts/createPost`
  - #### Request
  ```json
  {
    "title": "안녕하세요",
    "body": "만나서 반갑습니다."
  }
  ```

  - #### Response 성공
  ```json
  {
    "message": "게시글 등록 완료",
    "postId": 1
  }
  ```

</div>
</details>


<details>
<summary> 게시글 전체 조회 </summary>
<div markdown="1">

- [GET] `/api/v1/posts`
  - #### Response 성공
  ```json
  

  {
    "id": 10,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:09",
    "lastModifiedAt": ""
  },
  {
    "id": 9,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:09",
    "lastModifiedAt": ""
  },
  {
    "id": 8,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:08",
    "lastModifiedAt": ""
  },
  {
    "id": 7,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:08",
    "lastModifiedAt": ""
  },
  {
    "id": 6,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:07",
    "lastModifiedAt": ""
  },
  {
    "id": 5,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:07",
    "lastModifiedAt": ""
  },
  {
    "id": 4,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:06",
    "lastModifiedAt": ""
  },
  {
    "id": 3,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:06",
    "lastModifiedAt": ""
  },
  {
    "id": 2,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:04",
    "lastModifiedAt": ""
  },
  {
    "id": 1,
    "title": "안녕하세요",
    "body": "만나서 반갑습니다.",
    "userEmail": "string12@12",
    "createdAt": "2023-08-16 22:18:57",
    "lastModifiedAt": ""
  }

  ```

</div>
</details>


<details>
<summary> 게시글 1개 조회 </summary>
<div markdown="1">

- [GET] `/api/v1/posts/{postId}`

  - #### Response 성공
  ```json
  {
    "id": 10,
    "title": "string",
    "body": "string",
    "userEmail": "string123@naver.com",
    "createdAt": "2023-08-16 21:34:09",
    "lastModifiedAt": ""
  }
  ```

</div>
</details>


<details>
<summary> 게시글 수정 </summary>
<div markdown="1">

- [PUT] `/api/v1/posts/{postId}`
  - #### Request
  ```json
  {
    "title": "update title",
    "body": "update body"
  }
  ```

  - #### Response 성공
  ```json
  {
    "message": "게시물 수정 완료",
    "postId": 1
  }
  ```

</div>
</details>


<details>
<summary> 게시글 삭제 </summary>
<div markdown="1">

- [DELETE] `/api/v1/posts/{postId}`

  - #### Response 성공
  ```json
  {
    "message": "게시물 삭제 완료",
    "postId": 11
  }
  ```

</div>
</details>

