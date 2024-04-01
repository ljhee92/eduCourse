# Education Course Management Application
이 프로젝트는 대학 종합정보시스템을 개발하여 주 사용자인 관리자, 교수, 학생 각각에게 맞춤형 서비스를 제공합니다. <br>
이를 통해 관리자는 학사 관리를 효율적으로 수행하고, 교수는 강의 및 학생 성적 관리를 용이하게 하며, <br>
학생은 강의 등록 및 성적 확인 등의 업무를 간편하게 처리할 수 있습니다. 
<br>
<br>

## 사용방법
이 프로젝트를 실행하기 위해서는 별도의 의존성 패키지나 환경설정이 필요하지 않습니다. <br>
run.bat파일을 실행해 주세요. <br>
로그인 기능이 포함되어 있으며, 초기 계정명과 패스워드는 다음과 같습니다.
* 관리자 모드<br>
  ID: admin<br>
  PW: master<br>
  <br>
* 교수 모드<br>
  ID: 100403141<br>
  PW: prof<br>
  <br>
* 학생 모드<br>
  ID: 202403141<br>
  PW: student<br>
<br>
<br>

## 개발 기간 및 작업 관리
개발 기간 : 2024-03-08 ~ 2024-03-29<br>
개발 언어 : Java17<br>
작업 관리 : GitHub<br>
<br>
<br>

## 리팩토링 기간
리팩토링 기간 : 2024-03-31 ~ 진행중<br>
<br>
<br>

## 프로젝트 구조
```
├─.settings
├─bin
│  ├─eduCourse_prj
│  │  ├─admin
│  │  │  ├─dao
│  │  │  ├─design
│  │  │  └─event
│  │  ├─image
│  │  │  ├─admin
│  │  │  ├─common
│  │  │  ├─crs
│  │  │  ├─login
│  │  │  ├─prof
│  │  │  └─stud
│  │  ├─login
│  │  ├─professor
│  │  │  ├─dao
│  │  │  ├─design
│  │  │  └─event
│  │  ├─run
│  │  ├─student
│  │  │  ├─dao
│  │  │  ├─design
│  │  │  └─event
│  │  └─VO
│  └─test
│      └─admin
└─src
    ├─eduCourse_prj
    │  ├─admin
    │  │  ├─dao
    │  │  ├─design
    │  │  └─event
    │  ├─image
    │  │  ├─admin
    │  │  ├─common
    │  │  ├─crs
    │  │  ├─login
    │  │  ├─prof
    │  │  └─stud
    │  ├─login
    │  ├─professor
    │  │  ├─dao
    │  │  ├─design
    │  │  └─event
    │  ├─run
    │  ├─student
    │  │  ├─dao
    │  │  ├─design
    │  │  └─event
    │  └─VO
    └─test
        └─admin
```
<br>
<br>

## 기능
Role은 관리자/학생/교수 세 가지 모드로 나뉘어져있습니다.
<br>
* 관리자 모드<br>
  관리자모드 로그인 시 관리자 관리, 교수 관리, 학과 관리, 수강학생 조회, 과목 관리, 학사일정 관리가 가능합니다.<br>
  <br>
* 학생모드<br>
  학생모드 로그인 시 접속된 계정의 정보 수정, 수강신청, 수강신청 내역 확인이 가능합니다.<br>
  시험 응시와 결과 확인, 응시한 시험의 정오표, 학사일정을 확인할 수 있습니다.<br>
  <br>
* 교수 모드<br>
  교수모드 로그인 시 접속된 계정의 정보 수정이 가능합니다.<br>
  강의 과목 관리, 시험 관리, 학생 조회와 학습 성취도 확인이 가능합니다.<br>
<br>
<br>

## 기여 및 문의
프로젝트에 대한 버그 리포트, 기능 제안 또는 코드 리뷰는 [👉🏻이곳👈🏻](https://github.com/ljhee92/eduCourse/issues)에 남겨주시면 감사하겠습니다.
<br>
<br>

## 배포 가능성
이 프로젝트는 얼마든지 배포할 수 있습니다.
<br>
<br>

## 연락처
프로젝트 관리자에게 문의하려면 아래의 이메일 주소로 연락해주세요.<br>
Email: ljhee92.sist@gmail.com