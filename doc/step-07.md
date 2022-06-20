# 복귀

## 8.1 되돌리기
- 깃에서 코드 작업을 되돌리는 방법은 크게 `reset`과 `revert` 두 가지

> git add . : 스테이지 영역에 등록

### 다시 시작
- menu.html 파일에 menu1 ~ menu5를 차례대로 추가하여 계속 커밋

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675250-4650b965-cece-4814-b9ca-cd7535656243.png" width="500px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162675267-e58b879d-fbd2-4870-8ab1-de5a7cd0483f.png" width="500px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162676311-19aaf7a1-42b1-41f2-a6a5-3962e0e145f0.png" width="500px" height="250px">
</center>

## 8.2 리셋
- 리셋은 커밋을 기준으로 이전 코드로 되돌리는 방법으로, 기록한 커밋을 취소함
- 커밋을 취소하는 만큼 리셋할 때는 항상 신중하게 작업해야 함

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675262-29dfbe2d-581d-4038-992a-fc5ddd8ce5d4.png" width="600px">
</center>

### reset 명령어

> `git reset 옵션 커밋ID`

- 옵션
  - soft: 스테이지 영역을 포함한 상태로 복원함 -> 커밋만 취소하고 스테이지 영역은 남김
  - mixed: 커밋과 스테이지 영역을 취소하고 워킹 디렉토리만 남김 (디폴트 값)
  - hard: 워킹 디렉토리까지 삭제하고 이전 상태로 복원함

### soft 옵션

> `git reset --soft HEAD~`
> 
> 이전 커밋으로 soft 옵션을 사용한 리셋

- soft 옵션은 가장 낮은 단계의 리셋 동작
- 최신 커밋(menu5)을 리셋

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675277-9aaa6629-c4db-4329-b9ad-6107a517f920.png" width="500px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162675282-ef5bcd5a-4d82-41fc-97b3-90f0ec8a2fe1.png" width="500px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162675292-3b6e23e6-ef1d-4350-a23a-2b7a5e2990fc.png" width="500px" height="250px">
</center>

- menu5 까지 있던 커밋이 사라지고 HEAD가 menu4를 가리키고 있음
- 스테이지 영역에는 남아있는 것을 볼 수 있음
- soft 리셋한 후 menu.html 파일의 내용에도 menu5가 그대로 남아있음

- [x] soft 옵션은 커밋 상태만 이전으로 돌아가고 스테이지 영역, 워킹 디렉토리는 그대로 남아있음을 알 수 있음

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675307-e0f4020d-f494-4fad-88c4-902b68768bb7.png" width="600px">
</center>

### mixed 옵션
- soft 리셋에서 삭제한 menu5 커밋 후, mixed 리셋 실행

> `git reset --mixed HEAD~`

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675313-8637d3df-4581-4a4b-8928-89e3f18f2fc5.png" width="400px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162675326-dc00f7e1-1e42-4253-a79d-41cafd92d918.png" width="400px" height="250px">
</center>

- menu5 커밋이 사라지고 HEAD가 menu4를 가리키고 있음
- menu.html 파일의 내용에는 menu5가 그대로 남아있음

- [x] soft와 다른점은 menu5가 스테이지 영역에서 사라지고 워킹 디렉토리로 감
- [x] 따라서 menu5를 다시 스테이지 영역으로 올리기 위해 add 후 commit 해야함

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675329-9f4435d9-05d3-4598-996c-a002bdd45ef8.png" width="600px">
</center>

### hard 옵션
- hard 옵션은 리셋되는 복귀 시점의 커밋 상태와 해당 커밋의 워킹 디렉토리까지 모두 되돌림

> `git reset –-hard HEAD~`

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675349-e6f9b79e-7bdd-4f79-8acc-2eefb1ea6b2b.png" width="400px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162675353-6b7aa686-53f3-422c-9aed-9a24ec4e1bb0.png" width="400px" height="250px">
</center>

- menu.html 파일의 menu5 커밋에 해당하는 `<li>menu5</li>` 코드가 삭제됨

- [x] **hard 옵션을 사용하면 워킹 디렉토리 내용도 함께 삭제**

### 커밋 합치기

- menu3과 menu4 커밋을 reset 명령어를 사용하여 합치기

> `git reset --soft HEAD~2`

> `git commit -m menu3/4`

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675372-38aaae7a-6450-4b4b-91ad-a59d0e7014ac.png" width="400px" height="250px">
    <img src="https://user-images.githubusercontent.com/83503188/162675382-faa9ff5b-bf0b-4cb2-9eb5-53e20d69343f.png" width="400px" height="250px">
</center>

### 스테이지 리셋
- add 명령어로 등록된 스테이지 영역의 파일을 다시 unstage 상태가 되도록 스테이징을 취소할 수 있음
- 스테이지 영역에서 등록된 파일을 다시 unstage 상태로 만들 때는 reset 명령어를 사용함

> `git reset 파일이름`

- 특정 커밋의 파일을 unstage 상태로 만들 경우

> `git reset 커밋ID 파일이름`

### 병합 취소

- 이전에 병합한 커밋 리셋

>  `git reset –-merge HEAD~`

## 8.2 리버트

### 취소 커밋
- 리셋은 기존 커밋 정보를 삭제하는 반면, **리버트는 기존 커밋을 남겨 두고 취소에 대한 새로운 커밋을 생성**
- 취소 커밋은 지정한 커밋을 삭제하지 않고 삭제를 위한 새로운 커밋을 생성함


<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675397-10591488-9588-4adb-944f-e64cf5de3f51.png" width="600px">
</center>

- [x] 커밋2로 돌아가고 싶다? 앞에 있던 커밋3을 지우는게 아니라 남겨두고 이전 커밋2 상태로 새로운 커밋을 생성

### 현재 커밋을 리버트

- menu6~7을 추가하고 저장한 후 커밋

> `git revert HEAD` 
> 
> 자동 vi 에디터 실행을 통해 메시지 작성 후 저장

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675406-036d02b2-606c-4d02-8a87-20745e6a8c88.png" width="600px">
</center>

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675414-544c1232-7de2-44c6-9496-749d6b299036.png" width="600px" height="250px">
</center>

- menu.html 파일에서 menu7 커밋에 해당하는 코드가 사라짐을 확인

- 직전의 커밋이 아닌 다른 커밋을 취소할 때는 커밋 해시 값을 지정

>  `git revert 커밋ID`

- 깃의 범위 지정 연산자(..)를 사용하여 여러 커밋을 리버트할 수도 있음

> `git revert 커밋ID .. 커밋ID`

### 병합 취소
- **리버트를 이용하여 병합한 커밋을 취소할 수 있음**
- **리셋은 방금 전 실행한 병합만 삭제함**
- 리버트는 시간이 지난 후에도 과거의 병합을 선택하여 취소할 수 있음


- 리버트로 병합이 취소 상태가 되면 둘 중 한 브랜치로 체크아웃해야 함
- `--mainline` 옵션은 병합을 취소한 후 체크아웃되는 브랜치를 표시함

> `git revert –-mainline 숫자 병합커밋ID`

<center>
    <img src="https://user-images.githubusercontent.com/83503188/162675456-af4d7ede-6764-4e40-9871-cb23e57b45c0.png" width="600px">
</center>

- 취소된 상태의 새로운 커밋 하나 생성

### 리버트 히스토리
- 리버트를 실행하면 새 커밋이 추가되기 때문에 커밋 이력이 복잡함
- 리셋으로 간단하게 이전 상태로 되돌리는 것이 간편해 보일 수도 있음
- 저장소를 공개했다면 리셋으로 커밋을 삭제하는 것은 협업 차원에서 위험
- 이때는 리버트가 유용
