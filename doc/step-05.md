# 브랜치

## 6.1 새로운 작업

- [x] 큰 나무 줄기에서 작은 줄기가 뻗어 나오는 것처럼 저장 공간 하나에서 가상의 또 다른 저장 공간을 만드는 것이라고 생각하면됨
- [x] 커밋은 파일의 수정 이력을 관리하는데 사용한다면, 브랜치는 프로젝트를 독립적으로 관리하는데 사용함

### 깃 브랜치 특징

- [x] 깃 브랜치는 기존 폴더를 복제하는 것과 다르게 가상 폴더를 사용하여 개발 작업을 구분함

> 가상 폴더
>
> 깃의 브랜치는 작업 폴더를 실제로 복사하지 않고, 가상 폴더를 생성
>
> 다른 버전 관리 시스템은 폴더의 파일 전체를 복사하는 반면, 깃은 41바이트를 가지는 해시(SHA1) 파일 하나만 만들면 됨
>
> 외부적으로는 물리적인 파일 하나만 있는 것으로 보임

## 6.2 깃 브랜치 생성

- [x] 처음 깃을 초기화할 때 워킹 디렉토리는 master 브랜치를 생성
- [x] 브랜치를 생성하려면 기준이 되는 브랜치 또는 커밋이 하나 있어야 함
- [x] 브랜치는 깃에서 또 하나의 **개발 분기점**을 의미함

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125285-d1ba0ac1-fbcd-434f-9be4-904f491ab438.png" width="600px" />
</p>


> `git branch 브랜치이름 커밋ID`
>
> 브랜치 이름만 입력하면 현재 HEAD 포인터를 기준으로 새로운 브랜치를 생성
>
> 직접 커밋ID 인자 값을 지정하면, 지정한 커밋 ID를 기준으로 브랜치를 생성

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125297-d18d8428-5a9f-498d-ab24-34092b02ece0.png" width="400px" height="250px"/>
     <img src="https://user-images.githubusercontent.com/83503188/162125302-9d78cacc-4cc3-4d5d-95b9-0c1b1491170a.png" width="400px" height="250px"/>
     <img src="https://user-images.githubusercontent.com/83503188/162125308-bafe4108-7ab6-41ac-aa30-3cf5dd3456d1.png" width="400px" height="250px"/>
</p>

### 브랜치 이름

- [x] 브랜치 이름을 슬래시(/)를 사용하여 계층적인 구조로 만들어서 사용할 수 있음
- [x] 작성 규칙은 다음과 같음
  1. 기호(-)로 시작할 수 없음
  2. 마침표(.)로 시작할 수 없음
  3. 연속적인 마침표(..)를 포함할 수 없음
  4. 빈칸, 공백 문자, 물결(~), 캐럿(^), 물음표(?), 별표(*), 대괄호([]) 등은 포함할 수 없음
  5. 아스키 제어 문자는 포함할 수 없음
  6. **브랜치 이름은 중복해서 사용할 수 없음** -> 오류 발생

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125321-d6fc440f-bf94-402d-b8dd-c837dc5247be.png" width="600px" />
</p>

## 6.3 브랜치 확인

- [x] 간단한 브랜치 목록

> `git branch`

- [x] 브랜치 세부사항 확인
> `git branch -v`

### 브랜치 해시

- [x] 브랜치는 특정한 커밋의 해시 값(SHA1)을 가리킴
- [x] 깃의 저수준 명령어인 rev-parse를 사용하면 현재 브랜치가 어떤 커밋 해시 값(SHA1)을 가리키는지 확인할 수 있음

> `git rev-parse 브랜치이름`


<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162126796-fac31f74-2a27-419e-bacc-9c3296d0bbd6.png" width="600px" />
</p>

## 6.4 브랜치 이동

### 체크아웃

- [x] 현재 브랜치를 떠나 새로운 브랜치로 돌아간다는 의미
- [x] 깃에서 브랜치 간 이동할때는 checkout 명령어를 사용

> `git checkout 브랜치이름`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125335-478b6a52-8b1b-403f-8767-8fc65b8a5cec.png" width="600px" />
</p>

### 브랜치 동작 원리 

1. HEAD 정보는 항상 변경된 브랜치의 마지막 커밋을 가리킴, 이처럼 HEAD가 브랜치의 마지막 커밋을 의미하기 때문에 브랜치가 이동하면 HEAD 포인터도 함께 이동함
2. 변경된 브랜치로 새로운 작업을 할 수 있도록 워킹 디렉터리를 변경함, 브랜치를 변경하려면 기존 브랜치의 워킹 디렉터리를 정리해야함
3. 기존 브랜치의 워킹 디렉토리를 정리하지 않고서는 브랜치를 변경할 수 없음

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125345-0ba4fcc2-692d-43fa-99da-1de482b07110.png" width="600px" />
</p>

### 이전 브랜치 이동

> `git checkout -`

### 워킹 디렉토리 정리
- [x] 파일을 저장하고 커밋을 하지 않음
- [x] status 명령어를 통해 확인

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125350-2bb0f659-899f-40a6-8ca1-ca36b691c018.png" width="600px" />
</p>

- 현재 상태에서 브랜치를 변경 ?

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125353-46cb7b4e-3af8-48c6-9d80-621041c83a3e.png" width="600px" />
</p>

- 워킹 디렉토리의 수정 상태를 알려줌
- 브랜치 간에 정상적으로 이동하려면 남아있는 작업들을 모두 정리해 주어야 함

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125359-dda49782-1b61-4188-a474-90a0c08962d7.png" width="600px" />
</p>

## 6.5 브랜치 공간

### 브랜치 로그

- [x] 로그를 출력할 때 브랜치 흐름도 같이 보려면 --graph 옵션을 함께 사용

> `git log --graph --all`

## 6.6 HEAD 포인터

### 마지막 커밋

- [x] HEAD는 작업 중인 브랜치의 마지막 커밋 ID를 가리키는 참조 포인터
- [x] 브랜치를 이동하면 HEAD 포인터도 이동됨
- [x] 브랜치가 여러 개면 HEAD 포인터도 여러 개임
- [x] 브랜치마다 마지막 커밋 ID를 가리키는 HEAD 포인터가 하나씩 있음

### AHEAD, BHEAD

- [x] AHEAD 
  - [x] AHEAD는 서버로 전송되지 않은 로컬 커밋이 있는 것
  - [x] 로컬 저장소에 새로운 커밋을 생성하고, 새로운 커밋 정보를 서버로 전송하지 않는 상황
  - [x] 로컬 저장소의 HEAD 포인터를 기준으로 로컬 브랜치에 있는 커밋이 서버의 커밋 개수보다 많은 경우

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125370-dd6b8604-a256-4ae8-9e40-15e2d66f7271.png" width="600px" />
</p>

- [x] BHEAD
  - [x] BHEAD는 로컬 저장소로 내려받지 않은 커밋이 있는 것
  - [x] 다른 개발자가 코드를 수정하여 원격 저장소의 커밋이 자신의 로컬 저장소보다 더 최신상태인 것을 의미

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125373-90489e49-df9b-4c54-bb3e-09a115c472df.png" width="600px" />
</p>

## 6.7 생성과 이동

### 자동 이동 옵션

- [x] 체크아웃할 때 **`-b` 옵션**을 같이 사용하면 브랜치 생성과 이동을 한 번에 할 수 있음

> `git checkout -b 브랜치이름`

### 커밋 이동
- [x] 브랜치로 이동할 때 이름 대신 커밋 해시키를 사용하여 체크아웃할 수 도 있음

> `git checkout 커밋해시키`

### HEAD를 활용한 이동

- [x] 마지막 커밋인 HEAD를 기준으로 1단계의 커밋 상태로 이동함

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125386-16f76835-31bc-4095-9762-ecab294f5a0e.png" width="600px" />
</p>


> `git checkout HEAD~5`

### 이전 브랜치로 돌아오기

> `git checkout -`

## 6.8 원격 브랜치

### 리모트 브랜치
- [x] 원격 저장소에 생성한 브랜치
- [x] 저장소는 각자의 고유한 브랜치를 생성하고 관리
- [x] 로컬 저장소에 생성한 브랜치는 서버로 공유할 수 있음
- [x] 원격 저장소와 연결된 로컬 저장소에서 새로운 브랜치를 생성한다고 해서 자동으로 원격 저장소에도 브랜치가 생성되는 것은 아님
- [x] 원격 저장소에 등록된 브랜치가 자동으로 로컬 저장소를 만들지도 않음
- [x] 별도 명령을 실행하여 **저장소를 동기화**해야 함

### 브랜치 업로드
- [x] 등록된 원격 저장소의 리모트 브랜치는 remote show 명령어로 확인할 수 있음

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125397-81dd8f5d-a3d3-4765-b308-751d33cfe7e7.png" width="600px" />
</p>

- [x] 브랜치 없는 상태

- [x] 로컬 저장소의 브랜치를 원격 저장소에 동기화하려면 **푸시** 작업을 해야 함

> `git push 원격저장소별칭 브랜치이름`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125403-cd8e165f-1898-4d47-8d9a-2e9a2ad8405e.png" width="600px" />
</p>

> `-u`옵션: 업스트림 연결

- [x] 이름이 다른 브랜치

> `git push origin 브랜치이름:새로운브랜치이름`

### 원격 브랜치 복사
- [x] 원격 저장소와 로컬 저장소의 브랜치 목록은 서로 다를 수 있음
- [x] 다른 개발자가 원격 저장소에 새로운 리모트 브랜치를 생성할 수 있기 때문임
- [x] 이렇게 생성된 원격 저장소의 리모트 브랜치를 이용해서 로컬 저장소에도 새로운 브랜치를 생성하여 동기화할 수 있음

> `git checkout -b 새이름 origin/브랜치이름`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125409-46a41153-0c9a-4a4c-9819-965e87ec0ed9.png" width="400px" height="250px"/>
     <img src="https://user-images.githubusercontent.com/83503188/162125415-9d23d0d1-b7dc-431f-8444-5a0b1a4a3df4.png" width="400px" height="250px"/>
</p>

- [x] 잘 만들어졌는지 트래킹 브랜치 목록을 확인
> `git branch -vv`

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162129619-878ef61a-acaa-41ce-9070-9a0519398d62.png" width="600px" />
</p>

- [x] aaa 브랜치에서 코드를 수정한 후 커밋한 뒤 트래킹 브랜치 목록 다시 확인

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162129735-acc6a676-a151-42bc-a1ff-45fedc7e9693.png" width="600px" />
</p>

- [x] aaa 브랜치에서 서버로 전송하지 않은 커밋이 하나 있어 AHEAD 1로 표시

### 업스트림 연결(`-u`)

- [x] 기존에 있는 브랜치를 업스트림으로 직접 설정할 수도 있음
- [x] 브랜치를 생성한 후 직접 트래킹 브랜치를 지정함
- [x] 업스트림을 직접 설정하면 원격 저장소로 트래킹 브랜치가 설정됨

> `git branch -u origin/브랜치이름`
> 
> **기존 브랜치를 특정 원격 브랜치로 추적함**
> 
> 추적? 
> 
> 양쪽의 commit 내용이 달라지면 AHEAD, BHEAD를 통해 변경을 감시

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125425-17f340c7-aa43-4788-a8a5-87bd533994a1.png" width="600px" />
</p>


## 6.9 브랜치 전송

### 브랜치 푸시

- [x] 깃의 푸시 작업은 로컬 저장소의 파일들을 원격 저장소로 전송
- [x] 파일뿐만 아니라 브랜치 정보와 커밋까지 모두 전송
- [x] 처음으로 로컬 저장소에 새로운 원격 저장소가 등록되면 다음과 같이 push 명령어를 사용할 때 오류 메시지가 출력됨

<p align="center">
     <img src="https://user-images.githubusercontent.com/83503188/162125434-97128b12-5d9f-471a-a00d-5ae0ae336973.png" width="600px" />
</p>


- [x] 처음에는 커밋과 브랜치를 푸시하는데 업스트림 설정이 필요함
- [x] 원격 저장소 연결만으로 업스트림이 자동으로 설정되지는 않음

> `git push --set-upstream origin master`


### 브랜치 페치

- [x] 리모트 브랜치 페치는 일반적인 커밋 페치와 동일
- [x] 리모트 브랜치를 페치한다고 해서 자동으로 로컬 저장소에 새로운 브랜치가 생성되지는 않음
- [x] 페치 동작은 원격 저장소에서 리모트 브랜치 내용을 내려받기만 할 뿐이지 자동으로 병합하지 않기 때문임
- [x] 원격 저장소에서 페치된 커밋들을 새로운 로컬 브랜치로 반영하려면 병합 명령을 실행해야함

> `git fetch`
> 
> `git merge 원격저장소별칭/브랜치이름`

- [x] 가끔은 페치된 브랜치를 병합하지 않고 테스트만 하고 싶을 때도 있음
- [x] 이때는 원격 브랜치의 포인터를 사용하여 임시 브랜치를 생성하거나 직접 체크아웃할 수 있음

> `git checkout -b 임시브랜치이름 origin/브랜치이름`

## 6.10 브랜치 삭제

- [x] 일반적으로 브랜치를 삭제할 때는 `-d` 옵션을 사용
- [x] `-d` 옵션은 스테이지 상태가 깨끗할 때만 삭제를 허용

> `git branch -d 브랜치이름`

- [x] 리모트 브랜치를 삭제하는 방법 

> `git push origin --delete 리모트브랜치이름`