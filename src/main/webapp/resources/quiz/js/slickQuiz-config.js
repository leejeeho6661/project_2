// Setup your quiz text and questions here

// NOTE: pay attention to commas, IE struggles with those bad boys

var quizJSON = {
    "info": {
        "name":    " 시사 상식 QUIZ",
        "main":    "<p>시사 상식 QUIZ 입니다.</p>",
        "results": "<h5>잘 푸셨나요?</h5><p>문제 풀이 하느라 고생하셨습니다.</p>",
        "level1":  "훌륭합니다! 100점입니다.",
        "level2":  "참 잘하셨습니다! 80점 입니다.",
        "level3":  "조금만 더 관심을 갖자구요 ! ",
        "level4":  "문제가 많이 어려웠나요? ",
        "level5":  "문제가 많이 어려웠나요?" 
    },
    "questions": [
        { // Question 1
            "q": "미국의 100달러 지폐에 그려진 이 인물은 철저한 자기 관리를 통해서 자수성가 한 미국의 대표 사업가 입니다. 피뢰침을 발명한 과학자이자 미국 독립선언서를 작성한 정치인 중 한명인 이 인물은 누구일까요?",
            "a": [
                {"option": "토마스 에디슨",     "correct": false},
                {"option": "에이브러햄 링컨",     "correct": false},
                {"option": "벤자민 프랭클린",     "correct": true},
                {"option": "마틴 루터 킹",     "correct": false} 
            ],
            "correct": "<p><span>정답입니다!</span>작은 지출은 삼가라, 작은 구멍이 거대한 배를 침몰 시킨다라는 말도 남겼습니다.</p>",
            "incorrect": "<p><span>오답입니다! </span> 정답은 벤자민 플랭클린 입니다.</p>" 
        },
        { // Question 2
            "q": "지난 4월 베트남 하노이에 세계 최초의 ()ATM 기기가 등장해 화제입니다. 전 세계에서 베트남이 세번째로 많이 수출하는 ()은 무엇일까요? ",
            "a": [
                {"option": "쌀국수",    "correct": false},
                {"option": "보리",     "correct": false},
                {"option": "감", "correct" : false},
                {"option": "쌀", "correct": true},
            ],
            "correct": "<p><span>정답입니다! </span>코로나19로 일자리를 잃고 생계난을 겪는 시민들에게 쌀을 무료로 제공하고 있습니다.</p>",
            "incorrect": "<p><span>오답입니다! </span> 정답은 쌀! 입니다.</p>" 
        },
        { // Question 3
            "q": "복수 표준어는 여러 가지 형태로 쓰이지만 똑같은 뜻을 가진 단어나 비표준어였지만 일상에서 많이 쓰이는 단어 모두를 표준어로 삼는 것을 말합니다. 다음 중 복수 표준어가 아닌것은?",
            "a": [
                {"option": "옥수수 강냉이",             "correct": false},
                {"option": "강소주 깡소주",           "correct": true},
                {"option": "예쁘다 이쁘다",          "correct": false},
                {"option":"벌레 버러지", "correct":false}
            ],
            "correct": "<p><span> 정답입니다 !!</span></p>",
            "incorrect": "<p><span>오답입니다!</span> 정답은 강소주 깡소주 입니다.</p>" 
        },
        { // Question 4
            "q": "태어난 아기가 저녁마다 우는 이유는 무엇일까요?",
            "a": [
                {"option":"배가 고파서",    "correct": false},
                {"option":"관심 받고싶어서",     "correct": false},
                {"option":"동생 만드는걸 방해하려고", "correct": true},
                {"option":"기저귀를 갈아달라고", "correct": false}
            ],
            "correct": "<p><span>정답입니다!</span> 하버드 논문에 실려 있다고 합니다!</p>",
            "incorrect": "<p><span>틀렸습니다!</span>정답은 동생 만드는걸 방해하려고 입니다.</p>"
    
        },
        { // Question 5
            "q": "질병은 원인에 따라 치료나 방법이 다르기 때문에 원인을 아는 것이 매우 중요합니다. 다음 중 바이러스로 감염되는 질병이 아닌것은?",
            "a": [
                {"option": "감기",   "correct": false},
                {"option": "결핵",          "correct": true},
                {"option": "홍역",  "correct": false},
                {"option":"A형 감염", "correct": false} 
            ],
            "correct": "<p><span>정답입니다!</span> 수고하셨습니다.</p>",
            "incorrect": "<p><span>틀렸습니다.</span> 정답은 결핵입니다.</p>" 
        } // no comma here
    ]
};