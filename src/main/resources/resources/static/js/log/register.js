document.addEventListener('DOMContentLoaded', function (){
    var inputs = document.querySelectorAll('input[id^="userEng"]');
    inputs.forEach(function (input){
        input.addEventListener('input', function (){
            this.value = this.value.replace(/[^a-zA-Z]/g, '');
            this.value = this.value.toUpperCase();
        })
    })
})

document.getElementById('emailDupBtn').addEventListener('click', function () {
    const email = document.getElementById('userEmail').value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email) {
        alert('이메일을 입력해주세요');
        return;
    }
    if (!emailRegex.test(email)) {
        alert('이메일 형식이 올바르지 않습니다. 이메일 형식으로 작성해주세요.');
        return;
    }

    fetch('/register2/duplicateEmail', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userEmail: email })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const emailCheckInputContainer = document.getElementById('emailCheckInputContainer');
            const spanNewId = emailCheckInputContainer.querySelector('.newId');
            const spanOldId = emailCheckInputContainer.querySelector('.oldId');
            if (data === "false") {
                console.log(data)
                spanNewId.style.display = 'block';
                spanOldId.style.display = 'none';
            } else if(data === "true") {
                console.log(data)
                spanNewId.style.display = 'none';
                spanOldId.style.display = 'block';
            }
            emailCheckInputContainer.style.display = 'block'; // Show the result container
        })
        .catch(error => {
            console.error('Error:', error);
            const emailCheckInputContainer = document.getElementById('emailCheckInputContainer');
            const span = emailCheckInputContainer.querySelector('.newId');
            span.textContent = '중복 확인 중 오류가 발생했습니다.';
            emailCheckInputContainer.style.display = 'block'; // Show the error message container
        });
});

function idCheck(){
    const userId = document.getElementById('userId').value;
    const idRegex = /^[a-zA-Z0-9]{1,20}$/;

    if (!userId) {
        alert('아이디를 입력해주세요');
        return;
    }
    if (!idRegex.test(userId)) {
        alert('형식이 올바르지 않습니다.');
        return;
    }

    fetch('/register2/duplicateId', {

        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId: userId })
    })
        .then(response => {

            if (!response.ok) { console.log("버튼누르긴함");
                throw new Error('Network response was not ok');
            }
            console.log("버튼누름");
            return response.json();
        })
        .then(data => { console.log("버튼누름" + data);
            const idCheckInputContainer = document.getElementById('idCheckInputContainer');
            const spanNewId = idCheckInputContainer.querySelector('.newId');
            const spanOldId = idCheckInputContainer.querySelector('.oldId');
            if (data ===  false) {
                // 중복이 아닌 경우
                console.log(data);
                spanNewId.style.display = 'block';
                spanOldId.style.display = 'none';
            } else if (data === true) {
                console.log(data);
                spanNewId.style.display = 'none';
                spanOldId.style.display = 'block';
            }
            idCheckInputContainer.style.display = 'block'; // Show the result container
        })
        .catch(error => {
            console.error('Error:', error);
            const idCheckInputContainer = document.getElementById('idCheckInputContainer');
            const span = idCheckInputContainer.querySelector('.newId');
            span.textContent = '중복 확인 중 오류가 발생했습니다.';
            idCheckInputContainer.style.display = 'block'; // Show the error message container
        });
}

