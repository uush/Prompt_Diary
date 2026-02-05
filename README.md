<h1>Prompt Diary[작성중]</h1>
</br>
<h1>프로젝트 소개</h1>
</br>
사용자의 기록을 기억하고 공감하는 AI 멘탈케어 다이어리입니다. 
Room DB에 저장된 일기 데이터를 기반으로 사용자의 과거 맥락을 파악하고, 이를 통해 단순한 챗봇을 넘어 '나를 아는 AI'와의 깊이 있는 위로와 대화를 제공합니다. 
기술을 통해 고립감과 우울감을 해소를 목표로 하고 있습니다.
<br>
<br>

<img width="200" height="300" alt="스크린샷 2026-02-05 오후 10 33 50" src="https://github.com/user-attachments/assets/1904bf8b-8100-4c58-b959-a7722b9d3ca9" />

<h2>주요 기능</h2>
<b>일기 작성</b> : 사용자가 작성한 일기를 RoomDB에 저장 
<br>
<b>AI 채팅</b> : 저장된 일기를 바탕으로 Gemini와 일상 대화 또는 상담

## 🛠 Tech Stack

**Architecture**
* **MVVM Pattern**: View와 로직을 분리하여 유지보수성 향상
* **Repository Pattern**: 데이터 출처(DB, API)를 추상화하여 관리

**Android UI**
* **Jetpack Compose**: 선언형 UI로 직관적이고 효율적인 UI 구성
* **Material Design 3**: 최신 안드로이드 디자인 가이드라인 적용

**Local Database**
* **Room Database**: SQLite 객체 매핑 라이브러리를 통한 로컬 일기 데이터 영속성 관리
* **Flow**: DB 데이터 변경 사항을 실시간으로 UI에 반영 (Reactive Programming)

**AI & Network**
* **Google Gemini API**: Gemini 2.5 flash model 연동
* **Prompt Engineering**: 사용자 입력(일기)을 바탕으로 위로와 공감을 이끌어내는 시스템 프롬프트 설계 및 조립(Context Assembly)


