// 비동기 함수 사용 연습,
// axios ,
// 키워드, async , 함수의 선언부 앞에 사용하고,
// 키워드, await, 비동기적으로 받아온 함수 앞에 사용, 반환 타입, Promise,

// 비동기 함수의 핵심, 통보,
// 비동기 함수가 동작을 하고서, 무언가 알려주기,
//예) 카페,
//
// 비동기함수, async
// 커피 주문 할 때,
// 주문을 받는 사람이 1명만 있으면, 1명이 혼자서,

// await
// 1) 주문 받고,
// 2) 커피 만들고,
// 3) 커피 전달, -> 통보!! -> Promise

// 만약, 손님이 여러명, 1명의 커피가 완성 되기까지 대기.

// 점원 3명,
//

async function get(recipeid) {
    const result = await axios.get(`/replies/list/${recipeid}`)
    console.log(result)
}

// 마지막 댓글 위치로 이동하기.
async function getList({recipeid,page,size,goLast}) {
    const result = await axios.get(`/replies/list/${recipeid}`,
        {params: {page,size}})
    if(goLast){
        const total = result.data.total

        const lastPage = parseInt(Math.ceil(total/size))
        return getList({recipeid:recipeid,page:lastPage, size:size})
    }
    return result.data
}

//댓글 등록
async function addReply(replyObj){
    const response = await axios.post(`/replies/`, replyObj)
    return response.data
}

//댓글 조회 , rno : 댓글 번호
async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

// 댓글 수정, 수정할 댓글 내용 :  replyObj 이용하기.
async function updateReply(replyObj){
    const response = await axios.put(`/replies/${replyObj.rno}`,replyObj)
    return response.data
}

// 댓글 삭제, 댓글 번호만 필요함 rno
async function deleteReply(rno){
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}











