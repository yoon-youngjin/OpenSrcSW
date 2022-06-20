# 병합과 충돌

## 7.1 병합

- [x] 브랜치를 생성하는 목적은 원본 코드에 영향을 주지 않고 분리하여 개발하기 위함
- [x] 독립된 브랜치에서 개발 작업이 끝나면 다시 원본 브랜치에 작업한 결과를 반영해야 함
- [x] 병합: 분리된 브랜치를 한 브랜치로 합치는 작업

### 하나씩 직접 비교하는 수동 병합

- [x] 여러 개발자와 코드를 공유하면서 변경된 소스 코드를 병합하는 것은 매우 복잡
- [x] 오류 없이 코드를 병합하는 것은 간단하지 않음

<p align="center">
    <img src="https://user-images.githubusercontent.com/83503188/162604345-8fb38c29-977b-453e-b5f1-c4421d8893fd.png" width="600px">
</p>

### 깃으로 자동 병합

> `git merge 브랜치이름`

- [x] 깃의 자동 병합은 원본을 기준으로 두 파일의 변경 이력을 비교함
- [x] 변경된 파일 내용이 발견되면 자동으로 수정된 코드 내용을 병합함

## 7.2 Fast-Forward 병합
- [x] 일반적으로 Fast-Forward 병합 방식은 혼자 개발할 때 사용
- [x] 혼자 개발할 때는 브랜치가 커밋에 따라 순차적으로 분기
- [x] 브랜치가 분기되지만 전체 커밋 그림으로 보면 모든 변경 사항은 순차적으로 진행
- [x] 순차적 커밋에 맞추어 병합을 처리하는 방법이 Fast-Forward 병합
   <div style="text-align : center;">
       <img src="https://user-images.githubusercontent.com/83503188/162604346-5f52cc5e-af95-4be4-9012-352e8b184155.png" width="400px" height="250px">
       <img src="https://user-images.githubusercontent.com/83503188/162604347-2c0f96a2-376d-4a75-8431-4c026df3726b.png" width="400px" height="250px">
       <img src="https://user-images.githubusercontent.com/83503188/162604348-79720190-9ef0-49b4-b5a7-67b358df015c.png" width="400px" height="250px">
   </div>

## 7.3 3-way 병합

- [x] 여러 개발자와 협업으로 작업하는 경우 대부분 3-way 병합을 사용
    <p align="center">
        <img src="https://user-images.githubusercontent.com/83503188/162604351-e4fb932d-f5c6-49ba-8ccc-8fb53029401f.png" width="600px">
    </p>
       
     > **공통 조상 커밋**
     > 
     > 두 브랜치를 병합하려면 먼저 분할 기준인 공통 커밋을 찾아야 함
- [x] 공통 조상 커밋을 포함하는 브랜치와 새로운 두 브랜치, 이렇게 3개를 하나로 병합해야 함
   <p align="center">
       <img src="https://user-images.githubusercontent.com/83503188/162604355-89994771-aac3-4a4e-888d-979dcc1cfea3.png" width="600px">
   </p>
  
- [x] 깃의 3-way 병합은 공통 조상 커밋을 자동으로 찾아 주며, 공통 조상 커밋을 기준으로 브랜치를 병합함 
- [x] 병합을 성공적으로 완료한 후에는 새로운 커밋을 추가로 하나 생성
- [x] 새로 생성된 커밋을 `병합 커밋`이라고 함
     <div style="text-align : center;">
         <img src="https://user-images.githubusercontent.com/83503188/162604356-d95a12b2-4559-40f4-903f-bedaeaf0b4f2.png" width="400px" height="250px">
         <img src="https://user-images.githubusercontent.com/83503188/162605254-5d25b6ce-065f-47f8-b889-91bd92342967.png" width="400px" height="250px">
     </div> 

## 7.4 브랜치 삭제

> `git branch -d 브랜치이름`

- [x] 일반적으로 병합한 이후에는 병합된 브랜치를 삭제함
- [x] 지속적으로 통합과 개발을 해야 하는 브랜치라면 병합 후에도 계속 남겨 둠
- [x] 병합을 완료하지 않은 브랜치를 삭제하고 싶다면 대문자 `-D` 옵션을 사용

> `git branch -D 브랜치이름`

## 7.5 충돌
    
- [x] 대부분 충돌 원인은 여러 사람이 같은 위치의 코드를 동시에 수정했기 때문
- [x] 파일에서 동일한 위치에 두 명 이상이 서로 다르게 수정했다면 충돌이 발생
- [x] 이때 깃은 충돌 오류라고 알려 주고, 개발자에게 직접 수정하여 충돌을 해결하라고 요청

<p align="center">
  <img src="https://user-images.githubusercontent.com/83503188/162604358-5acd01ab-8da1-46b9-be67-5eb69b0638b8.png" width="400px" height="250px">
</p>

### 충돌 예방 방법

- [x] 내부적으로 팀원 간 규칙을 정하고 상의하면서 개발을 진행하면 향후 발생할 충돌을 줄임
- [x] master 브랜치 내용을 자주 자신의 브랜치로 병합
- [x] 자주 커밋하고 병합할수록 충돌이 발생할 기회는 적음
- [x] 많은 내용을 수정할수록 병합할 때 충돌이 발생하기 쉬움
- [x] 자신의 브랜치 상태가 최신일수록 향후 병합할 때 발생하는 충돌을 최소화 할 수 있음

## 7.6 브랜치 병합 여부 확인

> `git branch -merged
`
- [x] 깃은 병합한 브랜치와 병합하지 않은 브랜치를 구분하는 옵션을 제공
- [x] 병합한 브랜치는 `별표(*)` 기호로 표시
- [x] 병합하지 않은 브랜치 확인

<p align="center">
    <img src="https://user-images.githubusercontent.com/83503188/162604359-f74819b6-e837-4770-9923-1745a7734e14.png" width="600px">
</p>

> `git branch --no-merged`

- [x] 병합하지 않은 브랜치는 `-d` 옵션으로 삭제되지 않으므로 `-D` 옵션을 사용 