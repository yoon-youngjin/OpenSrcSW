# 커밋
> 시간에 따라 변화되는 내용만 관리하고, 코드가 변화된 시간 순서에 따라서 영구적으로 저장

- [x] 깃의 커밋은 새로 변경된 부분만 추출하여 저장 
- [x] 파일 이름을 변경하지 않고도 동일한 파일 이름 하나로 관리가 가능

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161252743-12167ec8-baf6-4893-a623-d39a54c9798b.png" width="600px" />
</p>

- [x] index.html이 새로 생성되었지만 untracked 상태임
- [x] tracked 상태로 만들기 위해서 add 해야함

## 깃에 새파일 등록
- [x] untracked 상태 -> tracked 상태
  
- [x] git add 파일: 워킹 디렉토리 -> 스테이지 영역
  > `git add 파일이름`

### 파일 등록 취소: tracked 상태 -> untracked 상태

> `git rm --cached index.html`


- [x] 파일을 등록한 후 커밋하지 않고 바로 삭제(워킹 디렉토리에 원본을 두고 스테이지 영역에서만 삭제)


### 등록된 파일 이름이 변경되었을 때

- 작업 도중 파일 이름도 변경할 수 있음
- 깃에서도 파일 이름을 변경할 때 mv 명령어를 사용함

> `git mv 파일이름 새파일이름`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161253600-3338650b-a81f-4ec0-b549-79b5bd414f0a.png" width="600px" />
</p>

- [x] 이름을 변경할 index.html은 stage 영역
- [x] git mv는 말풍선의 3단계를 축약해 놓은 것

## 첫 번째 커밋

### HEAD

- [x] 최종적인 커밋 작업의 위치를 가리키는 포인터

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161253822-98897c10-771a-480e-a642-ea1ddbf67fb4.png" width="600px" />
</p>

### 스냅샷
- [x] HEAD가 가리키는 커밋을 기반으로 사진을 찍음
- [x] 깃은 시스템적인 단점(=파일을 복사하며 저장)을 해결하려고 변경된 파일 전체를 저장하지 않고, 파일에서 변경된 부분만 찾아 수정된 내용만 저장함
- [x] 현재 HEAD와 스테이지 영역을 비교하여 새로운 커밋으로 기록함
- [x] 깃의 스냅샷 방식을 이용하여 빠르게 버전의 차이점을 처리하고, 용량을 적게 사용

> 스냅샷?
> 
> 현재 HEAD가 가리키는 커밋을 기준으로 사진을 찍는다 = 현재 HEAD와 스테이지 영역을 비교하여 새로운 커밋으로 기록한다 => 빠르게 버전의 차이점을 처리하고, 용량을 적게 사용

### 파일 상태와 커밋
> `git commit`
> 
> 스테이지 영역의 파일을 최종 repo로 만들어 버전 컨트롤을 시작

- [x] **워킹 디렉토리가 깨끗하게 정리되어 있지 않으면 커밋 명령어를 수행할 수 없음**
- [x] 커밋을 하려면 스테이지 영역에 새로운 변경 내용이 있어야 함
- [x] 수정된 내용이 스테이지 영역으로 등록되지 않으면 커밋을 할 수 없음
  > `git commit -a`
  > 
  > 파일 등록과 커밋을 동시에 하는 방법

## 커밋 확인

### 로그 기록 확인

> `git log`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161254756-aa71f50e-bcec-4dde-9dce-45a495d5a2fd.png" width="600px" />
</p>

- [x] 시간순으로 커밋 기록을 출력

## 두 번째 커밋

### 파일 수정 - 파일 변경 사항 확인

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161254896-153b5bda-bdad-43bb-8db3-8ee0de2d7c4c.png" width="600px" />
</p>

### 수정된 파일 되돌리기
- [x] 깃을 이용하면 수정한 파일을 커밋 전 마지막 내용으로 쉽게 되돌릴 수 있음
- [x] 수정 파일을 되돌리면 이전 커밋 이후에 작업한 수정 내역은 모두 삭제함
> `git checkout -- 수정파일이름`

### 스테이지에 등록
- [x] 기존 파일을 수정하면 해당 파일은 modified 상태로 변경
- [x] 다시 워키 디렉토리로 이동
- [x] 파일이 수정되면 반드시 **add 명령어로 스테이지 영역에 재등록**해야 함

## 메시지가 없는 빈 커밋

- [x] 커밋을 할 때는 반드시 커밋 메시지를 같이 작성
- [x] 의미가 없는 커밋이라 커밋 메시지를 생략하고 싶을 때도 있음

> `git commit --allow-empty-message -m ""`

## 커밋 아이디
<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161255511-e334444c-33f2-43aa-833d-79e607e2a223.png" width="600px" />
</p>

## 커밋 로그

### 간략 로그

> `git log --pretty=short`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161255646-d354fa82-6fc9-46c3-b0de-ba0d67d7b8cb.png" width="600px" />
</p>


- [x] 특정 커밋의 상세 정보도 확인할 수 있음

> `git show 커밋ID`

- [x] 특정 파일의 로그

> `git log 파일 이름`


## diff 명령어

- [x] 커밋 간 차이를 확인
- [x] 아직 add 명령어로 파일을 추가하지 않은 경우, 워킹 디렉토리와 스테이지 영역 간 변경사항을 비교할 수 있음

> `git diff`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161255882-06ffd806-940c-4a8c-9a32-8dfac3bebd51.png" width="600px" />
</p>

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161255892-a70e4f05-c1af-4ed0-ab78-cdb8387b4b1a.png" width="600px" />
</p>

- [x] 스테이지 영역과 워킹 디렉토리 내용이 같아졌으므로 내용 출력X

### 커밋 간 차이
- [x] 워킹 디렉토리와 마지막 커밋을 비교
> `git diff head`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/161256059-a8bd92b4-c41f-4c6f-9d38-2404a2cf53be.png" width="600px" />
</p>

### diff 내용을 추가하여 커밋
> `git commit -v`