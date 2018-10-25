# JustGo Android 개발 가이드
## 패키지 구조
### adapter
- RecyclerView Adapter, ViewPager Adapter 등 어댑터 코드를 넣습니다.
### connecter
- 통신을 위한 코드는 `Connecter` 패키지에 작성합니다.
- 서버 요청 url은 API interface에 작성합니다.
- `Connecter` 파일은 건드리지 않습니다.
- `apiCall` 에 코틀린용 서버 호출 코드를 작성해놓았습니다.
- 다음과 같이 사용합니다.
```kotlin
main("token"){
    onSuccess = {

    }
    onFailure = {

    }
}
```
- `onSuccess`는 `Response<>` 의 확장함수이기에, `res.code()` 가 아닌 `code()`처럼 `Response<>`의 메소드를 호출할 수 있습니다.
  
### model
- 데이터 모델은 `Model` 패키지에 넣습니다.
### ui
- 액티비티마다 패키지를 만들고, 그 안에 액티비티, 프레젠터 등을 넣어 작업합니다.
- Ex) main 패키지를 만든 후 `MainActivity` `MainFragment` 등 들어가야할 파일을 생성
### Util 
- 토큰 저장, 삭제 등 유틸성 파일이 들어갈 패키지입니다.
## 네이밍 컨벤션
이름은 길어도 좋으니 명확하게!
### Java (Kotlin)
https://kotlinlang.org/docs/reference/coding-conventions.html  
이거 참고하세요~~
### drawable
- what_description_where
- where는 여러곳에 사용되면 쓰지 않습니다.
- icon -> ic, background -> back
- ex) ic_list, back_broder_black
### layout
- what_discription
- ex) activity_main, fragment_chatting
### ID
where_description_what
textview -> tv, editText -> et, button -> btn 등등등
ex) main_intro_tv, login_submit_btn
### colors.xml
- color[What] 과 같이 작성하여 사용합니다.
- 추후에 색상코드는 모두 넣어둘 예정입니다.
### string.xml
- 모든 텍스트 리소스는 string.xml에 넣어서 사용합니다.
- where_description_what
